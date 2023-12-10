import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CoreModule } from '../core/core.module';
import { ListProduitComponent } from './components/list-produit/list-produit.component';
import { ProduitAchatRoutingModule } from './produit-achat-routing.module';
import { MainComponent } from './components/main/main.component';
import { ProduitService } from './services/produit.service';
import { ProduitItemComponent } from './components/produit-item/produit-item.component';


@NgModule({
  declarations: [ListProduitComponent, MainComponent, ProduitItemComponent],
  imports: [
    CommonModule,
    ProduitAchatRoutingModule,
    CoreModule
  ],
  providers: [ProduitService]
})
export class ProduitAchatModule { }
