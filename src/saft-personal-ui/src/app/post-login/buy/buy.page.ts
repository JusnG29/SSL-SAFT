import { Component, OnDestroy, OnInit } from '@angular/core';
import { Subject, takeUntil } from 'rxjs';
import { Product } from '../../openapi-generated/models';
import { ProductService } from '../../shared/services/product.service';

@Component({
  selector: 'app-buy',
  templateUrl: 'buy.page.html',
  styleUrls: ['buy.page.scss'],
})
export class BuyPage implements OnInit, OnDestroy {
  public allProducts: Product[] = [];
  private $end: Subject<void> = new Subject();

  constructor(private readonly productService: ProductService) {}

  ngOnInit(): void {
    this.loadAllProducts();
  }

  ngOnDestroy(): void {
    this.$end.next();
    this.$end.complete();
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
