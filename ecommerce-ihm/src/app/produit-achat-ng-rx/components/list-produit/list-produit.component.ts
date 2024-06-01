import { AfterContentChecked, AfterContentInit, ChangeDetectionStrategy, Component, OnDestroy, OnInit, ViewChild, inject } from '@angular/core';
import { ProduitService } from '../../services/produit.service';
import { Observable, Subject, interval, of, switchMap, takeUntil, timer, withLatestFrom } from 'rxjs';
import { ActionP, Criteria, Page, ResponseEvent } from '../../models/product.model';
import { ActivatedRoute, Router } from '@angular/router';
import { Store } from '@ngrx/store';
import * as selectProduct from '../../ng-RX/reducers/index'
import * as actionProduct from '../../ng-RX/actions/products.action';
import { MatPaginator, PageEvent } from '@angular/material/paginator';

@Component({
  selector: 'app-list-produit',
  templateUrl: './list-produit.component.html',
  styleUrls: ['./list-produit.component.scss'],
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class ListProduitComponent implements OnInit , OnDestroy{


  // Observable
  loading$!: Observable<boolean | undefined>;
  page$!: Observable<Page>;
  private unsubscribe$ : Subject<void> = new Subject<void>();
  // Service 
  private produitService: ProduitService = inject(ProduitService);
  private router: Router = inject(Router);
  private route: ActivatedRoute = inject(ActivatedRoute);
  private store : Store<any> = inject(Store<selectProduct.AppState>);
  @ViewChild(MatPaginator, { static: false }) paginator!: MatPaginator;



  ngOnInit(): void {
    this.loadNextPage() ;
    this.initObservable();
    //this.forTest();
  }


  forTest = (): void => {
    console.log(`****************START TEST*****************`);
    const source$ = interval(1000);
    const other$ = timer(4000, 2000).pipe(switchMap(data => of(`AA ==> ${data}`)));

    source$.pipe(withLatestFrom(other$)).subscribe({
      next: ([data, other]) => console.log(` data : ${data} and other ${other}`)
    });

    console.log(`****************END TEST*****************`);
  }

  initObservable = (): void  => {
    this.loading$ = this.store.select(selectProduct.isLoadingSelector).pipe(takeUntil(this.unsubscribe$));
    this.page$ = this.store.select(selectProduct.listProductSuivant).pipe(takeUntil(this.unsubscribe$));

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

  loadNextPage = (pageEvent : PageEvent | undefined = undefined) => {
    console.log("Next Page ...");
    const page  = pageEvent?.pageIndex;
    const size  = pageEvent?.pageSize;
    console.log(`current Page : ${page} && size : ${size}`);
   this.store.dispatch(actionProduct.List_Products_Start_Action({page, size}));
  }
  onUpdateFilter(filters: Criteria[]) {
     this.produitService.filter$ = filters;
    }
  onApplyFilter(applyFilter: boolean): void {
      this.produitService.applyFilter$ = applyFilter;
      this.loadNextPage();
      this.paginator.pageIndex = 1;
      }
 
  ngOnDestroy(): void {
        this.unsubscribe$.next();
        this.unsubscribe$.complete();
      }
}
