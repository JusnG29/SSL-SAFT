import { Component, OnDestroy, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { NavController } from '@ionic/angular';
import { Subject, takeUntil } from 'rxjs';
import { User } from '../../shared/domain/user';
import { UserService } from '../../shared/services/user.service';

@Component({
  selector: 'app-login-page',
  templateUrl: './login-page.component.html',
  styleUrls: ['./login-page.component.scss'],
})
export class LoginPageComponent implements OnInit, OnDestroy {
  public loginForm: FormGroup;
  public allUsers: User[] = [];

  private $end: Subject<void> = new Subject();

  constructor(
    private readonly formBuilder: FormBuilder,
    private readonly userService: UserService,
    private readonly navController: NavController
  ) {
    this.loginForm = formBuilder.group({
      couleurName: [undefined, Validators.compose([Validators.required])],
      passCode: [undefined, Validators.compose([Validators.required])],
    });
  }

  ngOnInit(): void {
    this.initializeUserList();
  }

  ngOnDestroy(): void {
    this.$end.next();
    this.$end.complete();
  }

  test() {
    console.log(this.allUsers);
  }

  public initializeLogin() {
    if (this.loginForm.valid) {
      this.userService
        .login(
          this.loginForm.get('couleurName')?.value,
          this.loginForm.get('passCode')?.value
        )
        .subscribe({
          next: (user) => {
            this.navController.navigateRoot('/home');
            this.userService.setAuthenticatedUser(user);
          },
          error: (error) => {
            console.error(error);
          },
        });
    }
  }

  // TODO: Add error handling
  private initializeUserList(): void {
    this.userService
      .getAllUsers()
      .pipe(takeUntil(this.$end))
      .subscribe({
        next: (users) => (this.allUsers = users),
        error: (error) => console.error,
      });
  }
}
