import { Component, EventEmitter, Input, OnInit, Output, inject } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { Observable } from 'rxjs';
import { ProductService } from '../../../services/product.service';
import { Produit } from '../../../models/produit.model';

@Component({
  selector: 'app-product-item',
  templateUrl: './product-item.component.html',
  styleUrls: ['./product-item.component.scss']
})
export class ProductItemComponent implements OnInit{

  private readonly _productService = inject(ProductService);
  products$!: Observable<Produit[]>;

  @Input() productItemGroup!: FormGroup;
  @Input() index!: number;
  @Output() deleteProductEvent: EventEmitter<number> = new EventEmitter<number>();


  ngOnInit(): void {
    this.products$ = this._productService.product;
    this._productService.doawloadProduct();
  }


  
deleteItem() {
  this.deleteProductEvent.emit(this.index);
}

}
