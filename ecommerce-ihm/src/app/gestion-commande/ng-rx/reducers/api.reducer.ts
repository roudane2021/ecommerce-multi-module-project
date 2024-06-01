import { HttpErrorResponse } from "@angular/common/http";
import { createReducer, on, State } from '@ngrx/store';
import * as COMMANDE_ACTION from '../actions/commande.action';
import { state } from '@angular/animations';
import { ApiState } from "../state/state";



const intialSatate: ApiState = { isLoading: false, error: undefined };

export const apiReducer = createReducer(intialSatate,
    on(COMMANDE_ACTION.CommandesActions.commandeActionPaginationError, (state, { error }) => {
        return {...state, isLoading: false, error}
    }),
    on(COMMANDE_ACTION.CommandesActions.commandeActionPaginationStart, (state) => {
         return {...state, isLoading: true}
    })
    ,
    on(COMMANDE_ACTION.CommandesActions.commandeActionPaginationSuccess, (state) => {

         return {...state, isLoading: false}
    })
);