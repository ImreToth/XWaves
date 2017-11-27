import {Component, Input, OnInit} from '@angular/core';
import {Hero} from '../../Hero';
import {CyclopaediaService} from '../cyclopaedia.service';

@Component({
  selector: 'app-heroes',
  templateUrl: './heroes.component.html',
  styleUrls: ['./heroes.component.css']
})
export class HeroesComponent implements OnInit {
  heroes: Hero[];
  constructor(private cyclopediaService: CyclopaediaService) { }

  ngOnInit() {
    this.heroes = this.cyclopediaService.getHeroes();
  }

}
