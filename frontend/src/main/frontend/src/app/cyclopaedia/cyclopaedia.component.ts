import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {Hero} from '../_models/Hero';
import {CyclopaediaService} from '../_services/cyclopaedia.service';

@Component({
  selector: 'app-cyclopaedia',
  templateUrl: './cyclopaedia.component.html',
  styleUrls: ['./cyclopaedia.component.css']
})
export class CyclopaediaComponent implements OnInit {

  ngOnInit() {
  }
}
