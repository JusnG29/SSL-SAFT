import { Component, OnDestroy, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { NavController } from '@ionic/angular';
import { Subject, takeUntil } from 'rxjs';
import { User } from '../../openapi-generated/models';
import { EnterPinComponent } from '../../shared/components/enter-pin/enter-pin.component';
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
    private readonly pinDialog: MatDialog,
    private readonly navController: NavController
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

    pinDialogRef.afterClosed().subscribe((isValid) => {
      if (isValid) {
        this.completeLogin(user);
      }
    });
  }

  private completeLogin(user: User) {
    this.navController.navigateRoot('/home/buy');
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
