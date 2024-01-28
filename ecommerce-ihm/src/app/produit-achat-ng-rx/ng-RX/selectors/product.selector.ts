import { createFeatureSelector, createSelector, State } from '@ngrx/store';


import { Product, ProductState } from '../../models/product.model';
import { productFeatureKey } from '../reducers/product.reducer';





export const selectProductFeature = createFeatureSelector<ProductState>(productFeatureKey);
 
export const selectProduct = createSelector(
  selectProductFeature,
  (state: ProductState) => state.products
);