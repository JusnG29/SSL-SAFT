import { Component, Inject, Input, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Subject, takeUntil } from 'rxjs';
import { User } from '../../../openapi-generated/models';
import { UserService } from '../../services/user.service';

@Component({
  selector: 'app-enter-pin',
  templateUrl: './enter-pin.component.html',
  styleUrls: ['./enter-pin.component.scss'],
})
export class EnterPinComponent implements OnInit {
  public loginForm: FormGroup;
  public invalidPinError = false;
  private user: User;

  private $end: Subject<void> = new Subject();

  constructor(
    private readonly formBuilder: FormBuilder,
    private readonly pinDialogRef: MatDialogRef<EnterPinComponent, boolean>,
    private readonly userService: UserService,
    @Inject(MAT_DIALOG_DATA) public data: { user: User }
  ) {
    this.user = data.user;
    this.loginForm = formBuilder.group({
      passCode: [undefined, Validators.compose([Validators.required])],
    });
  }

  ngOnInit() {
    this.subscribeToPinChange();
  }

  public tryReturnToView(): void {
    const passCode = this.loginForm.get('passCode').value;
    this.userService
      .login(this.user.id, passCode)
      .pipe(takeUntil(this.$end))
      .subscribe({
        next: (user) => {
          if (user && user.id === this.user.id && !this.invalidPinError) {
            this.pinDialogRef.close(true);
          } else {
            this.invalidPinError = false;
          }
        },
        error: () => {
          this.loginForm.reset();
          this.invalidPinError = true;
        },
      });
  }

  public subscribeToPinChange() {
    this.loginForm.valueChanges
      .pipe(takeUntil(this.$end))
      .subscribe({ next: () => (this.invalidPinError = false) });
  }
}
