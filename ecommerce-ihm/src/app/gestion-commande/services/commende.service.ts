import { Injectable, inject } from "@angular/core";
import { Observable } from "rxjs";
import { Page } from '../../shared/models/page.model';
import { Commande } from "../models/commande.model";
import { HttpClient } from "@angular/common/http";
import { wsService } from '../../shared/url-ws/url.ws';




@Injectable()
export class CommandeService {

    private readonly httpClient: HttpClient = inject(HttpClient);


    grtPageCommande(pageNb : number ): Observable<Page<Commande>> {
        

        return this.httpClient.post<Page<Commande>>(`${wsService.commande.searchProduit}?page=${pageNb}&size=6`, []);
    }

    createCommande = (commande: Commande): Observable<Commande> => {
        
        return this.httpClient.post<Commande>(wsService.commande.createCommande, commande);
    }
    
}