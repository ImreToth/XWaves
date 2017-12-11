import { Component, OnInit } from '@angular/core';
import {Turn} from '../../_models/Turn';
import {TurnService} from '../../_services/turn.service';
import {Monster} from '../../_models/Monster';
import {Hero} from '../../_models/Hero';

@Component({
  selector: 'app-play-board',
  templateUrl: './play-board.component.html',
  styleUrls: ['./play-board.component.css']
})
export class PlayBoardComponent implements OnInit {
  array: number[];
  N = 100; // mezők száma
  turn: Turn;
  monsters = [new Monster()];
  heroes = [new Hero()];
  gamename: string;
  constructor(private turnService: TurnService) {
    this.turn = {'heroes': [], 'monsters': []};
    this.gamename = turnService.getGameName();
    this.startTurn();
    this.array = Array.apply(null, {length: this.N}).map(Number.call, Number); // mezőgenerátor
    console.log(this.turn.monsters);
   // console.log(this.turn.heroes);
   // console.log(this.gamename);
  }

  ngOnInit() {
  }
  startTurn(): void {
    this.turnService.startTurn(this.gamename)
      .subscribe(
        result => this.heroes = result.heroes,
        error => console.log('Error :: ' + error)
      );
    console.log(this.heroes[0]);
  }

}
