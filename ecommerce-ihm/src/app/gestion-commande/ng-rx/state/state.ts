import { ErrorModel } from 'src/app/shared/models/error.model';
import { Page } from '../../../shared/models/page.model';
import { Commande } from '../../models/commande.model';


export interface ApiState {
    isLoading: boolean | undefined;
    error : string | undefined;
}

export interface CreateCommandeState {
    isCreated: boolean | undefined;
    error : string | undefined;
}

export interface FeatureState{
    commandes: Page<Commande>,
    createCommande: CreateCommandeState,
    api: ApiState
}