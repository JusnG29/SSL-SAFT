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

  public setAuthenticatedUser(user: User): void {
    this.authenticatedUser.next(user);
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
    return this.getAllUsers[0];
  }

  public getAllUsers(): Observable<User[]> {
    return of([
      {
        id: 'someid0',
        couleurName: 'Skywalker',
        passCode: '1234',
        balance: 0,
      },
      {
        id: 'someid1',
        couleurName: 'Jusn',
        passCode: '1234',
        balance: 0,
      },
      {
        id: 'someid2',
        couleurName: 'Jusn',
        passCode: '1234',
        balance: 0,
      },
      {
        id: 'someid3',
        couleurName: 'Limos',
        passCode: '1234',
        balance: 0,
      },
      {
        id: 'someid4',
        couleurName: 'UMad',
        passCode: '1234',
        balance: 0,
      },
      {
        id: 'someid5',
        couleurName: 'Malygos',
        passCode: '1234',
        balance: 0,
      },
    ]);
  }
}
