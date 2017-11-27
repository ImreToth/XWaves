import {Component, Input, OnInit} from '@angular/core';
import {Monster} from '../../../Monster';
@Component({
  selector: 'app-monster',
  templateUrl: './monster.component.html',
  styleUrls: ['./monster.component.css']
})
export class MonsterComponent implements OnInit {
  @Input() monster: Monster;
  constructor() { }

  ngOnInit() {
  }

}
