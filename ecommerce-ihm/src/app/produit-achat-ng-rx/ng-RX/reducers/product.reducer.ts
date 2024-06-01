import { State, createReducer, on } from "@ngrx/store";
import { ProductState, ProductStateEnum } from "../../models/product.model";
import { Get_All_Product_Action, Get_All_Product_Error_Action, Get_All_Product_Success_Action } from "../actions/counters.action";
import { state } from "@angular/animations";


export const productFeatureKey = 'productState';


export const initialState: ProductState = {
    products : [],
    errorMessage : '',
    dataState: ProductStateEnum.INITIAL
}

export const productReducer = createReducer(
    initialState,
    on(Get_All_Product_Action, (state, action) => {
        return {...state};
    }),
    on(Get_All_Product_Success_Action, (state , action) => {

        return {...state, products: action.payload};
    }),
    on(Get_All_Product_Error_Action, (state, action) => {
        return {...state, errorMessage: action.type}
    })
);


