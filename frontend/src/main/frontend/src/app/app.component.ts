import { Component } from '@angular/core';
import {CyclopaediaService} from './_services/cyclopaedia.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  constructor(private cyclopaediaService: CyclopaediaService) { }
}
