import { Component, OnInit } from '@angular/core';
import { Subject, takeUntil } from 'rxjs';
import { Transaction, User } from '../../shared/openapi-generated/models';
import { TransactionService } from '../../shared/services/transaction.service';
import { UserService } from '../../shared/services/user.service';
import { BalanceState } from './dtos/balance-state.enum';

@Component({
  selector: 'app-balance',
  templateUrl: './balance.page.html',
  styleUrls: ['./balance.page.scss'],
})
export class BalancePage implements OnInit {
  public user: User;
  public balanceState: BalanceState;
  public userHistory: Transaction[] = [];

  private $end: Subject<void> = new Subject();

  constructor(
    private readonly userService: UserService,
    private readonly transactionService: TransactionService
  ) {}

  ngOnInit() {
    this.subscribeToUser();
    this.subscribeToTransactions();
  }

  private subscribeToUser(): void {
    this.userService
      .getAuthenticatedUser$()
      .pipe(takeUntil(this.$end))
      .subscribe((user) => {
        this.user = user;
        this.updateBadge(user.balance);
      });
  }

  public getBadgeIcon(): string {
    return this.balanceState === BalanceState.OK ? 'thumbs-up' : 'thumbs-down';
  }

  public getBadgeDescription(): string {
    return this.balanceState === BalanceState.OK
      ? 'Alles in Ordnung!'
      : 'Bitte um Einzahlung!';
  }

  private updateBadge(balance: number): void {
    if (balance >= 0) {
      this.balanceState = BalanceState.OK;
    } else {
      this.balanceState = BalanceState.DEPT;
    }
  }

  public subscribeToTransactions(): void {
    this.transactionService
      .getUserHistory$()
      .pipe(takeUntil(this.$end))
      .subscribe((history) => {
        this.userHistory = history;
      });

    this.transactionService.loadTransactions(this.user.id);
  }
}
