import { Injectable } from '@angular/core';
import {Http} from '@angular/http';
import {Game} from '../_models/Game';
import {HttpClient} from '@angular/common/http';
import {Monster} from '../_models/Monster';
import {Hero} from '../_models/Hero';

@Injectable()
export class GamesService {
  createGameName: string;
  games: Game[];
  myGames: Game[];
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
  setCreateGameName(name: string) {
    this.createGameName = name;
  }
  getCreateGameName() {
    return this.createGameName;
  }
  joinGame(username: string, gamename: string, hero: Hero) {
    this.http.post('/api/games/join' , {'username' : username, 'gamename' : gamename, 'hero' : hero})
      .subscribe(suc => {
          return true;
        },
        err => {
          console.log('nem tudtunk joinolni');
          return false;
        });
  }
  myGamesList(username: string) {
     this.http.post('/api/play/search' , {'username' : username})
      .subscribe(suc => {
          this.myGames = suc['games'];
          console.log(suc);
          return true;
        },
        err => {
          console.log('nem tudom lekerni a sajat jatekaid');
          return false;
        });
  }
  getMyGames() {
    console.log('getMyGames ezt tartalmazza:');
    console.log(this.myGames);
    return this.myGames;
  }
}
