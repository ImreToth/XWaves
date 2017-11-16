import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {Hero} from '../Hero';
import {CyclopaediaService} from './cyclopaedia.service';

@Component({
  selector: 'app-cyclopaedia',
  templateUrl: './cyclopaedia.component.html',
  styleUrls: ['./cyclopaedia.component.css']
})
export class CyclopaediaComponent implements OnInit {
  hero: Hero[];
  constructor(private cyclopaediaService: CyclopaediaService) {
    this.hero = this.cyclopaediaService.getHeroes();
    console.log(this.hero[3].attack);
  }
  ngOnInit() {
  console.log('lefut no kappa');
  console.log(this.hero[3].name);
  }
}
