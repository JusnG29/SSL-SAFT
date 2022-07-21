import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { UserService } from '../../shared/services/user.service';

@Component({
  selector: 'app-login-page',
  templateUrl: './login-page.component.html',
  styleUrls: ['./login-page.component.scss'],
})
export class LoginPageComponent implements OnInit {
  public loginForm: FormGroup;

  constructor(
    private readonly formBuilder: FormBuilder,
    private readonly userService: UserService
  ) {
    this.loginForm = formBuilder.group({
      couleurName: [undefined, Validators.compose([Validators.required])],
      passCode: [undefined, Validators.compose([Validators.required])],
    });
  }

  ngOnInit(): void {}

  public initializeLogin() {
    if (this.loginForm.valid) {
      this.userService
        .login(
          this.loginForm.get('couleurName')?.value,
          this.loginForm.get('passCode')?.value
        )
        .subscribe({
          next: (user) => {
            console.log(user);
          },
          error: (error) => {
            console.error(error);
          },
        });
    }
  }
}
