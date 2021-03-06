import { Component, OnInit } from '@angular/core';
import {GamesService} from '../_services/games.service';

@Component({
  selector: 'app-games',
  templateUrl: './games.component.html',
  styleUrls: ['./games.component.css']
})
export class GamesComponent implements OnInit {
  isActive: boolean;
  constructor(private gamesService: GamesService) { }

  ngOnInit() {
    this.gamesService.getGames();
  }
btnActive() {
    this.isActive = !this.isActive;
}
}
