import { Component, OnInit } from '@angular/core';
import {LoginService} from '../_services/login.service';
import {Router} from "@angular/router";


@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  constructor(private loginService: LoginService, private router: Router) {}
  ngOnInit() {
  }
  logout() {
    this.loginService.logout();
    this.router.navigate(['rules']);
  }

  isLoggedIn() {
    return this.loginService.isAuthenticated();
  }
}
