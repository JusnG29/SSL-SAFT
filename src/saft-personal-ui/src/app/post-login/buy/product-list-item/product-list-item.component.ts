import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Product } from '../../../openapi-generated/models';

@Component({
  selector: 'app-product-list-item',
  templateUrl: './product-list-item.component.html',
  styleUrls: ['./product-list-item.component.scss'],
})
export class ProductListItemComponent implements OnInit {
  @Output() public countChange: EventEmitter<number> = new EventEmitter();
  @Input() public product: Product;

  constructor() {}

  ngOnInit() {}

  public emitCountChange(count: number): void {
    this.countChange.emit(count);
  }
}
