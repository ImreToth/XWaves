import { Component, OnInit } from '@angular/core';
import {GamesService} from '../_services/games.service';

@Component({
  selector: 'app-games',
  templateUrl: './games.component.html',
  styleUrls: ['./games.component.css']
})
export class GamesComponent implements OnInit {
  createActive: boolean;
  searchActive: boolean;
  constructor(private gamesService: GamesService) { }

  ngOnInit() {
    this.gamesService.getGames();
    this.createActive = false;
    this.searchActive = true;
  }
btnCreateActive() {
    this.createActive = true;
    this.searchActive = false;
}
  btnSearchActive() {
    this.searchActive = true;
    this.createActive = false;
  }
}
