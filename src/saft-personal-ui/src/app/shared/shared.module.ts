import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatCardModule } from '@angular/material/card';
import { ReactiveFormsModule } from '@angular/forms';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { EnterPinComponent } from './components/enter-pin/enter-pin.component';
import { MatButtonModule } from '@angular/material/button';
import { LogoutComponent } from './components/logout/logout.component';

@NgModule({
  declarations: [EnterPinComponent, LogoutComponent],
  imports: [
    CommonModule,
    MatCardModule,
    ReactiveFormsModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
  ],
  exports: [MatCardModule, LogoutComponent],
})
export class SharedModule {}
