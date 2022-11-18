import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable, Subject } from 'rxjs';
import { Product } from '../openapi-generated/models';

@Injectable({
  providedIn: 'root',
})
export class BasketService {
  public basketPrice$: BehaviorSubject<number> = new BehaviorSubject<number>(0);
  public basketCleared$: Subject<void> = new Subject<void>();
  private basket: Map<Product, number> = new Map();

  constructor() {}

  public getBasketPrice$(): Observable<number> {
    return this.basketPrice$.asObservable();
  }

  public getBasketPrice(): number {
    return this.basketPrice$.getValue();
  }

  public getBasketCleared$(): Observable<void> {
    return this.basketCleared$.asObservable();
  }

  public getBasket(): Map<Product, number> {
    return this.basket;
  }

  public getCopyOfBasket(): Map<Product, number> {
    return new Map(this.basket);
  }

  public modifyBasket(product: Product, count: number): void {
    if (count === 0) {
      this.basket.delete(product);
    } else {
      this.basket.set(product, count);
    }

    this.calculateBasketPrice();
  }

  public clearBasket(): void {
    this.basket.clear();
    this.basketPrice$.next(0);
    this.basketCleared$.next();
  }

  private calculateBasketPrice() {
    let currentPrice = 0;

    this.basket.forEach((count, product) => {
      currentPrice += product.price * count;
    });

    this.basketPrice$.next(currentPrice);
  }
}
