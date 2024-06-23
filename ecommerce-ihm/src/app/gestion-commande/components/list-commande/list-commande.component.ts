import { ChangeDetectionStrategy, Component, OnDestroy, OnInit, inject } from '@angular/core';
import { CommandeService } from '../../services/commende.service';
import { Observable, Subject, takeUntil, tap } from 'rxjs';
import { Store } from '@ngrx/store';
import * as appState from '../../ng-rx/state/state';
import * as commandeAction from '../../ng-rx/actions/commande.action';
import * as commandeSelector from '../../ng-rx/selectors/commande.selector';
import { Page } from '../../../shared/models/page.model';
import { Commande } from '../../models/commande.model';
import { PageEvent } from '@angular/material/paginator';
import { EventType, EventUser } from '../../../shared/models/event.model';

@Component({
  selector: 'app-list-commande',
  templateUrl: './list-commande.component.html',
  styleUrls: ['./list-commande.component.scss'],
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class ListCommandeComponent implements OnInit, OnDestroy{



  private readonly _commande: CommandeService = inject(CommandeService);
  private readonly store: Store<any> = inject(Store<appState.FeatureState>);


  private readonly _unSubscriber: Subject<void> = new Subject<void>;
  page$!: Observable<Page<Commande>>;
  loading$!: Observable<boolean | undefined>;

  

  ngOnInit(): void {

    this.initObsr();
  
    this.store.dispatch(commandeAction.CommandesActions.commandeActionPaginationStart({ page: 0 }));
  }

  initObsr = (): void => {
    this.page$ = this.store.select(commandeSelector.pageCommandeSelector).pipe(
      takeUntil(this._unSubscriber),
      tap(page => console.table(page))
    
    );
    this.loading$ = this.store.select(commandeSelector.apiSelector).pipe(
      takeUntil(this._unSubscriber));
  }

  loadNextPage(pageEvent: PageEvent) {
    const page = pageEvent?.pageIndex;
    console.log("page suivant :"+page)
    this.store.dispatch(commandeAction.CommandesActions.commandeActionPaginationStart({ page }));
  }
  
  receiveEvent = (event: EventUser): void  => {
    console.log("receive ", event); 

    switch (event.type) {
      case EventType.DELETE :
        this.deleteElement(event.element);
        break;
      case EventType.UPDATE:
        console.log('Update !!!!');
        break;
    }
  }
  
  deleteElement = (element: Commande): void => {
    
  }

  ngOnDestroy(): void {
    this._unSubscriber.next();
    this._unSubscriber.complete();
  }

}


