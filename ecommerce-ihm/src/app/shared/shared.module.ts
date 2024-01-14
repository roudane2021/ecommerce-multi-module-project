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




@NgModule({
  declarations: [
    PrixValidDirective,
    ShortenPipe,
    ShortenDirective
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
    ShortenDirective
  ]
})
export class SharedModule { }
