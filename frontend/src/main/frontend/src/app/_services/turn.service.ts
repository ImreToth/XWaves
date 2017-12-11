import { Injectable } from '@angular/core';
import {Http} from '@angular/http';
import {Monster} from '../_models/Monster';
import {Hero} from '../_models/Hero';
import {Observable} from 'rxjs/Observable';
import 'rxjs/add/operator/map';
import { Response } from '@angular/http';
import 'rxjs/add/operator/catch';
import {Turn} from '../_models/Turn';

@Injectable()
export class TurnService  {
  monsters: Monster[];
  heroes: Hero[];
  gameName: string;
  constructor(private http: Http) {}

  startTurn(gamename): Observable<Turn> {
    return this.http
      .post('/api/play/start', {'gamename' : gamename})
      .map((response: Response) => {
        return <Turn>response.json();
      })
      .catch(this.handleError);
  }

  private handleError(error: Response) {
    return Observable.throw(error.statusText);
  }
  setGameName(gameName: string) {
    this.gameName = gameName;
  }
  getGameName() {
    return this.gameName;
  }

}
