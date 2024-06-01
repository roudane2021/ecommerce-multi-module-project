import { createFeatureSelector, createSelector, State } from '@ngrx/store';


import { Product, ProductState } from '../../models/product.model';
import {  StateProduct } from '../reducers/product.reducer';





/**export const selectProductFeature = createFeatureSelector<StateProduct>(productFeatureKey);
 
export const selectProduct = createSelector(
  selectProductFeature,
  (state: StateProduct) => state.entities
);**/