import { Component, OnInit } from '@angular/core';
import { MatDialog, MatDialogRef } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { MessageComponent } from '../message/message.component';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit{

  public passcode: string = "";

  public ngOnInit(): void {
  }

  constructor(private dialog: MatDialog, private router: Router) { }
  login(){
    if(this.passcode === "1234")
    {
      this.router.navigate(['success']);
    }
    else
    {
      this.dialog.open(MessageComponent,{data:{
        message: "Error"
      }});
    }
  }

}
