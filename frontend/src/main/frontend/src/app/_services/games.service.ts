import { Injectable } from '@angular/core';
import {Http} from '@angular/http';
import {Game} from '../_models/Game';

@Injectable()
export class GamesService {
  games: Game[];
  constructor(private http: Http) {
    this.http.get('/api/games/search').subscribe(data => {

      this.games = data['games'];
    });
  }

  getGames() {
    return this.games;
  }

}
