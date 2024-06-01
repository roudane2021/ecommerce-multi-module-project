import { createReducer, on } from "@ngrx/store";
import { Page} from "../../models/product.model";
import * as productState from "../actions/products.action";



export const initialState: Page  = {


};

export const productReducer = createReducer(
    initialState,
    on(productState.List_Products_Success_Action, (state , {payload}) => {
        return { ...payload };
    })
);


