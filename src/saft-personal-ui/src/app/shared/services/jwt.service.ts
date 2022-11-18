import { Injectable } from '@angular/core';
import { User } from '../openapi-generated/models';

// TODO: Add "real" Jwt implementation
@Injectable({
  providedIn: 'root',
})
export class JwtService {
  constructor() {}

  public decodeUser(jwt: string): User {
    const splits = jwt.split(',');

    return {
      id: splits[0],
      couleurName: splits[1],
      balance: Number(splits[2]),
    };
  }

  // TODO: Remove this, it is not necessary any more, once Jwt implementation is in place
  // ATTENTION: Kinda hacky, since generated User class does not provide a toString();
  public encodeUser(user: User): string {
    return `${user.id},${user.couleurName},${user.balance}`;
  }
}
