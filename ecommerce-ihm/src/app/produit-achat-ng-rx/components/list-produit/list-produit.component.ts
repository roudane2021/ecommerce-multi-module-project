import { Component, OnInit } from '@angular/core';
import { Store, createSelector } from '@ngrx/store';
import { Observable, map } from 'rxjs';
import * as productAction from '../../ng-RX/actions/counters.action';
import { selectProduct } from '../../ng-RX/selectors/product.selector';
import { ProductState } from '../../models/product.model';



@Component({
  selector: 'app-list-produit',
  templateUrl: './list-produit.component.html',
  styleUrls: ['./list-produit.component.scss']
})
export class ListProduitComponent implements OnInit{

  count$!: Observable<number>;

  constructor(private store : Store<ProductState>) {
    // TODO: Connect `this.count$` stream to the current store `count` state
  }

  ngOnInit(): void {
    this.store.select(selectProduct).pipe(
      map( data => {
        console.log("data : ")
        console.table(data)
        return data;
      })
    ).subscribe();
    }

  increment() {
   this.store.dispatch(productAction.Get_All_Product_Action());
  }



}


