import {
  Component,
  EventEmitter,
  OnDestroy,
  OnInit,
  Output,
} from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Subject, takeUntil } from 'rxjs';
import { BasketService } from '../../../shared/services/basket.service';

@Component({
  selector: 'app-product-counter',
  templateUrl: './product-counter.component.html',
  styleUrls: ['./product-counter.component.scss'],
})
export class ProductCounterComponent implements OnInit, OnDestroy {
  @Output() public countChange: EventEmitter<number> = new EventEmitter();
  public countForm: FormGroup;
  private $end: Subject<void> = new Subject();

  constructor(
    private readonly formBuilder: FormBuilder,
    private readonly basketService: BasketService
  ) {
    this.countForm = formBuilder.group({
      count: [0, Validators.compose([Validators.required, Validators.min(0)])],
    });
  }

  ngOnInit() {
    this.subscribeToBasketCleared();
  }

  ngOnDestroy(): void {
    this.$end.next();
    this.$end.complete();
  }

  public incrementCount(i: number = 1): void {
    const countControl = this.countForm.get('count');
    const currentValue = countControl.value;

    countControl.setValue(currentValue + i);
    this.countChange.emit(currentValue + i);
  }

  public decrementCount(i: number = 1): void {
    const currentValue = this.countForm.get('count').value;

    if (currentValue >= 1) {
      this.countForm.get('count').setValue(currentValue - i);
      this.countChange.emit(currentValue - i);
    }
  }

  private subscribeToBasketCleared(): void {
    this.basketService
      .getBasketCleared$()
      .pipe(takeUntil(this.$end))
      .subscribe({
        next: () => this.countForm.get('count').setValue(0),
      });
  }
}
