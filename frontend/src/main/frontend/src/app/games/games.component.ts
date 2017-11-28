import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-games',
  templateUrl: './games.component.html',
  styleUrls: ['./games.component.css']
})
export class GamesComponent implements OnInit {
  isActive: boolean;
  constructor() { }

  ngOnInit() {
  }
btnActive() {
    this.isActive = !this.isActive;
}
}
