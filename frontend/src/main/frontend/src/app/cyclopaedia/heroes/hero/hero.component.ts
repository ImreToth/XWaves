import {Component, Input, OnInit} from '@angular/core';

@Component({
  selector: 'app-hero',
  templateUrl: './hero.component.html',
  styleUrls: ['./hero.component.css']
})
export class HeroComponent implements OnInit {
  @Input() heroName: string;
  @Input() heroType: string ;
  @Input() heroAttack: number;
  @Input() heroHealth: number;
  @Input() heroStamina: number;
  @Input() heroDefense: number;
  @Input() heroSpeed: number;
  @Input() heroImagePath: string;
  constructor() { }

  ngOnInit() {
  }

}
