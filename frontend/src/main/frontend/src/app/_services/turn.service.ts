import { Injectable } from '@angular/core';
import {Http} from '@angular/http';
import {Monster} from '../_models/Monster';
import {Hero} from '../_models/Hero';
import {Observable} from 'rxjs/Observable';
import 'rxjs/add/operator/map';
import { Response } from '@angular/http';
import 'rxjs/add/operator/catch';
import {Turn} from '../_models/Turn';
import {Router} from '@angular/router';

@Injectable()
export class TurnService  {
  monsters: Monster[];
  heroes: Hero[];
  gameName: string;
  constructor(private http: Http, private router: Router) {}

  startTurn(gamename): Observable<Turn> {
    return this.http
      .post('/api/play/start', {'gamename' : gamename})
      .map((response: Response) => {
        return <Turn>response.json();
      })
      .catch(this.handleError);
  }

  endTurn(username: string, gamename: string, action: number ) {
    return this.http
      .post('/api/play/end', {'username' : username, 'gamename' : gamename, 'action' : action})
      .subscribe(suc => {
          if (suc.text() === 'WINNER') {this.router.navigate(['play/winner']);
          } else if (suc.text() === 'LOOSER') {this.router.navigate(['play/looser']);
          }else {this.router.navigate(['rules']); }
          return true;
        },
        err => {
          return false;
        });
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
