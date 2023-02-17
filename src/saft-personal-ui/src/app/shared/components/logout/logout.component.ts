import { Component, OnInit } from '@angular/core';
import { NavController } from '@ionic/angular';
import { UserService } from '../../services/user.service';
import { faSignOutAlt } from '@fortawesome/free-solid-svg-icons';

@Component({
  selector: 'app-logout',
  templateUrl: './logout.component.html',
  styleUrls: ['./logout.component.scss'],
})
export class LogoutComponent implements OnInit {
  public faRightFromBracket = faSignOutAlt;

  constructor(
    private readonly navController: NavController,
    private readonly userService: UserService
  ) {}

  ngOnInit() {}

  public logout(): void {
    this.userService.logout();
    this.navController.navigateRoot('/login');
  }
}
