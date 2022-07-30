import { Injectable } from '@angular/core';
import { User } from '../../openapi-generated/models';

@Injectable({
  providedIn: 'root',
})
export class StorageService {
  private readonly JWT_KEY = 'saft-active-user-jwt';

  constructor(private readonly storage: Storage) {}

  public setUserJwt(jwt: string): void {
    this.storage.setItem(this.JWT_KEY, jwt);
  }

  public unsetUserJwt(): void {
    this.storage.removeItem(this.JWT_KEY);
  }
}
