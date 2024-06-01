import { createFeatureSelector, createSelector } from '@ngrx/store';
import { commandeFeatureKey } from '../reducers/root.reducer';
import {  FeatureState } from '../state/state';







export const commandeFeatureSelector = createFeatureSelector<FeatureState>(commandeFeatureKey);

export const commandeSelectorFeature = createSelector(commandeFeatureSelector, state => state.commandes);
export const apiSelectorFeature = createSelector(commandeFeatureSelector, state => state.api);

export const pageCommandeSelector = createSelector(commandeSelectorFeature, state => state);
export const apiSelector = createSelector(apiSelectorFeature, state => state.isLoading);






