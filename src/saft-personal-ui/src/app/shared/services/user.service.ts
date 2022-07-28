import { Injectable } from '@angular/core';
import { BehaviorSubject, map, Observable, of, throwError } from 'rxjs';
import { User } from '../../openapi-generated/models';
import { UserRestApiService } from '../../openapi-generated/services';

@Injectable({
  providedIn: 'root',
})
export class UserService {
  private authenticatedUser: BehaviorSubject<User | undefined> =
    new BehaviorSubject<User | undefined>(undefined);

  constructor(private readonly userService: UserRestApiService) {}

  public getAuthenticatedUser(): User | undefined {
    return this.authenticatedUser.value;
  }

  public setAuthenticatedUser(user: User): void {
    this.authenticatedUser.next(user);
  }

  public isAuthenticated(): boolean {
    return this.authenticatedUser.value !== undefined;
  }

  public login(couleurName: string, passCode: string): Observable<User> {
    if (
      couleurName === this.getTestUser().couleurName &&
      passCode === this.getTestUser().passcode
    ) {
      return of(this.getTestUser());
    }

    return throwError(() => new Error('Authentication failed'));
  }

  public logout(): void {
    // TODO: Implement logout
    this.authenticatedUser = undefined;
  }

  public getTestUser(): User {
    return this.getAllUsers[0];
  }

  public getAllUsers(): Observable<User[]> {
    return this.userService
      .getAllUsers$Response()
      .pipe(map((value) => value.body));
  }
}
