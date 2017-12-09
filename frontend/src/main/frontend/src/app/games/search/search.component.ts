import { Component, OnInit } from '@angular/core';
import {GamesService} from '../../_services/games.service';
import {Game} from '../../_models/Game';
import {Router} from '@angular/router';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit {
  games: Game[];
  constructor(private gameService: GamesService, private router: Router) {
  }

  ngOnInit() {
    this.gameService.refreshGames();
    this.games = this.gameService.getGames();
  }

  connectGame(gameName: string) {
    console.log(gameName);
    this.gameService.setCreateGameName(gameName);
  }
}
