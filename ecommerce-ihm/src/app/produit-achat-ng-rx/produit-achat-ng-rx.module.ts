import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ProduitAchatNgRxRoutingModule } from './produit-achat-ng-rx-routing.module';

import { CoreModule } from '../core/core.module';
import { StoreFeatureModule, StoreModule } from '@ngrx/store';

import { EffectsModule } from '@ngrx/effects';


import { ProduitService } from './services/produit.service';
import { StoreDevtoolsModule } from '@ngrx/store-devtools';
import { environment } from 'src/environments/environment';
import * as productEffect from './ng-RX/effects/product.effect';
import { productFeatureKey, reducers } from './ng-RX/reducers';
import { MainComponent } from './components/main/main.component';
import { ListProduitComponent } from './components/list-produit/list-produit.component';
import { ProduitItemComponent } from './components/produit-item/produit-item.component';
import { ItemListProduitComponent } from './components/list-produit/item-list-produit/item-list-produit.component';
import { FilterProduitComponent } from './components/list-produit/filter-produit/filter-produit.component';
import { EnregistreCommandeComponent } from './components/enregistre-commande/enregistre-commande.component';
import { LineCommandeComponent } from './components/enregistre-commande/line-commande/line-commande.component';
import { CommandeService } from './services/commande.service';
import { ParentComponent } from './components/test/parent/parent.component';
import { ChildComponent } from './components/test/child/child.component';



@NgModule({
  declarations: [
    ListProduitComponent, MainComponent, ProduitItemComponent, ItemListProduitComponent, 
    FilterProduitComponent, EnregistreCommandeComponent, LineCommandeComponent, ParentComponent, ChildComponent]
,
  imports: [
    CommonModule,
    ProduitAchatNgRxRoutingModule,
    CoreModule,
    StoreModule.forFeature(productFeatureKey , reducers),
    EffectsModule.forFeature(productEffect),
    StoreDevtoolsModule.instrument({maxAge: 25, logOnly: environment.production})
  ],
  providers: [
    ProduitService,
    CommandeService
  ]
})
export class ProduitAchatNgRxModule { }
