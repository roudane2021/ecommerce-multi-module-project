import { Component, OnDestroy, OnInit } from '@angular/core';
import { ProduitService } from '../../services/produit.service';
import { Observable, Subject, takeUntil } from 'rxjs';
import { Page } from '../../models/produit.model';

@Component({
  selector: 'app-list-produit',
  templateUrl: './list-produit.component.html',
  styleUrls: ['./list-produit.component.scss']
})
export class ListProduitComponent implements OnInit , OnDestroy{
  loading$!: Observable<boolean>;
  page$!: Observable<Page>;
  private unsubscribe$ : Subject<void> = new Subject<void>();

  constructor(private produitService: ProduitService){}

  ngOnInit(): void {
    this.produitService.getPageProduit();
    this.initObservable();
  }

  initObservable(): void {
    this.loading$ = this.produitService.loading$.pipe(takeUntil(this.unsubscribe$));
    this.page$ = this.produitService.page$.pipe(takeUntil(this.unsubscribe$));

  }

  ngOnDestroy(): void {
    this.unsubscribe$.next();
    this.unsubscribe$.complete();
  }
 

}
