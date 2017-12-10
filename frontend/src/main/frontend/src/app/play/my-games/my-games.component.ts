import { Component, OnInit } from '@angular/core';
import {GamesService} from '../../_services/games.service';
import {Game} from '../../_models/Game';

@Component({
  selector: 'app-my-games',
  templateUrl: './my-games.component.html',
  styleUrls: ['./my-games.component.css']
})
export class MyGamesComponent implements OnInit {
  username: string;
  myGames: Game[];
  constructor(private gamesService: GamesService) { }

  ngOnInit() {
    this.username = JSON.parse(localStorage.getItem('currentUser')).username;
   this.gamesService.myGamesList(this.username);
   this.myGames = this.gamesService.getMyGames();
   console.log(this.myGames[0].nextPlayer);
  }
  canStart(game: Game) {
    this.myGames = this.gamesService.getMyGames();
    if (game.nextPlayer) {return 'Click to play!'; } else {return 'Wait!'; }
  }
  startGame(game: Game) {
    console.log(game);
  }
}
