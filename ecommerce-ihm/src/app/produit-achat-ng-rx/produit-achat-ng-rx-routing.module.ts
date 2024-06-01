import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ListProduitComponent } from './components/list-produit/list-produit.component';

const routes: Routes = [
  {path: 'main', children: [
    {path:'list', component: ListProduitComponent},
    {path:'', pathMatch: 'full', redirectTo: 'list'}
  ]},
  {path:'', pathMatch: 'full', redirectTo: 'main'}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ProduitAchatNgRxRoutingModule { }
