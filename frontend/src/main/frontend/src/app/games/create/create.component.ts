import { Component, OnInit } from '@angular/core';
import {CyclopaediaService} from '../../_services/cyclopaedia.service';
import {Monster} from '../../_models/Monster';
import {GamesService} from '../../_services/games.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-create',
  templateUrl: './create.component.html',
  styleUrls: ['./create.component.css']
})
export class CreateComponent implements OnInit {
  boardName: string;
  bank = 150;
  target: Monster;
  placeholder: Monster;
  bog: Monster;
  lava:  Monster;
  rock:  Monster;
  stoneWall:  Monster;
  horizontalWall:  Monster;
  verticalWall:  Monster;
  water:  Monster;
  monsters: Monster[];
  array: number[];
  N = 100; // mezők száma
  targetIndex: number;
  createMatrix= [ new Monster() ];
  constructor(private cyclopediaService: CyclopaediaService, private gameService: GamesService,
              private router: Router) {
  }

  ngOnInit() {
    this.placeholder = {name: '', attack: 0, attacktype: '', cost: 0, defense: 0, health: 0, speed: 0, stamina: 0,  position: 0, path: ''};
    this.bog = {name: 'bog', attack: 0, attacktype: '', cost: 0, defense: 0, health: 0, speed: 0, stamina: 0,  position: 0, path: '/place/Bog.png'};
    this.lava = {name: 'lava', attack: 0, attacktype: '', cost: 0, defense: 0, health: 0, speed: 0, stamina: 0, position: 0, path: '/place/Lava.png'};
    this.rock = {name: 'rock', attack: 0, attacktype: '', cost: 0, defense: 0, health: 0, speed: 0, stamina: 0,  position: 0, path: '/place/Rock.png'};
    this.stoneWall = {name: 'stoneWall', attack: 0, attacktype: '', cost: 0, defense: 0, health: 0, speed: 0, stamina: 0,  position: 0, path: '/place/Wall.png'};
    this.horizontalWall = {name: 'horizontalWall', attack: 0, attacktype: '', cost: 0, defense: 0, health: 0, speed: 0, position: 0, stamina: 0, path: '/place/WallBrickHorizontal.png'};
    this.verticalWall = {name: 'verticalWall', attack: 0, attacktype: '', cost: 0, defense: 0, health: 0, speed: 0, stamina: 0, position: 0, path: '/place/WallBrickVertical.png'};
    this.water = {name: 'water', attack: 0, attacktype: '', cost: 0, defense: 0, health: 0, speed: 0, stamina: 0, position: 0, path: '/place/Water.png'};
    this.monsters = this.cyclopediaService.getMonsters();
    this.target = this.monsters[0];
    for (let i = 0; i < 100; i++) {
      this.createMatrix[i] = this.placeholder;
    }
    this.array = Array.apply(null, {length: this.N}).map(Number.call, Number); // mezőgenerátor
  }

  targetClick(num: number) {
    if (num !== 90 && num !== 91 && num !== 92) {
    if (this.createMatrix[num] !== this.placeholder ) {
      this.bank += this.createMatrix[num].cost;
      this.createMatrix[num] = this.placeholder;
    }
    if (this.bank - this.target.cost >= 0 && this.createMatrix[num] === this.placeholder) {
    console.log(num);
    this.bank -= this.target.cost;
    this.targetIndex = num;
    this.createMatrix[this.targetIndex] = this.target;
    console.log(this.createMatrix); } else {console.log('no money haver'); }}
  }
  targetDbClick(num: number) {
    this.bank += this.createMatrix[num].cost;
    this.createMatrix[num] = this.placeholder;
  }
  createGameBoard() {
    console.log(JSON.parse(localStorage.getItem('currentUser')).username);
    this.gameService.sendGameBoard(JSON.parse(localStorage.getItem('currentUser')).username, this.boardName, this.createMatrix);
    this.gameService.refreshGames();
    this.router.navigate(['games/search']);
    this.gameService.refreshGames();
  }
}
