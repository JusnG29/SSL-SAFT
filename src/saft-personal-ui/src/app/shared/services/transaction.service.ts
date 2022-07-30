import { Injectable } from '@angular/core';
import { BehaviorSubject, map, Observable, take, tap } from 'rxjs';
import { Transaction } from '../../openapi-generated/models';
import { TransactionRestApiService } from '../../openapi-generated/services';

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

  public getUserHistory$(): Observable<Transaction[]> {
    return this.transactions$.asObservable();
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

  public purchase(
    userId: string,
    productId: string,
    amount: number
  ): Observable<Transaction> {
    // TODO: Dynamic price
    return this.transactionRestService
      .purchase$Response({ body: { userId, productId, amount, value: 3.5 } })
      .pipe(
        map((response) => response.body),
        tap((response) => {
          this.appendTransaction(response);
        })
      );
  }

  private appendTransaction(transaction: Transaction) {
    const currentTransactions = this.transactions$.value;

    this.transactions$.next([...currentTransactions, transaction]);
  }
}
