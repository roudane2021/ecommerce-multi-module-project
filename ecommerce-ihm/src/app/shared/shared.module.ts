import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MaterialModule } from './material/material/material.module';
import { ReactiveFormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { HttpClientModule } from '@angular/common/http';
import { FlexLayoutModule } from '@angular/flex-layout';
import { InfiniteScrollModule } from 'ngx-infinite-scroll';
import { PrixValidDirective } from './directives/prix-valid.directive';
import { ShortenPipe } from './pipes/shorten.pipe';
import { ShortenDirective } from './directives/shorten.directive';
import { SelectedDirective } from './directives/selected.directive';
import { SelectElementComponent } from './components/select-elements/select-element/select-element.component';
import { SelectElementsComponent } from './components/select-elements/select-elements.component';
import { AllElementsComponent } from './components/select-elements/all-elements/all-elements.component';
import { TableComponent } from './components/table/table.component';




@NgModule({
  declarations: [
    PrixValidDirective,
    ShortenPipe,
    ShortenDirective,
    SelectedDirective,
    SelectElementComponent,
    SelectElementsComponent,
    AllElementsComponent,
    TableComponent
  ],
  imports: [
    CommonModule,
    MaterialModule,
    FlexLayoutModule,
    InfiniteScrollModule
  ],
  exports: [
    MaterialModule,
    ReactiveFormsModule,
    RouterModule,
    HttpClientModule,
    FlexLayoutModule,
    InfiniteScrollModule,
    PrixValidDirective,
    ShortenPipe,
    ShortenDirective,
    SelectedDirective,
    SelectElementComponent,
    SelectElementsComponent,
    AllElementsComponent,
    TableComponent
  ]
})
export class SharedModule { }
