import { Actions, createEffect, ofType } from "@ngrx/effects";
import { inject } from '@angular/core';
import { CommandeService } from "../../services/commende.service";
import * as COMMANDE_ACTION from '../actions/commande.action'
import { catchError, map, of, switchMap } from "rxjs";
import { Page } from '../../../shared/models/page.model';
import { Commande } from "../../models/commande.model";
import { ErrorModel } from "src/app/shared/models/error.model";




export const commandCreationEffect = createEffect(
    (actions$ = inject(Actions), commandeService = inject(CommandeService)) => {
  
        return actions$.pipe(
            ofType(COMMANDE_ACTION.CommandesActions.commandeActionAddStart),
            switchMap(action => commandeService.createCommande(action.commande).pipe(
                map((commande: Commande) => COMMANDE_ACTION.CommandesActions.commandeActionAddSuccess({ commande })),
                catchError((error: ErrorModel) => of(COMMANDE_ACTION.CommandesActions.commandeActionAddError({error})))
            ))
        )
    
    },
    {functional: true}
)