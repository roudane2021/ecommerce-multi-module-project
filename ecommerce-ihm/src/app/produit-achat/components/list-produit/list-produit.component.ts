import { AfterContentChecked, AfterContentInit, ChangeDetectionStrategy, Component, OnDestroy, OnInit, inject } from '@angular/core';
import { ProduitService } from '../../services/produit.service';
import { Observable, Subject, takeUntil } from 'rxjs';
import { ActionP, Criteria, Produit, ResponseEvent } from '../../models/produit.model';
import { ActivatedRoute, Router } from '@angular/router';
import { Page } from '../../../shared/models/page.model';

@Component({
  selector: 'app-list-produit',
  templateUrl: './list-produit.component.html',
  styleUrls: ['./list-produit.component.scss'],
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class ListProduitComponent implements OnInit , OnDestroy{


  // Observable
  loading$!: Observable<boolean>;
  page$!: Observable<Page<Produit>>;
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
  onUpdateFilter(filters: Criteria[]) {
     this.produitService.filter$ = filters;
    }
  onApplyFilter(applyFilter: boolean): void {
      this.produitService.applyFilter$ = applyFilter;
      this.produitService.getPageProduit();
      }
 
  ngOnDestroy(): void {
        this.unsubscribe$.next();
        this.unsubscribe$.complete();
      }
}
