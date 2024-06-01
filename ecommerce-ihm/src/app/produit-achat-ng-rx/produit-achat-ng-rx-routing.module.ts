import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ListProduitComponent } from './components/list-produit/list-produit.component';
import { MainComponent } from './components/main/main.component';
import { ProduitItemComponent } from './components/produit-item/produit-item.component';
import { EnregistreCommandeComponent } from './components/enregistre-commande/enregistre-commande.component';

const routes: Routes = [
  {path: 'main', component: MainComponent, 
  children: [
    {path: 'produits', component: ListProduitComponent},
    {path: 'produits/:id', component: ProduitItemComponent},
    {path: 'enregistre-commande', component: EnregistreCommandeComponent},
    {path:'', pathMatch: 'full', redirectTo: 'produits' }
  ]
},
{path:'', pathMatch: 'full', redirectTo: 'main'}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ProduitAchatNgRxRoutingModule { }
