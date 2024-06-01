import { createAction, props } from "@ngrx/store";
import { Page, Product } from "../../models/product.model";
import { HttpErrorResponse } from "@angular/common/http";



export enum ProductsActionsType{
    LIST_PRODUCTS_START_PRODUCTS = '[PRODUCTS] GET ALL PRODUCTS',
    LIST_PRODUCTS_SUIVANT_SUCCESS = '[PRODUCTS] GET ALL PRODUCTS SUCCESS',
    LIST_PRODUCTS_SUIVANT_ERROR = '[PRODUCTS] GET ALL PRODUCTS ERROR'
}



export const List_Products_Start_Action = createAction(ProductsActionsType.LIST_PRODUCTS_START_PRODUCTS, props<{page : number | undefined, size : number | undefined}>());
export const List_Products_Success_Action  = createAction(ProductsActionsType.LIST_PRODUCTS_SUIVANT_SUCCESS, props<{payload: Page}>());
export const List_Products_Error_Action = createAction(ProductsActionsType.LIST_PRODUCTS_SUIVANT_ERROR,props<{error: HttpErrorResponse}>());