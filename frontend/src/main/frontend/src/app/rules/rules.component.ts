import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-rules',
  templateUrl: './rules.component.html',
  styleUrls: ['./rules.component.css']
})
export class RulesComponent implements OnInit {
  array: number[];
  N = 100; // mezők száma
  constructor() {
    this.array = Array.apply(null, {length: this.N}).map(Number.call, Number); // mezőgenerátor
  }

  ngOnInit() {
  }

}
