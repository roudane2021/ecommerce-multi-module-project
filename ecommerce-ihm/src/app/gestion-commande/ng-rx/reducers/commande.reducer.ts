import { Page } from '../../../shared/models/page.model';
import { createReducer, on } from '@ngrx/store';
import * as COMMANDE_ACTION from '../actions/commande.action'
import { Commande } from '../../models/commande.model';



const initialState: Page<Commande> = {content: []};

export const commandeReducer = createReducer(
    initialState,
    on(COMMANDE_ACTION.CommandesActions.commandeActionPaginationSuccess, (state, {payload}) => {
        
        return {...payload};
    })
);