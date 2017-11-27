import { Component, OnInit } from '@angular/core';
import {Item} from '../../Item';
import {CyclopaediaService} from '../cyclopaedia.service';

@Component({
  selector: 'app-items',
  templateUrl: './items.component.html',
  styleUrls: ['./items.component.css']
})
export class ItemsComponent implements OnInit {
  items: Item[];
  constructor(private cyclopaediaService: CyclopaediaService) { }

  ngOnInit() {
    this.items = this.cyclopaediaService.getItems();
  }

}
