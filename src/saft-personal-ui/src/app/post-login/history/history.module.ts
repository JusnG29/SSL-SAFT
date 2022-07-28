import { IonicModule } from '@ionic/angular';
import { RouterModule } from '@angular/router';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { HistoryPage } from './history.page';

import { HistoryRoutingModule } from './history-routing.module';

@NgModule({
  imports: [IonicModule, CommonModule, FormsModule, HistoryRoutingModule],
  declarations: [HistoryPage],
})
export class HistoryModule {}
