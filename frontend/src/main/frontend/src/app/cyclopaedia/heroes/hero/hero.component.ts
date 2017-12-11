import {Component, Input, OnInit} from '@angular/core';
import {Hero} from '../../../_models/Hero';

@Component({
  selector: 'app-hero',
  templateUrl: './hero.component.html',
  styleUrls: ['./hero.component.css']
})
export class HeroComponent implements OnInit {
  @Input() hero: Hero;
  stri = 'assets';
  constructor() { }

  ngOnInit() {
  }

}
