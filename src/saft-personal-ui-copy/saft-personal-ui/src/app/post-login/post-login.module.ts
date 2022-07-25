import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { PostLoginRoutingModule } from './post-login-routing.module';
import { BuyPageComponent } from './buy-page/buy-page.component';
import { HistoryPageComponent } from './history-page/history-page.component';
import { PostLoginPageComponent } from './post-login-page/post-login-page.component';


@NgModule({
  declarations: [
    BuyPageComponent,
    HistoryPageComponent,
    PostLoginPageComponent
  ],
  imports: [
    CommonModule,
    PostLoginRoutingModule
  ]
})
export class PostLoginModule { }
