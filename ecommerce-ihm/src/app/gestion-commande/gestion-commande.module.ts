import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { GestionCommandeRoutingModule } from './gestion-commande-routing.module';
import { CreationCommandeComponent } from './components/creation-commande/creation-commande.component';
import { MainComponent } from './components/main/main.component';
import { CoreModule } from '../core/core.module';
import { CommandeService } from './services/commende.service';
import { ProductItemComponent } from './components/creation-commande/product-item/product-item.component';
import { ProductService } from './services/product.service';
import { ListCommandeComponent } from './components/list-commande/list-commande.component';
import { StoreModule } from '@ngrx/store';
import { EffectsModule } from '@ngrx/effects';
import * as commandePaginationEffect from './ng-rx/effects/commandePagination.effect';
import * as commandeCreationEffect from './ng-rx/effects/commandeCreation.effect';
import { commandeFeatureKey, reducers } from './ng-rx/reducers/root.reducer';




@NgModule({
  declarations: [
    MainComponent,
    CreationCommandeComponent,
    ProductItemComponent,
    ListCommandeComponent
  ],
  imports: [
    CommonModule,
    GestionCommandeRoutingModule,
    CoreModule,
    StoreModule.forFeature(commandeFeatureKey, reducers),
    EffectsModule.forFeature(commandeCreationEffect, commandePaginationEffect)
  ],
  providers:[
    CommandeService,
    ProductService
  ]
})
export class GestionCommandeModule { }
