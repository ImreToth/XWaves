import { Injectable } from '@angular/core';
import {Http} from '@angular/http';
import {HttpClient } from '@angular/common/http';
import {Hero} from '../_models/Hero';
import {Monster} from '../_models/Monster';
import {Item} from '../_models/Item';

@Injectable()
export class CyclopaediaService {
  heroes: Hero[];
  monsters: Monster[];
  items: Item[];

  constructor(private http: HttpClient) {
    this.http.get('/api/heroes').subscribe(data => {

      this.heroes = data['heroes'];
    });

    this.http.get('/api/monsters').subscribe(data => {

      this.monsters = data['monsters'];
    });

    this.http.get('/api/items').subscribe(data => {

      this.items = data['items'];
    });
  }
  getHeroes() {
    return this.heroes;
  }
  getMonsters() {
    return this.monsters;
  }
  getItems() {
    return this.items;
  }
}
