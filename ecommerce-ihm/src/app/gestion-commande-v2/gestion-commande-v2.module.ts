import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { GestionCommandeV2RoutingModule } from './gestion-commande-v2-routing.module';
import { CreateCommandeComponent } from './components/create-commande/create-commande.component';
import { ProductItemComponent } from './components/product-item/product-item.component';


@NgModule({
  declarations: [
    CreateCommandeComponent,
    ProductItemComponent
  ],
  imports: [
    CommonModule,
    GestionCommandeV2RoutingModule
  ]
})
export class GestionCommandeV2Module { }
