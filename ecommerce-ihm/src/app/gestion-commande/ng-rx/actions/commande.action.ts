import { createActionGroup, props } from "@ngrx/store";

import { Page } from '../../../shared/models/page.model';
import { Commande } from "../../models/commande.model";
import { ErrorModel } from "src/app/shared/models/error.model";






export const CommandesActions = createActionGroup({
    source: 'Commandes Actions',
    events: {
        'COMMANDE ACTION ADD START': props<{ commande: Commande }>(),
        'COMMANDE ACTION ADD SUCCESS': props<{ commande: Commande }>(),
        'COMMANDE ACTION ADD ERROR': props<{error: ErrorModel}>(),
        'COMMANDE ACTION DELETE': props<{ commande: Commande }>(),
        'COMMANDE ACTION PAGINATION START': props<{ page: number }>(),
        'COMMANDE ACTION PAGINATION SUCCESS': props<{ payload: Page<Commande> }>(),
        'COMMANDE ACTION PAGINATION ERROR': props<{error: string}>()
    }
});

