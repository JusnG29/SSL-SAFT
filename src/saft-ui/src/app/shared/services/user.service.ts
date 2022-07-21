import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable, of, throwError } from 'rxjs';
import { User } from '../domain/user';

@Injectable({
  providedIn: 'root',
})
export class UserService {
  private authenticatedUser: BehaviorSubject<User | undefined> =
    new BehaviorSubject<User | undefined>(undefined);

  constructor() {}

  public getAuthenticatedUser(): User | undefined {
    return this.authenticatedUser.value;
  }

  public isAuthenticated(): boolean {
    return this.authenticatedUser.value !== undefined;
  }

  public login(couleurName: string, passCode: string): Observable<User> {
    if (
      couleurName === this.getTestUser().couleurName &&
      passCode === this.getTestUser().passCode
    ) {
      return of(this.getTestUser());
    }

    return throwError(() => new Error('Authentication failed'));
  }

  public getTestUser(): User {
    return {
      id: 'someid',
      couleurName: 'Skywalker',
      passCode: '1234',
      balance: 0,
    };
  }
}
