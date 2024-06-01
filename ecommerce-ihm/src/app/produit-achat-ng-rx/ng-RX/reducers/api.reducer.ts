import { HttpErrorResponse } from "@angular/common/http";
import { createReducer, on, State } from '@ngrx/store';
import * as productAction from "../actions/products.action";





export interface ApiState {
    isLoading: boolean | undefined;
    error : HttpErrorResponse | undefined;
}


const initState : ApiState = {isLoading: false, error : undefined};

export const apiReducer = createReducer(initState,
    on(productAction.List_Products_Start_Action, (state) => {

        return {...state, isLoading: true};
    }),
    on(productAction.List_Products_Error_Action, (state, {error}) => {
         return {...state, isLoading: false, error}

    })
    ,
    on(productAction.List_Products_Success_Action, (state) => {
         return {...state, isLoading: false, error: undefined}

    })
    );