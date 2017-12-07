import { Component, OnInit } from '@angular/core';
import {GamesService} from '../../_services/games.service';
import {Game} from '../../_models/Game';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit {
  games: Game[];
  constructor(private gameService: GamesService) {
  }

  ngOnInit() {
    this.gameService.refreshGames();
    this.games = this.gameService.getGames();
  }

  connectGame(gameName: string) {
    console.log(gameName);
  }
}
