import { Component, OnDestroy, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Subject, takeUntil } from 'rxjs';
import { Product } from '../../openapi-generated/models';
import { ProductService } from '../../shared/services/product.service';
import { TransactionService } from '../../shared/services/transaction.service';
import { UserService } from '../../shared/services/user.service';

@Component({
  selector: 'app-buy',
  templateUrl: 'buy.page.html',
  styleUrls: ['buy.page.scss'],
})
export class BuyPage implements OnInit, OnDestroy {
  public allProducts: Product[] = [];
  public countFormGroup: FormGroup;
  public selectedProduct: Product | undefined = undefined;
  private $end: Subject<void> = new Subject();

  constructor(
    private readonly formBuilder: FormBuilder,
    private readonly productService: ProductService,
    private readonly transactionService: TransactionService,
    private readonly userService: UserService
  ) {
    this.countFormGroup = formBuilder.group({
      count: [1, Validators.compose([Validators.min(1)])],
    });
  }

  ngOnInit(): void {
    this.loadAllProducts();
  }

  ngOnDestroy(): void {
    this.$end.next();
    this.$end.complete();
  }

  public getProductCount(): number {
    return this.countFormGroup.get('count').value;
  }

  public setSelectedProduct(product: Product): void {
    this.selectedProduct = product;
  }

  // TODO: Fix border coloring
  public isSelectedProduct(product: Product): boolean {
    if (!this.selectedProduct) return false;

    return product.id === this.selectedProduct.id;
  }

  public initializePurchase(): void {
    const user = this.userService.getAuthenticatedUser();
    const count = this.countFormGroup.get('count').value;

    if (user && count) {
      this.transactionService
        .purchase(user.id, this.selectedProduct.id, count)
        .pipe(takeUntil(this.$end))
        .subscribe({
          next: (transaction) => console.log(transaction),
          error: () => console.error('Purchase not successful'),
        });
    } else {
      console.error('User not found');
    }
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
}
