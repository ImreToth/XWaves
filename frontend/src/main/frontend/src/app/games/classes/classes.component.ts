import { Component, OnInit } from '@angular/core';
import {CyclopaediaService} from '../../_services/cyclopaedia.service';
import {Hero} from '../../_models/Hero';
import {Router} from '@angular/router';
import {GamesService} from '../../_services/games.service';

@Component({
  selector: 'app-classes',
  templateUrl: './classes.component.html',
  styleUrls: ['./classes.component.css']
})
export class ClassesComponent implements OnInit {
  heroes: Hero[];
  username: string;
  gamename: string;
  constructor(private cyclopediaService: CyclopaediaService, private gameService: GamesService,
  private router: Router) { }

  ngOnInit() {
    this.heroes = this.cyclopediaService.getHeroes();
    console.log('classcomponet');
    console.log(this.gameService.getCreateGameName());
    this.gamename = this.gameService.getCreateGameName()
    this.username = JSON.parse(localStorage.getItem('currentUser')).username;
  }
  joinGame(hero: Hero) {
    this.gameService.joinGame(this.username, this.gamename, hero);
  }
}
