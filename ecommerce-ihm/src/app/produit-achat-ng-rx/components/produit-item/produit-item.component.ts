import { ChangeDetectionStrategy, Component, OnDestroy, OnInit, inject } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Produit } from '../../models/product.model';
import { Observable, Subject, catchError, map, of, switchMap, take, takeUntil, tap } from 'rxjs';
import { ProduitService } from '../../services/produit.service';

@Component({
  selector: 'app-produit-item',
  templateUrl: './produit-item.component.html',
  styleUrls: ['./produit-item.component.scss'],
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class ProduitItemComponent implements OnInit, OnDestroy{


  private router: Router = inject(Router);
  private route: ActivatedRoute = inject(ActivatedRoute);
  private produitService: ProduitService = inject(ProduitService);
  produit$!: Observable<Produit>;
  loading$!: Observable<boolean>;
  private unsubscribe$ : Subject<void> = new Subject<void>();
  

  ngOnInit(): void {
    this.init();
  }

  private init = (): void => {
    this.unsubscribe$.next();
    this.loading$ = this.produitService.loading$.pipe(takeUntil(this.unsubscribe$));
    this.produit$ = this.route.params.pipe(
      takeUntil(this.unsubscribe$),
      map(params => +params['id']),
      take(1),
      switchMap(id => this.produitService.getProduitByID(id)),
      tap(produit => {
        if (!produit.id) {
         this.onGoBack();
        }
      })
      
    );
  }

  onGoBack() {
    this.router.navigate(['../'], { relativeTo: this.route });
    }

    ngOnDestroy(): void {
      this.unsubscribe$.complete();
    }

}
