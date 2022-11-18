import { Component, OnDestroy, OnInit } from '@angular/core';
import { NavController } from '@ionic/angular';
import { Subject, takeUntil } from 'rxjs';
import { Product, User } from '../../openapi-generated/models';
import { BasketService } from '../../shared/services/basket.service';
import { ProductService } from '../../shared/services/product.service';
import { TransactionService } from '../../shared/services/transaction.service';
import { UserService } from '../../shared/services/user.service';

@Component({
  selector: 'app-buy',
  templateUrl: 'buy.page.html',
  styleUrls: ['buy.page.scss'],
})
export class BuyPage implements OnInit, OnDestroy {
  public user: User;
  public allProducts: Product[] = [];
  public basketPrice: number = 0;
  private $end: Subject<void> = new Subject();

  constructor(
    private readonly productService: ProductService,
    private readonly transactionService: TransactionService,
    private readonly userService: UserService,
    private readonly basketService: BasketService,
    private readonly navController: NavController
  ) {}

  ngOnInit(): void {
    this.loadAllProducts();
    this.subscribeToBasketPrice();
    this.user = this.userService.getAuthenticatedUser();
  }

  ngOnDestroy(): void {
    this.$end.next();
    this.$end.complete();
  }

  public initializePurchase(): void {
    this.navController.navigateForward('/home/buy/checkout');
  }

  public applyProductCountChange(product: Product, count: number): void {
    this.basketService.modifyBasket(product, count);
  }

  private loadAllProducts(): void {
    this.productService
      .getAllProducts()
      .pipe(takeUntil(this.$end))
      .subscribe({
        next: (products) => (this.allProducts = products),
        error: console.error,
      });
  }

  private subscribeToBasketPrice(): void {
    this.basketService
      .getBasketPrice$()
      .pipe(takeUntil(this.$end))
      .subscribe({
        next: (price) => (this.basketPrice = price),
      });
  }
}
