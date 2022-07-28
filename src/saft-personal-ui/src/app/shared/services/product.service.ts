import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { Product } from '../../openapi-generated/models';
import { ProductRestApiService } from '../../openapi-generated/services';

@Injectable({
  providedIn: 'root',
})
export class ProductService {
  constructor(private readonly productRestService: ProductRestApiService) {}

  public getAllProducts(): Observable<Product[]> {
    return of([
      { id: 'a', name: 'Bier', price: 1 },
      { id: 'a', name: 'Wein', price: 1 },
      { id: 'a', name: 'Schokolade', price: 1 },
      { id: 'a', name: 'Pizza', price: 1 },
      { id: 'a', name: 'Knabber', price: 1 },
      { id: 'a', name: 'Kaffee', price: 1 },
    ]);

    // return this.productRestService.productAllGet$Response();
  }
}
