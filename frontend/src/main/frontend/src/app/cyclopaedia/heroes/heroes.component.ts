import {Component, Input, OnInit} from '@angular/core';
import {Hero} from '../../Hero';

@Component({
  selector: 'app-heroes',
  templateUrl: './heroes.component.html',
  styleUrls: ['./heroes.component.css']
})
export class HeroesComponent implements OnInit {
  kappa = 'kappa';
  @Input() heroesFromCyclopaedia: Hero[];
  constructor() { }

  ngOnInit() {
    console.log('ez itt a heroescomponent');
    console.log(this.heroesFromCyclopaedia[1].name);
  }

}
