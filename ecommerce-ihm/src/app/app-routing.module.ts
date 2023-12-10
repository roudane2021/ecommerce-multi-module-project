import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

const routes: Routes = [
  {path: 'produit', loadChildren: () => import('./produit-achat/produit-achat.module').then(m => m.ProduitAchatModule)},
  {path: '**', redirectTo: 'produit'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
