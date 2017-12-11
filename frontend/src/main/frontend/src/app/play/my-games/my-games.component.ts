import { Component, OnInit } from '@angular/core';
import {GamesService} from '../../_services/games.service';
import {Game} from '../../_models/Game';
import {Router} from '@angular/router';
import {TurnService} from '../../_services/turn.service';
import {Observable} from "rxjs/Observable";

@Component({
  selector: 'app-my-games',
  templateUrl: './my-games.component.html',
  styleUrls: ['./my-games.component.css']
})
export class MyGamesComponent implements OnInit {
  username: string;
  myGames: Observable<Game[]>;
  constructor(private gamesService: GamesService, private router: Router, private turnService: TurnService) { }

  ngOnInit() {
    this.username = JSON.parse(localStorage.getItem('currentUser')).username;
   this.myGames = this.gamesService.myGamesList(this.username);
  }
  canStart(game: Game) {
    // this.myGames = this.gamesService.getMyGames();
    if (game.nextPlayer) {return 'Click to play!'; } else {return 'Wait!'; }
  }
  startGame(game: Game) {
    if (game.nextPlayer) {
      this.turnService.setGameName(game.name);
      this.router.navigate(['play/playboard']); }
  }
  /*getGames(): void {
    this.gamesService.myGamesList(this.username)
      .subscribe(
        resultArray => this.myGames = resultArray,
        error => console.log('Error :: ' + error)
      );
  }*/
}
