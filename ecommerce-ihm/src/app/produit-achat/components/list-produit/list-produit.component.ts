import { AfterViewInit, Component, HostListener, OnDestroy, OnInit, ViewChild, inject } from '@angular/core';
import { ProduitService } from '../../services/produit.service';
import { Observable, Subject, filter, first, takeUntil } from 'rxjs';
import { ActionP, Page, ResponseEvent } from '../../models/produit.model';
import { CdkVirtualScrollViewport, ScrollDispatcher } from '@angular/cdk/scrolling';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-list-produit',
  templateUrl: './list-produit.component.html',
  styleUrls: ['./list-produit.component.scss'],
})
export class ListProduitComponent implements OnInit , OnDestroy{
  // Observable
  loading$!: Observable<boolean>;
  page$!: Observable<Page>;
  private unsubscribe$ : Subject<void> = new Subject<void>();
  // Service 
  private produitService: ProduitService = inject(ProduitService);
  private router: Router = inject(Router);
  private route: ActivatedRoute = inject(ActivatedRoute);

  ngOnInit(): void {
    this.loadNextPage() ;
    this.initObservable();
  }

  initObservable = (): void  => {
    this.loading$ = this.produitService.loading$.pipe(takeUntil(this.unsubscribe$));
    this.page$ = this.produitService.page$.pipe(takeUntil(this.unsubscribe$));

  }
  onApplyAction = (response: ResponseEvent) => {
    console.table(response)
    switch (response.event) {
      case ActionP.DETAILS:
        this.router.navigate([response.idP], { relativeTo: this.route });
        break;
      case ActionP.PANIER:
        break;
    }
    }

  loadNextPage = () => {
    console.log("Next Page ...");
    this.produitService.getPageProduit();
  }

  ngOnDestroy(): void {
    this.unsubscribe$.next();
    this.unsubscribe$.complete();
  }
 

}
