import { Component, OnInit } from '@angular/core';
import {LoginService} from '../_services/login.service';


@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
  token: string;
  constructor(private loginService: LoginService) {}
  ngOnInit() {
    const currentUser = JSON.parse(localStorage.getItem('currentUser'));
    this.token = currentUser && currentUser.token;
  }
  logout(): void {
    this.token = null;
    localStorage.removeItem('currentUser');
  }
}
