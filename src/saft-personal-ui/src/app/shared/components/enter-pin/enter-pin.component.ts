import { Component, Input, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef } from '@angular/material/dialog';

@Component({
  selector: 'app-enter-pin',
  templateUrl: './enter-pin.component.html',
  styleUrls: ['./enter-pin.component.scss'],
})
export class EnterPinComponent implements OnInit {
  public loginForm: FormGroup;

  constructor(
    private readonly formBuilder: FormBuilder,
    private readonly pinDialogRef: MatDialogRef<EnterPinComponent, string>
  ) {
    this.loginForm = formBuilder.group({
      passCode: [undefined, Validators.compose([Validators.required])],
    });
  }

  ngOnInit() {}

  public returnToView(): void {
    // TODO: Validate pin
    this.pinDialogRef.close(this.loginForm.get('passCode').value);
  }
}
