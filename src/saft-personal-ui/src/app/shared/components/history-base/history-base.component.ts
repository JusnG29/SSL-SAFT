import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-history-base',
  templateUrl: './history-base.component.html',
  styleUrls: ['./history-base.component.scss'],
})
export class HistoryBaseComponent implements OnInit {
  @Input() badgeDescription: String = 'Bist du durstig?';

  constructor() {}

  ngOnInit() {}
}
