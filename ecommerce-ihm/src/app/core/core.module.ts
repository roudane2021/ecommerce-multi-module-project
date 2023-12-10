import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SharedModule } from '../shared/shared.module';
import { HeaderAchatComponent } from './components/header-achat/header-achat.component';



@NgModule({
  declarations: [
    HeaderAchatComponent
  ],
  imports: [
    CommonModule,
    SharedModule
  ],
  exports: [
   HeaderAchatComponent,
    SharedModule
  ]
})
export class CoreModule { }
