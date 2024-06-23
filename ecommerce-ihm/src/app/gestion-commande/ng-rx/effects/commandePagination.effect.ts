import { Actions, createEffect, ofType } from "@ngrx/effects";
import { inject } from '@angular/core';
import { CommandeService } from "../../services/commende.service";
import * as COMMANDE_ACTION from '../actions/commande.action'
import { catchError, map, of, switchMap } from "rxjs";
import { Page } from '../../../shared/models/page.model';
import { Commande } from "../../models/commande.model";




export const commandPaginationEffect = createEffect(
    (actions$ = inject(Actions), commandeService = inject(CommandeService)) => {
  
        return actions$.pipe(
            ofType(COMMANDE_ACTION.CommandesActions.commandeActionPaginationStart),
            switchMap(action => commandeService.grtPageCommande(action.page).pipe(
                map((payload: Page<Commande>) => COMMANDE_ACTION.CommandesActions.commandeActionPaginationSuccess({ payload })),
                catchError(error => of(COMMANDE_ACTION.CommandesActions.commandeActionPaginationError({error})))
            ))
        )
    
    },
    {functional: true}
)