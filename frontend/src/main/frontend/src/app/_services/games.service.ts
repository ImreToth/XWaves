import { Injectable } from '@angular/core';
import {Http} from '@angular/http';
import {Game} from '../_models/Game';
import {HttpClient} from '@angular/common/http';
import {Monster} from '../_models/Monster';

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
  sendGameBoard(username: string, gamename: string, board: Monster[]) {
    this.http.post('/api/games/create' , {'username' : username, 'gamename' : gamename, 'board' : board})
      .subscribe(suc => {
          return true;
        },
        err => {
          console.log('gond van tetya');
          return false;
        });
  }
  refreshGames() {
    this.http.get('/api/games/search').subscribe(data => {

      this.games = data['games'];
    });
  }
}
