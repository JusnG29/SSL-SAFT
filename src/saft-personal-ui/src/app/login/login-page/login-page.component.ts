import { Component, OnDestroy, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Subject, takeUntil } from 'rxjs';
import { EnterPinComponent } from '../../shared/components/enter-pin/enter-pin.component';
import { User } from '../../shared/domain/user';
import { UserService } from '../../shared/services/user.service';

@Component({
  selector: 'app-login-page',
  templateUrl: './login-page.component.html',
  styleUrls: ['./login-page.component.scss'],
})
export class LoginPageComponent implements OnInit, OnDestroy {
  public allUsers: User[] = [];

  private $end: Subject<void> = new Subject();

  constructor(
    private readonly userService: UserService,
    private readonly pinDialog: MatDialog
  ) {}

  ngOnInit(): void {
    this.initializeUserList();
  }

  ngOnDestroy(): void {
    this.$end.next();
    this.$end.complete();
  }

  public openPinDialog(user: User): void {
    const pinDialogRef = this.pinDialog.open(EnterPinComponent);

    pinDialogRef.afterClosed().subscribe((result) => {
      console.log(result);
    });
  }

  private initializeLogin() {
    // if (this.loginForm.valid) {
    //   this.userService
    //     .login(
    //       this.loginForm.get('couleurName')?.value,
    //       this.loginForm.get('passCode')?.value
    //     )
    //     .subscribe({
    //       next: (user) => {
    //         this.navController.navigateRoot('/home');
    //         this.userService.setAuthenticatedUser(user);
    //       },
    //       error: (error) => {
    //         console.error(error);
    //       },
    //     });
    // }
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
