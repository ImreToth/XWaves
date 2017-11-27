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

  ngOnInit() {
  }
}
