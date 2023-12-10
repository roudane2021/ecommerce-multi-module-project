import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ListProduitComponent } from './components/list-produit/list-produit.component';
import { MainComponent } from './components/main/main.component';

const routes: Routes = [
  {path: 'main', component: MainComponent, 
  children: [
    {path: 'listProduit', component: ListProduitComponent},
    {path:'', pathMatch: 'full', redirectTo: 'listProduit'}
  ]
},
{path:'', pathMatch: 'full', redirectTo: 'main'}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ProduitAchatRoutingModule { }
