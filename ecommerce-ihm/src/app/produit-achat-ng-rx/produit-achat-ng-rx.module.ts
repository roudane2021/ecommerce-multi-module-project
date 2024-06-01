import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ProduitAchatNgRxRoutingModule } from './produit-achat-ng-rx-routing.module';
import { MainComponent } from './components/main/main.component';
import { ListProduitComponent } from './components/list-produit/list-produit.component';
import { ProduitItemComponent } from './components/list-produit/produit-item/produit-item.component';
import { CoreModule } from '../core/core.module';
import { StoreFeatureModule, StoreModule } from '@ngrx/store';

import { EffectsModule } from '@ngrx/effects';

import { productFeatureKey, productReducer } from './ng-RX/reducers/product.reducer';
import { ProduitService } from './service/produit.service';
import { StoreDevtoolsModule } from '@ngrx/store-devtools';
import { environment } from 'src/environments/environment';
import * as productEffect from './ng-RX/effects/product.effect';



@NgModule({
  declarations: [
    MainComponent,
    ListProduitComponent,
    ProduitItemComponent
  ],
  imports: [
    CommonModule,
    ProduitAchatNgRxRoutingModule,
    CoreModule,
    StoreModule.forFeature(productFeatureKey , productReducer),
    EffectsModule.forFeature(productEffect),
    StoreDevtoolsModule.instrument({maxAge: 25, logOnly: environment.production})
  ],
  providers: [
    ProduitService
  ]
})
export class ProduitAchatNgRxModule { }
