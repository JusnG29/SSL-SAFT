import { Component, OnDestroy, OnInit } from '@angular/core';
import { NavController } from '@ionic/angular';
import { Subject, takeUntil } from 'rxjs';
import { Product, User } from '../../../openapi-generated/models';
import { BasketService } from '../../../shared/services/basket.service';
import { TransactionService } from '../../../shared/services/transaction.service';
import { UserService } from '../../../shared/services/user.service';

@Component({
  selector: 'app-checkout',
  templateUrl: './checkout.page.html',
  styleUrls: ['./checkout.page.scss'],
})
export class CheckoutPage implements OnInit, OnDestroy {
  public user: User;
  public basket: Map<Product, number> = new Map();
  public basketPrice = 0;
  public hasError = false;
  public checkoutIsFinished = false;

  private $end: Subject<void> = new Subject();

  constructor(
    private readonly userService: UserService,
    private readonly basketService: BasketService,
    private readonly navController: NavController,
    private readonly transactionService: TransactionService
  ) {}

  ngOnInit() {
    this.subscribeToUser();
    this.basket = this.basketService.getCopyOfBasket();
    this.basketPrice = this.basketService.getBasketPrice();
  }

  private subscribeToUser(): void {
    this.userService
      .getAuthenticatedUser$()
      .pipe(takeUntil(this.$end))
      .subscribe((user) => (this.user = user));
  }

  ngOnDestroy(): void {
    this.$end.next();
    this.$end.complete();
  }

  public abortCheckout(): void {
    this.navController.pop();
  }

  public finishCheckout(): void {
    this.transactionService
      .executeTransaction(this.user.id, this.basket)
      .pipe(takeUntil(this.$end))
      .subscribe({
        next: (result) => {
          this.hasError = false;
          this.userService.setAuthenticatedUser(result.user);
          this.basketService.clearBasket();
        },
        error: (_) => {
          this.hasError = true;
        },
        complete: () => (this.checkoutIsFinished = true),
      });
  }
}
