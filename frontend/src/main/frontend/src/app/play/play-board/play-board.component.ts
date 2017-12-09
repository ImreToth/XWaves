import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-play-board',
  templateUrl: './play-board.component.html',
  styleUrls: ['./play-board.component.css']
})
export class PlayBoardComponent implements OnInit {
  array: number[];
  N = 100; // mezők száma
  constructor() {
    this.array = Array.apply(null, {length: this.N}).map(Number.call, Number); // mezőgenerátor
  }

  ngOnInit() {
  }


}
