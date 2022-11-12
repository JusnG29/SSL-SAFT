import { Injectable } from '@angular/core';
import { BehaviorSubject, map, Observable, of, throwError } from 'rxjs';
import { User } from '../../openapi-generated/models';
import { UserRestApiService } from '../../openapi-generated/services';
import { StorageService } from './storage.service';

@Injectable({
  providedIn: 'root',
})
export class UserService {
  private authenticatedUser: BehaviorSubject<User | undefined> =
    new BehaviorSubject<User | undefined>(undefined);

  constructor(
    private readonly userService: UserRestApiService,
    private readonly storageService: StorageService
  ) {}

  public getAuthenticatedUser(): User | undefined {
    return this.authenticatedUser.value;
  }

  public getAuthenticatedUser$(): Observable<User | undefined> {
    return this.authenticatedUser.asObservable();
  }

  public setAuthenticatedUser(user: User): void {
    this.authenticatedUser.next(user);
  }

  public isAuthenticated(): boolean {
    return this.authenticatedUser.value !== undefined;
  }

  public login(id: string, passcode: string): Observable<User | undefined> {
    return this.userService.login$Response({ body: { id, passcode } }).pipe(
      map((response) => {
        if (response.ok) {
          return response.body;
        } else {
          throwError(() => 'Could not parse user');
        }
      })
    );
  }

  public logout(): void {
    this.authenticatedUser = undefined;
    this.storageService.clearUserJwt();
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
