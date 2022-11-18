import { Injectable } from '@angular/core';
import { map, Observable } from 'rxjs';
import { Product } from '../openapi-generated/models';
import { ProductRestApiService } from '../openapi-generated/services';

@Injectable({
  providedIn: 'root',
})
export class ProductService {
  constructor(private readonly productRestService: ProductRestApiService) {}

  public getAllProducts(): Observable<Product[]> {
    return this.productRestService
      .getAllProducts$Response()
      .pipe(map((value) => value.body));
  }
}
