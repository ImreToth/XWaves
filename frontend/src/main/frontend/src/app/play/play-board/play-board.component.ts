import { Component, OnInit } from '@angular/core';
import {Turn} from '../../_models/Turn';
import {TurnService} from '../../_services/turn.service';
import {Monster} from '../../_models/Monster';
import {Hero} from '../../_models/Hero';
import 'rxjs/add/operator/finally';
import {Router} from '@angular/router';
import {ValidStepService} from "../../_services/validStep.service";

@Component({
  selector: 'app-play-board',
  templateUrl: './play-board.component.html',
  styleUrls: ['./play-board.component.css']
})
export class PlayBoardComponent implements OnInit {
  array: number[];
  yourHero: Hero;
  N = 100; // mezők száma
  turn: Turn;
  monsters = [new Monster()];
  heroes = [new Hero()];
  gamename: string;
  boardMatrix= [ new Monster() ];
  placeholder: Monster;
  cross: Monster;
  target: number;
  constructor(private turnService: TurnService, private router: Router, private validStep: ValidStepService) {
    this.placeholder = {name: '', attack: 0, attacktype: '', cost: 0, defense: 0, health: 0, speed: 0, stamina: 0, path: '', position: 0};
    this.cross = {name: '', attack: 0, attacktype: '', cost: 0, defense: 0, health: 0, speed: 0, stamina: 0, path: '/cross.png', position: 0};

    for (let i = 0; i < 100; i++) {
      this.boardMatrix[i] = this.placeholder;
    }
    this.array = Array.apply(null, {length: this.N}).map(Number.call, Number); // mezőgenerátor

    this.turn = {'heroes': [], 'monsters': []};
    this.gamename = turnService.getGameName();
    this.startTurn();

   // console.log(this.turn.heroes);
   // console.log(this.gamename);
  }

  ngOnInit() {
  }
  startTurn(): void {
    this.turnService.startTurn(this.turnService.getGameName())
      .finally(() => { console.log('finally!'); this.makeThePlace(); })
      .subscribe(
        result => {this.heroes = result.heroes; this.monsters = result.monsters; },
        error => console.log('Error :: ' + error),
        () => console.log('comp')
      );
  }
  makeThePlace() {
    this.insertMonsters(this.monsters);
    this.insertHeroes(this.heroes);
    this.selectYourHero(this.heroes);
  }

  insertMonsters(monsters: Monster[]) {
    const monstersSize = monsters.length;
    for (let i = 0; i < monstersSize; i++) {
      this.boardMatrix[monsters[i].position] = monsters[i];
    }

  }
  insertHeroes(heroes: Hero[]) {
    this.boardMatrix[heroes[0].position] = {name: '', attack: 0, attacktype: '', cost: 0, defense: 0, health: 0, speed: 0, stamina: 0, path: heroes[0].path, position: heroes[0].position};
    this.boardMatrix[heroes[1].position] = {name: '', attack: 0, attacktype: '', cost: 0, defense: 0, health: 0, speed: 0, stamina: 0, path: heroes[1].path, position: heroes[1].position};
    this.boardMatrix[heroes[2].position] = {name: '', attack: 0, attacktype: '', cost: 0, defense: 0, health: 0, speed: 0, stamina: 0, path: heroes[2].path, position: heroes[2].position};
  }
  selectYourHero(heroes: Hero[]) {
    const username = JSON.parse(localStorage.getItem('currentUser')).username;
    if (heroes[0].username === username) {this.yourHero = heroes[0];
    } else if (heroes[1].username === username) {this.yourHero = heroes[1];
    } else {this.yourHero = heroes[2]; }

  }

  targetClick(i: number) {
    this.boardMatrix[this.target] = this.placeholder;
    this.makeThePlace();
    if (this.validStep.validStep(i, this.yourHero, this.boardMatrix)) {this.target = i;
    this.boardMatrix[i] = this.cross; }
  }
  endTurn() {
    const username = JSON.parse(localStorage.getItem('currentUser')).username;
    this.turnService.endTurn(username, this.gamename, this.target);
    this.router.navigate(['rules']);
  }

}
