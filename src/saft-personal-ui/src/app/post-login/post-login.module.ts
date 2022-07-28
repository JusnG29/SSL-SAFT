import { IonicModule } from '@ionic/angular';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { PostLoginRoutingModule } from './post-login-routing.module';

import { PostLoginPage } from './post-login.page';
import { SharedModule } from '../shared/shared.module';

@NgModule({
  imports: [
    IonicModule,
    CommonModule,
    FormsModule,
    PostLoginRoutingModule,
    SharedModule,
  ],
  declarations: [PostLoginPage],
})
export class PostLoginModule {}
