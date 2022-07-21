import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-login-page',
  templateUrl: './login-page.component.html',
  styleUrls: ['./login-page.component.scss'],
})
export class LoginPageComponent implements OnInit {
  public loginForm: FormGroup;

  constructor(private readonly formBuilder: FormBuilder) {
    this.loginForm = formBuilder.group({
      couleurName: [undefined, Validators.compose([Validators.required])],
      passCode: [undefined, Validators.compose([Validators.required])],
    });
  }

  ngOnInit(): void {}

  public initializeLogin() {
    // if (this.passcode === '1234') {
    //   this.router.navigate(['success']);
    // } else {
    //   this.dialog.open(MessageComponent, {
    //     data: {
    //       message: 'Error',
    //     },
    //   });
    // }
  }
}
