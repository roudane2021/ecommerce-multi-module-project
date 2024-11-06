import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CreateCommandeComponent } from './components/create-commande/create-commande.component';

const routes: Routes = [
  {path: '', component: CreateCommandeComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class GestionCommandeV2RoutingModule { }
