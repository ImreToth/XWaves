import { Component, OnInit } from '@angular/core';
import {GamesService} from '../../_services/games.service';
import {Game} from '../../_models/Game';
import {Router} from '@angular/router';
import {Observable} from 'rxjs/Observable';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit {
  games: Observable<Game[]>;
  constructor(private gameService: GamesService, private router: Router) {
  }

  ngOnInit() {
    this.games = this.gameService.getGames();
    // this.getGames();
    console.log(this.games);
  }

  connectGame(gameName: string) {
    console.log(gameName);
    this.gameService.setCreateGameName(gameName);
  }
  /*getGames(): void {
    this.gameService.getGames()
      .subscribe(
        resultArray => this.games = resultArray,
        error => console.log('Error :: ' + error)
      );
  }*/
}
