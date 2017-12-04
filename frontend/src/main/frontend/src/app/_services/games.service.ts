import { Injectable } from '@angular/core';
import {Http} from '@angular/http';
import {Game} from '../_models/Game';
import {HttpClient} from '@angular/common/http';

@Injectable()
export class GamesService {
  games: Game[];
  constructor(private http: HttpClient) {
    this.http.get('/api/games/search').subscribe(data => {

      this.games = data['games'];
    });
  }

  getGames() {
    return this.games;
  }
  getGamesSize() {
    return this.games.length;
  }

}
