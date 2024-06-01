import { Injectable } from "@angular/core";
import { Observable, delay, of, throwError } from "rxjs";
import { Page } from '../../shared/models/page.model';
import { Commande, page_g } from "../models/commande.model";
import { readCommandes } from "../data/commande.data";




@Injectable()
export class CommandeService {



    grtPageCommande(numero : number): Observable<Page<Commande>> {
        let page: Page<Commande> = page_g;
        let commandes;
        switch (numero) {
            case 0:
                 commandes = readCommandes(0);
                page = { ...page, content: commandes };
                break;
            case 1:
                commandes = readCommandes(1);
                page = { ...page, content: commandes };
                break;
            case 2:
                commandes = readCommandes(2);
                page = { ...page, content: commandes, last: true };
                break;
            default:
                return throwError(() => 'Numéro de page non valide');
        }

        return of(page).pipe(delay(2000));
    }

    createCommande = (commande: Commande):void => {
        page_g.content?.push(commande);
    }
    
}