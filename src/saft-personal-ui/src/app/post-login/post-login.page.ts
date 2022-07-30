import { AfterViewInit, Component } from '@angular/core';

@Component({
  selector: 'app-post-login',
  templateUrl: 'post-login.page.html',
  styleUrls: ['post-login.page.scss'],
})
export class PostLoginPage implements AfterViewInit {
  constructor() {}

  ngAfterViewInit(): void {
    console.log('asdf');
  }
}
