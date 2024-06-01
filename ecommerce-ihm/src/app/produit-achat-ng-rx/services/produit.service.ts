

import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable, catchError, delay, filter, of, pipe, tap } from 'rxjs';
import { wsService } from 'src/app/shared/url-ws/url.ws';
import { environment } from 'src/environments/environment';
import { Criteria, Page, Product, Produit } from '../models/product.model';

@Injectable()
export class ProduitService {

  private _loading$: BehaviorSubject<boolean> = new BehaviorSubject<boolean>(false);
  private _page$: BehaviorSubject<Page> = new BehaviorSubject<Page>({} as Page);
  private _filter$: BehaviorSubject<Criteria[]> = new BehaviorSubject<Criteria[]>([]);
  private _applyFilter$: BehaviorSubject<boolean> = new BehaviorSubject<boolean>(false);

  constructor(private httpClient: HttpClient) { }

  get loading$(): Observable<boolean> {
    return this._loading$?.asObservable() || undefined;
  }
  get page$(): Observable<Page>{
    return this._page$?.asObservable() || undefined;
  }

  set filter$(value : Criteria[]) {
    const filters : Criteria[] = value?.filter(filter => filter.name && filter.operator != undefined && filter.value) || [];
    this._filter$.next(filters);
  }

  set applyFilter$(value : boolean) {
    this._applyFilter$.next(value);
  }

/**
 * Charge la page de produits en fonction de la taille spécifiée et des critères de recherche.
 *
 */
getPageProduit(currentPage : number | undefined, size : number | undefined): Observable<Page> {
  
  currentPage = currentPage ?? environment.page_default;
  size = size ?? environment.size_default;

  const applyFilter = this._applyFilter$.getValue();
   let filters = [] as Criteria[];

  if(applyFilter) {
   filters = this._filter$.getValue();
  }
  
  
  // Effectue une requête POST HTTP pour obtenir la page de produits.
  return this.httpClient.post<Page>(`${wsService.produit.searchProduit}?page=${currentPage}&size=${size}`, filters);
}

getProduitByID(produitID: Number): Observable<Produit> {
  this.setLoadingStatus(true);
  return this.httpClient.get<Produit>(`${wsService.produit.produitByID}${produitID}`).pipe(
    delay(2000),
    tap(produit => {
     this.setLoadingStatus(false);
    }),
    catchError( error => {
       // Affiche un message d'erreur dans la console.
       console.error('Une erreur réseau s\'est produite :', error);
       this.setLoadingStatus(false);
      return of({});
    })
  );
}

/**
 * Détermine si le chargement de la page suivante est autorisé en fonction de plusieurs critères.
 *
 * @param size La taille de la page à charger.
 * @returns True si le chargement est autorisé, sinon false.
 */
private autoriseLoadingNextPage(currentPage : Page, size: number, isLoading : boolean | undefined): boolean {
  // Obtient l'état actuel du chargement à partir de l'observable _loading$.


    // Obtient l'état actuel de la dernière page à partir de l'observable _page$.
    // Si last_page est indéfini, on utilise false comme valeur par défaut.
    const last_page: boolean = currentPage?.last || false;
  
    // Autorise le chargement de la page suivante si la taille est supérieure à 0, aucun chargement en cours et ce n'est pas la dernière page.
    //return applyFilter || (size > 0 && !loading && !last_page);
    return (size > 0 && !isLoading && !last_page);
}

/**
 * Met à jour l'observable _page$ en fonction des informations de la page actuelle et du numéro de page.
 *
 * @param page Les informations de la page à ajouter ou mettre à jour.
 * @param nbPage Le numéro de la page à traiter.
 */
private setProduis(page: Page, nbPage: number): void {
  // Si nbPage est 0, remplace complètement le contenu de _page$ avec les informations de la première page.
  if (nbPage === 0) {
    this._page$.next(page);
  }
  // Si nbPage est supérieur à 0, fusionne le contenu actuel avec le nouveau contenu de la page.
  else if (nbPage > 0) {
    // Obtient le contenu actuel de _page$. Si le contenu est nul, utilise un tableau vide.
    const currentContent: Produit[] = this._page$.getValue()?.content || [];

    // Fusionne le contenu actuel avec le nouveau contenu de la page.
    const newContent: Produit[] = [...currentContent, ...(page?.content || [])];

    // Crée une nouvelle page en copiant les informations de la page actuelle et en remplaçant le contenu par le nouveau contenu.
    const newPage: Page = { ...page, content: newContent };

    // Met à jour _page$ avec la nouvelle page.
    this._page$.next(newPage);
  }
}
/**
 * Obtient le numéro de la page suivante en fonction des informations de la page actuelle.
 * Si la page ou le numéro de page est indéfini, retourne la valeur par défaut spécifiée.
 *
 * @returns Le numéro de la page suivante.
 */
private getNbPage(currentPage : Page, applyFilter: boolean | null = false): number {
  // Si applyFilter est vrai, retourne 0.
  if (applyFilter) {
    return 0;
  }


  // Utilise l'opérateur de coalescence nulle (??) pour fournir une valeur par défaut si page ou page.number est nul ou indéfini.
  // La valeur par défaut est -1, ce qui peut indiquer qu'aucun numéro de page valide n'est disponible.
  return (currentPage?.number ?? -1) + 1;
}

/**
 * Met à jour l'observable _loading$ pour indiquer l'état de chargement actuel.
 *
 * @param loading Indique si le chargement est en cours (true) ou non (false).
 */
private setLoadingStatus(loading: boolean): void {
  // Met à jour l'observable _loading$ avec la nouvelle valeur représentant l'état de chargement actuel.
  this._loading$.next(loading);
}

getAllProduit(): Observable<Product[]>{

    return of([
        {id: 1, name: 'S12', price: 5026},
        {id: 2, name: 'Iphone 15', price: 1588},
        {id: 3, name: 'IPHONE', price: 1200}
    ])

}


}