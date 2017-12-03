import { Component, OnInit } from '@angular/core';
import {LoginService} from '../_services/login.service';


@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
  public loggedIn: boolean;
  constructor(private loginService: LoginService) {}
  ngOnInit() {
    this.loggedIn = this.loginService.isAuthenticated();
  }
  logout() {
    this.loginService.logout();
  }
}
