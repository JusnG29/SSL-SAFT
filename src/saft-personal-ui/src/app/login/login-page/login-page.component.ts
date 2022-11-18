import { Component, OnDestroy, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { NavController } from '@ionic/angular';
import { Subject, takeUntil } from 'rxjs';
import { EnterPinComponent } from '../../shared/components/enter-pin/enter-pin.component';
import { User } from '../../shared/openapi-generated/models';
import { JwtService } from '../../shared/services/jwt.service';
import { StorageService } from '../../shared/services/storage.service';
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
    private readonly navController: NavController,
    private readonly storageService: StorageService,
    private readonly jwtService: JwtService
  ) {}

  ngOnInit(): void {
    this.initializeUserList();
  }

  ngOnDestroy(): void {
    this.$end.next();
    this.$end.complete();
  }

  public openPinDialog(user: User): void {
    const pinDialogRef = this.pinDialog.open(EnterPinComponent, {
      data: { user },
    });

    pinDialogRef.afterClosed().subscribe((isValid) => {
      if (isValid) {
        this.completeLogin(user);
      }
    });
  }

  private completeLogin(user: User) {
    this.userService.setAuthenticatedUser(user);

    // TODO: Remove once Jwt implementation is in place
    const jwt = this.jwtService.encodeUser(user);
    this.storageService.setUserJwt(jwt);

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
