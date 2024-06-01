import * as fromProduct from './product.reducer';
import * as fromApi from './api.reducer';
import { createFeatureSelector, createSelector , Action, combineReducers} from '@ngrx/store';
import { Page } from '../../models/product.model';

export const productFeatureKey = 'productState';

export interface AppState {
    products: Page,
    api: fromApi.ApiState
};

export  const  reducers = combineReducers({
    products: fromProduct.productReducer,
    api: fromApi.apiReducer
   });


const productfeatureSelector = createFeatureSelector<AppState>(productFeatureKey);

export const productSelector = createSelector(productfeatureSelector, state => state.products);
export const apiSelector = createSelector(productfeatureSelector, state => state.api);

export const isLoadingSelector = createSelector(apiSelector, state => state.isLoading);

export const listProductSuivant = createSelector(productSelector, page => page);

