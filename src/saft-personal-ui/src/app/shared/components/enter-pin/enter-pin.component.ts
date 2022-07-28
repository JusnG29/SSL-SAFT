import { Component, Input, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef } from '@angular/material/dialog';
import { Subject, takeUntil } from 'rxjs';

@Component({
  selector: 'app-enter-pin',
  templateUrl: './enter-pin.component.html',
  styleUrls: ['./enter-pin.component.scss'],
})
export class EnterPinComponent implements OnInit {
  public loginForm: FormGroup;
  public invalidPinError = false;

  private $end: Subject<void> = new Subject();

  constructor(
    private readonly formBuilder: FormBuilder,
    private readonly pinDialogRef: MatDialogRef<EnterPinComponent, boolean>
  ) {
    this.loginForm = formBuilder.group({
      passCode: [undefined, Validators.compose([Validators.required])],
    });
  }

  ngOnInit() {
    this.subscribeToPinChange();
  }

  public tryReturnToView(): void {
    // TODO: Validate pin

    const passCode = this.loginForm.get('passCode').value;

    const validationResult = true;

    if (validationResult && !this.invalidPinError) {
      this.pinDialogRef.close(true);
    } else {
      this.invalidPinError = false;
    }
  }

  public subscribeToPinChange() {
    this.loginForm.valueChanges
      .pipe(takeUntil(this.$end))
      .subscribe({ next: () => (this.invalidPinError = false) });
  }
}
