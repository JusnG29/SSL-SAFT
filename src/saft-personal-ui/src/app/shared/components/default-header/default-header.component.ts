import { Component, OnDestroy, OnInit } from '@angular/core';
import { Subject, takeUntil } from 'rxjs';
import { User } from '../../openapi-generated/models';

import { UserService } from '../../services/user.service';

@Component({
  selector: 'app-default-header',
  templateUrl: './default-header.component.html',
  styleUrls: ['./default-header.component.scss'],
})
export class DefaultHeaderComponent implements OnInit {
  public user: User;

  constructor(private readonly userService: UserService) {}

  ngOnInit(): void {
    this.user = this.userService.getAuthenticatedUser();
  }
}
