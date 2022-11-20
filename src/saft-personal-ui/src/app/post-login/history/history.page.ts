import { Component, OnDestroy, OnInit } from '@angular/core';
import { Subject, takeUntil } from 'rxjs';
import { Transaction, User } from '../../shared/openapi-generated/models';
import { TransactionService } from '../../shared/services/transaction.service';
import { UserService } from '../../shared/services/user.service';

@Component({
  selector: 'app-history',
  templateUrl: 'history.page.html',
  styleUrls: ['history.page.scss'],
})
export class HistoryPage implements OnInit, OnDestroy {
  public user: User;
  public history: Transaction[] = [];
  private $end: Subject<void> = new Subject();

  constructor(
    private readonly userService: UserService,
    private readonly transactionService: TransactionService
  ) {}

  ngOnInit(): void {
    this.subscribeToUser();
    this.subscribeToTransactions();
  }

  ngOnDestroy(): void {
    this.$end.next();
    this.$end.complete();
  }

  private subscribeToUser(): void {
    this.userService
      .getAuthenticatedUser$()
      .pipe(takeUntil(this.$end))
      .subscribe((user) => (this.user = user));
  }

  private subscribeToTransactions(): void {
    this.transactionService
      .getUserHistory$()
      .pipe(takeUntil(this.$end))
      .subscribe((history) => (this.history = history));

    this.transactionService.loadTransactions(this.user.id);
  }
}
