import { Component, OnInit } from '@angular/core';
import {Monster} from '../../_models/Monster';
import {CyclopaediaService} from '../../_services/cyclopaedia.service';

@Component({
  selector: 'app-monsters',
  templateUrl: './monsters.component.html',
  styleUrls: ['./monsters.component.css']
})
export class MonstersComponent implements OnInit {
  monsters: Monster[];
  constructor(private cyclopediaService: CyclopaediaService) { }

  ngOnInit() {
    this.monsters = this.cyclopediaService.getMonsters();
  }

}
