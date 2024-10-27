import { createReducer, on } from '@ngrx/store';
import * as COMMANDE_ACTION from '../actions/commande.action'
import { CreateCommandeState } from '../state/state';



const initialState: CreateCommandeState = {isCreated: false, error: undefined};

export const createCommandeReducer = createReducer(
    initialState,
    on(COMMANDE_ACTION.CommandesActions.commandeActionAddSuccess, (state, {commande}) => {
        return {...state ,isCreated: true};
    }),
    on(COMMANDE_ACTION.CommandesActions.commandeActionAddError, (state, {error}) => {
        return {isCreated: false, error: error.mensaje};
    })
);