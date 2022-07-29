import { Injectable } from '@angular/core';
import { map, Observable } from 'rxjs';
import { Transaction } from '../../openapi-generated/models';
import { TransactionRestApiService } from '../../openapi-generated/services';

@Injectable({
  providedIn: 'root',
})
export class TransactionService {
  constructor(
    private readonly transactionRestService: TransactionRestApiService
  ) {}

  public getUserHistory(userId: string): Observable<Transaction[]> {
    return this.transactionRestService
      .getUserHistory$Response({ body: { userId } })
      .pipe(map((response) => response.body));
  }

  public purchase(
    userId: string,
    productId: string,
    amount: number
  ): Observable<Transaction> {
    // TODO: Dynamic price
    return this.transactionRestService
      .purchase$Response({ body: { userId, productId, amount, value: 3.5 } })
      .pipe(map((response) => response.body));
  }
}
