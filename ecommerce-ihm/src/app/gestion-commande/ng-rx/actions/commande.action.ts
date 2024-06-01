import { createActionGroup, props } from "@ngrx/store";

import { Page } from '../../../shared/models/page.model';
import { HttpErrorResponse } from "@angular/common/http";
import { Commande } from "../../models/commande.model";






export const CommandesActions = createActionGroup({
    source: 'Commandes Actions',
    events: {
        'COMMANDE ACTION ADD': props<{ commande: Commande }>(),
        'COMMANDE ACTION DELETE': props<{ commande: Commande }>(),
        'COMMANDE ACTION PAGINATION START': props<{ page: number }>(),
        'COMMANDE ACTION PAGINATION SUCCESS': props<{ payload: Page<Commande> }>(),
        'COMMANDE ACTION PAGINATION ERROR': props<{error: string}>()
    }
});

