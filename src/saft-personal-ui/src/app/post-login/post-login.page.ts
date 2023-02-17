import { Component } from '@angular/core';
import { NavController } from '@ionic/angular';

@Component({
  selector: 'app-post-login',
  templateUrl: 'post-login.page.html',
  styleUrls: ['post-login.page.scss'],
})
export class PostLoginPage {
  constructor(private readonly navController: NavController) {}

  public clickBuyTab(): void {
    this.navController.navigateRoot('/home/buy');
  }
}
