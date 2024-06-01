import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { MainComponent } from './components/main/main.component';
import { CreationCommandeComponent } from './components/creation-commande/creation-commande.component';
import { ListCommandeComponent } from './components/list-commande/list-commande.component';

const routes: Routes = [
  {path: 'main', component: MainComponent, children: [
    { path: 'creation-commande', component: CreationCommandeComponent },
    {path: 'list-commande', component: ListCommandeComponent},
    {path: '**', redirectTo: 'creation-commande'},
  ]},
  {path: '**', redirectTo: 'main'}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class GestionCommandeRoutingModule { }
