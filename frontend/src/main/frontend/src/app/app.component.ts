import { Component } from '@angular/core';
import {CyclopaediaService} from './cyclopaedia/cyclopaedia.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  constructor(private cyclopaediaService: CyclopaediaService) { }
}
