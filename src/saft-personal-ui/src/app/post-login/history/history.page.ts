import { Component, OnDestroy, OnInit } from '@angular/core';
import { Observable, Subject, takeUntil } from 'rxjs';
import { Transaction } from '../../openapi-generated/models';
import { TransactionService } from '../../shared/services/transaction.service';
import { UserService } from '../../shared/services/user.service';

@Component({
  selector: 'app-history',
  templateUrl: 'history.page.html',
  styleUrls: ['history.page.scss'],
})
export class HistoryPage implements OnInit, OnDestroy {
  public history: Transaction[] = [];
  private $end: Subject<void> = new Subject();

  constructor(
    private readonly userService: UserService,
    private readonly transactionService: TransactionService
  ) {}

  ngOnInit(): void {
    const user = this.userService.getAuthenticatedUser();

    if (user) {
      this.transactionService
        .getUserHistory(user.id)
        .pipe(takeUntil(this.$end))
        .subscribe({ next: (transactions) => (this.history = transactions) });
    }
  }

  ngOnDestroy(): void {
    throw new Error('Method not implemented.');
  }
}
