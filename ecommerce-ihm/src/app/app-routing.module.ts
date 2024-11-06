import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

const routes: Routes = [
  {path: 'produit', loadChildren: () => import('./produit-achat/produit-achat.module').then(m => m.ProduitAchatModule)},
  {path: 'produit-ng-rx', loadChildren: () => import('./produit-achat-ng-rx/produit-achat-ng-rx.module').then(m => m.ProduitAchatNgRxModule)},
  { path: 'commande', loadChildren: () => import('./gestion-commande/gestion-commande.module').then(m => m.GestionCommandeModule) },
  {path: 'commande-v2', loadChildren: () => import('./gestion-commande-v2/gestion-commande-v2.module').then(m => m.GestionCommandeV2Module)},
  {path: '**', redirectTo: 'produit-ng-rx'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
