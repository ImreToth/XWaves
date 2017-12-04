import { Component } from '@angular/core';
import {CyclopaediaService} from './_services/cyclopaedia.service';
import {GamesService} from './_services/games.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  constructor(private cyclopaediaService: CyclopaediaService, private gameService: GamesService) { }
}
