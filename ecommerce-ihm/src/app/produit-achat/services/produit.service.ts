import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable, catchError, of, pipe, tap } from 'rxjs';
import { wsService } from 'src/app/shared/url-ws/url.ws';
import { Page } from '../models/produit.model';

@Injectable()
export class ProduitService {

  private _loading$: BehaviorSubject<boolean> = new BehaviorSubject<boolean>(false);
  private _page$: BehaviorSubject<Page> = new BehaviorSubject<Page>({} as Page);

  constructor(private httpClient: HttpClient) { }

  get loading$(): Observable<boolean> {
    return this._loading$.asObservable();
  }
  get page$(): Observable<Page>{
    return this._page$.asObservable();
  }

  getPageProduit(): void {
    this.setLoadingStatus(true);
    this.httpClient.post<Page>(`${wsService.produit.searchProduit}`, []).pipe(
      tap((page: Page) => {
        this.setLoadingStatus(false);
        this.setProduis(page);
      }),
      catchError(error => {
        this.setLoadingStatus(false);
        console.error('Une erreur réseau s\'est produite :', error);
        return of({});
      })
    ).subscribe();
  }

  private setLoadingStatus (loading:boolean) {
    this._loading$.next(loading);
}

private setProduis (page: Page) {
  this._page$.next(page);
}

}
