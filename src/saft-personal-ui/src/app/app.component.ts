import { Component, OnInit } from '@angular/core';
import { StorageService } from './shared/services/storage.service';

@Component({
  selector: 'app-root',
  templateUrl: 'app.component.html',
  styleUrls: ['app.component.scss'],
})
export class AppComponent implements OnInit {
  constructor(private readonly storageService: StorageService) {}

  async ngOnInit() {
    await this.storageService.initialize();
  }
}
