import { Component, OnDestroy, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatStepper } from '@angular/material/stepper';
import { Subject, takeUntil } from 'rxjs';
import { Product, Transaction } from '../../openapi-generated/models';
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

  public setSelectedProductAndNavigate(
    product: Product,
    stepper: MatStepper
  ): void {
    // TODO: Fix two clicks needed to navigate
    // programmatically selecting the next step does not work either
    this.selectedProduct = product;
  }

  public isSelectedProduct(product: Product): boolean {
    if (!this.selectedProduct) return false;

    return product.id === this.selectedProduct.id;
  }

  public initializePurchase(stepper: MatStepper): void {
    const user = this.userService.getAuthenticatedUser();
    const count = this.countFormGroup.get('count').value;

    if (user && count) {
      this.transactionService
        .purchase(user.id, this.selectedProduct.id, count)
        .pipe(takeUntil(this.$end))
        .subscribe({
          next: (transaction) => this.completePurchase(stepper),
          error: () => console.error('Purchase not successful'),
        });
    } else {
      console.error('User not found');
    }
  }

  private completePurchase(stepper: MatStepper): void {
    stepper.reset();
    this.countFormGroup.get('count').setValue(1);
    this.selectedProduct = undefined;
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
