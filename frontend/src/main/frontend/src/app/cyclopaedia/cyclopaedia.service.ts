import { Injectable } from '@angular/core';
import {Http} from '@angular/http';
import {HttpClient } from '@angular/common/http';
import {Hero} from '../Hero';
import {Monster} from '../Monster';
import {Item} from '../Item';

@Injectable()
export class CyclopaediaService {
  heroes: Hero[];
  monsters: Monster[];
  items: Item[];
  kappa = 'kappa';

  constructor(private http: HttpClient) {
    this.http.get('/api/heroes').subscribe(data => {

      this.heroes = data['heroes'];
      console.log(this.heroes[1].name);
    });

    this.http.get('/api/monsters').subscribe(data => {

      this.monsters = data['monsters'];
      console.log(this.monsters[1].name);
    });

    this.http.get('/api/items').subscribe(data => {

      this.items = data['items'];
      console.log(this.items[1].name);
    });
  }
  getHeroes() {
    return this.heroes;
  }
}
