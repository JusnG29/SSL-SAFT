import { Injectable } from '@angular/core';
import { BehaviorSubject, map, Observable, take, tap } from 'rxjs';
import { Product, PurchaseDto, Transaction } from '../openapi-generated/models';
import { TransactionRestApiService } from '../openapi-generated/services';

@Injectable({
  providedIn: 'root',
})
export class TransactionService {
  private transactions$: BehaviorSubject<Transaction[]> = new BehaviorSubject(
    []
  );

  constructor(
    private readonly transactionRestService: TransactionRestApiService
  ) {}

  public executeTransaction(
    userId: string,
    basket: Map<Product, number>
  ): Observable<Transaction> {
    let purchases: PurchaseDto[] = [];

    basket.forEach((value: number, key: Product) => {
      purchases.push({
        productId: key.id,
        amount: value,
      });
    });

    return this.transactionRestService
      .executeTransaction$Response({
        body: { userId: userId, purchaseDTOList: purchases },
      })
      .pipe(
        map((value) => value.body),
        tap((transaction) => this.addTransaction(transaction))
      );
  }

  public loadTransactions(userId: string): void {
    this.transactionRestService
      .getUserHistory$Response({ body: { userId } })
      .pipe(
        take(1),
        map((response) => response.body)
      )
      .subscribe({
        next: (transactions) => this.transactions$.next(transactions),
        error: () => console.error('Could not load transactions'),
      });
  }

  public getUserHistory$(): Observable<Transaction[]> {
    return this.transactions$.asObservable();
  }

  private addTransaction(transaction: Transaction): void {
    this.transactions$.next([transaction, ...this.transactions$.value]);
  }
}
