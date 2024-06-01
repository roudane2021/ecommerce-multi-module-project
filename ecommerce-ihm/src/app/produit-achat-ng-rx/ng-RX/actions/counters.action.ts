import { createAction, props } from "@ngrx/store";
import { Product } from "../../models/product.model";



export enum ProductsActionsType{
    GET_ALL_PRODUCTS = '[PRODUCTS] GET ALL PRODUCTS',
    GET_ALL_PRODUCTS_SUCCESS = '[PRODUCTS] GET ALL PRODUCTS Success',
    GET_ALL_PRODUCTS_ERROR = '[PRODUCTS] GET ALL PRODUCTS ERROR'
}



export const Get_All_Product_Action = createAction(ProductsActionsType.GET_ALL_PRODUCTS);
export const Get_All_Product_Success_Action = createAction(ProductsActionsType.GET_ALL_PRODUCTS_SUCCESS, props<{payload: Product[]}>());
export const Get_All_Product_Error_Action = createAction(ProductsActionsType.GET_ALL_PRODUCTS_ERROR,props<{error:any}>());