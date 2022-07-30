import { Injectable } from '@angular/core';
import { Storage } from '@ionic/storage-angular';

@Injectable({
  providedIn: 'root',
})
export class StorageService {
  private _storage: Storage;
  private readonly JWT_KEY = 'saft-active-user-jwt';

  constructor(private readonly storage: Storage) {}

  public async initialize(): Promise<void> {
    this._storage = await this.storage.create();
  }

  public setUserJwt(jwt: string): void {
    console.log(jwt);
    this._storage.set(this.JWT_KEY, jwt);
  }

  public clearUserJwt(): void {
    this._storage.remove(this.JWT_KEY);
  }

  public async getUserJwt(): Promise<string> {
    return this._storage.get(this.JWT_KEY);
  }
}
