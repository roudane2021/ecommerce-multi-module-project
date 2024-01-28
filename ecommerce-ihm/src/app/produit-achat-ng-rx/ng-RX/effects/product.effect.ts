import { Injectable, inject } from '@angular/core';
import { Actions, createEffect, ofType } from '@ngrx/effects';
import { mergeMap, map, switchMap, catchError } from 'rxjs/operators';
import { Get_All_Product_Success_Action, ProductsActionsType, Get_All_Product_Error_Action, Get_All_Product_Action } from '../actions/counters.action'; // Import your action types
import { of } from 'rxjs';
import { ProduitService } from '../../service/produit.service';




export const productEffect = createEffect(
  (actions$ = inject(Actions), productService = inject(ProduitService)) => {

    return actions$.pipe(
      ofType(Get_All_Product_Action),
      switchMap( action => productService.getAllProduit()
      .pipe(
        map(payload => Get_All_Product_Success_Action({payload})),
        catchError(error => of(Get_All_Product_Error_Action({error})))
      ))
    )
  },
  { functional: true }
);