import { combineReducers } from "@ngrx/store";
import * as commandeReducer from '../reducers/commande.reducer';
import * as apiReducer from '../reducers/api.reducer';
import { createCommandeReducer } from "./createCommande.reducer";

export const commandeFeatureKey = 'COMMANDE_FEATURE_KEY';

export const reducers = combineReducers({
    commandes: commandeReducer.commandeReducer,
    createCommande: createCommandeReducer,
    api: apiReducer.apiReducer
});