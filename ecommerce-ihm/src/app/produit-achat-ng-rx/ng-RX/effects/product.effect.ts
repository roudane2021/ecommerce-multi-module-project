import { Injectable, inject } from '@angular/core';
import { Actions, createEffect, ofType } from '@ngrx/effects';
import { mergeMap, map, switchMap, catchError, delay, withLatestFrom, tap } from 'rxjs/operators';
import * as fromActionProduct from '../actions/products.action'; // Import your action types
import * as fromSelectProduct from '../reducers/index'
import { of, filter } from 'rxjs';
import { ProduitService } from '../../services/produit.service';
import { Store } from '@ngrx/store';
import { AppState } from '../reducers';




export const productEffect = createEffect(
  (actions$ = inject(Actions), productService = inject(ProduitService), store = inject(Store<AppState>)) => {

    return actions$.pipe(
      ofType(fromActionProduct.List_Products_Start_Action),
      switchMap( (action => productService.getPageProduit(action.page, action.size)
      .pipe(
        delay(1000),
        map(payload => fromActionProduct.List_Products_Success_Action({payload})),
        catchError(error => of(fromActionProduct.List_Products_Error_Action({error})))
      )))
    )
  },
  { functional: true }
);