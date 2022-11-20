import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatCardModule } from '@angular/material/card';
import { ReactiveFormsModule } from '@angular/forms';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { EnterPinComponent } from './components/enter-pin/enter-pin.component';
import { MatButtonModule } from '@angular/material/button';
import { LogoutComponent } from './components/logout/logout.component';
import { IonicModule } from '@ionic/angular';
import { DefaultHeaderComponent } from './components/default-header/default-header.component';
import { HistoryBaseComponent } from './components/history-base/history-base.component';

@NgModule({
  declarations: [
    EnterPinComponent,
    LogoutComponent,
    DefaultHeaderComponent,
    HistoryBaseComponent,
  ],
  imports: [
    CommonModule,
    MatCardModule,
    ReactiveFormsModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    IonicModule.forRoot(),
  ],
  exports: [
    MatCardModule,
    LogoutComponent,
    DefaultHeaderComponent,
    HistoryBaseComponent,
  ],
})
export class SharedModule {}
