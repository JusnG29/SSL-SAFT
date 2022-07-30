import { Injectable } from '@angular/core';
import {
  ActivatedRouteSnapshot,
  CanActivate,
  Router,
  RouterStateSnapshot,
  UrlTree,
} from '@angular/router';
import { from, Observable } from 'rxjs';
import { User } from '../../openapi-generated/models';
import { JwtService } from '../services/jwt.service';
import { StorageService } from '../services/storage.service';
import { UserService } from '../services/user.service';

@Injectable({
  providedIn: 'root',
})
export class AuthenticationGuard implements CanActivate {
  private authenticatedUser: User | undefined = undefined;

  constructor(
    private readonly userService: UserService,
    private readonly storageService: StorageService,
    private readonly jwtService: JwtService,
    private readonly router: Router
  ) {}

  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot
  ):
    | Observable<boolean | UrlTree>
    | Promise<boolean | UrlTree>
    | boolean
    | UrlTree {
    return this.checkForAuthenticatedUser();
  }

  private async checkForAuthenticatedUser(): Promise<boolean | UrlTree> {
    if (!(await this.authenticatedUserIsPresent())) {
      return this.router.navigate(['/login']);
    } else {
      return true;
    }
  }

  private async authenticatedUserIsPresent(): Promise<boolean> {
    this.authenticatedUser = this.userService.getAuthenticatedUser();

    if (this.authenticatedUser) {
      return true;
    } else {
      const userJwt = await this.storageService.getUserJwt();
      if (!userJwt || userJwt.length === 0) {
        return false;
      }

      const user = this.jwtService.decodeUser(userJwt);
      this.userService.setAuthenticatedUser(user);
      return true;
    }
  }
}
