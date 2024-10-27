import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

const routes: Routes = [
  {path: 'produit', loadChildren: () => import('./produit-achat/produit-achat.module').then(m => m.ProduitAchatModule)},
  {path: 'produit-ng-rx', loadChildren: () => import('./produit-achat-ng-rx/produit-achat-ng-rx.module').then(m => m.ProduitAchatNgRxModule)},
  {path: 'commande', loadChildren: () => import('./gestion-commande/gestion-commande.module').then(m => m.GestionCommandeModule)},
  {path: '**', redirectTo: 'commande'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
