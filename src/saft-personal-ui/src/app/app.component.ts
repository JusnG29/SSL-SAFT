import { Component, OnInit } from '@angular/core';
import { StorageService } from './shared/services/storage.service';
import { registerLocaleData } from '@angular/common';
import localeDe from '@angular/common/locales/de';
import localeDeExtra from '@angular/common/locales/extra/de';

@Component({
  selector: 'app-root',
  templateUrl: 'app.component.html',
  styleUrls: ['app.component.scss'],
})
export class AppComponent implements OnInit {
  constructor(private readonly storageService: StorageService) {}

  async ngOnInit() {
    registerLocaleData(localeDe, 'de-DE', localeDeExtra);
    await this.storageService.initialize();
  }
}
