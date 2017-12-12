import { Injectable } from '@angular/core';
import {Http} from '@angular/http';
import {Game} from '../_models/Game';
import {Monster} from '../_models/Monster';
import {Hero} from '../_models/Hero';
import {Observable} from 'rxjs/Observable';
import 'rxjs/add/operator/map';
import { Response } from '@angular/http';
import 'rxjs/add/operator/catch';
import {Router} from '@angular/router';

@Injectable()
export class GamesService {
  createGameName: string;
  games: Game[];
  myGames: Game[];
  constructor(private http: Http, private router: Router) {}

  getGames(): Observable<Game[]> {
    return this.http
      .get('/api/games/search')
      .map((response: Response) => {
        return <Game[]>response.json();
      })
      .catch(this.handleError);
  }

  private handleError(error: Response) {
    return Observable.throw(error.statusText);
  }

  sendGameBoard(username: string, gamename: string, board: Monster[]) {
    this.http.post('/api/games/create' , {'username' : username, 'gamename' : gamename, 'board' : board})
      .finally(() => { this.router.navigate(['games/search']); })
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
      .finally(() => { this.router.navigate(['play']); })
      .subscribe(suc => {
          return true;
        },
        err => {
          console.log('nem tudtunk joinolni');
          return false;
        });
  }
  myGamesList(username: string): Observable<Game[]> {
    return this.http.post('/api/play/search' , {'username' : username})
      .map((response: Response) => {
        return <Game[]>response.json();
      })
      .catch(this.handleError);
  }
  getMyGames() {
    console.log('getMyGames ezt tartalmazza:');
    console.log(this.myGames);
    return this.myGames;
  }
}
