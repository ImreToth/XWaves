import { Component, OnInit } from '@angular/core';
import {LoginService} from '../_services/login.service';
import {Http} from '@angular/http';
import { Router } from '@angular/router';
import {GamesService} from '../_services/games.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  constructor(private loginService: LoginService, private router: Router, private gameService: GamesService) {
  }
  loginUsername = '';
  loginPassword = '';
  registerUsername = '';
  registerPassword = '';
  registerRePassword = '';
  registerEmail = '';
  infoMessage = '';

  logActive = 'active';
  regActive = 'deactive';

  regBlock= 'none';
  logBlock = 'block';

  ngOnInit() {
  }

  changeRegister() {
    this.loginUsername = '';
    this.loginPassword = '';

    this.logActive = 'deactive';
    this.regActive = 'active';

    this.logBlock = 'none';
    this.regBlock = 'block';

    this.infoMessage = '';
  }

  changeLogin() {
    this.registerUsername = '';
    this.registerPassword = '';
    this.registerRePassword = '';
    this.registerEmail = '';

    this.regActive = 'deactive';
    this.logActive = 'active';

    this.logBlock = 'block';
    this.regBlock = 'none';

    this.infoMessage = '';

    console.log(this.loginUsername);
  }
  getLogValue() {
    return this.logBlock;
  }

  getRegValue() {
    return this.regBlock;
  }

  submitLogin() {
    this.loginService.loginAccount(this.loginUsername, this.loginPassword);
  this.infoMessage = this.loginService.getInfo();
    console.log(this.infoMessage); }
  submitRegister() {
    this.loginService.registerAccount(this.registerUsername, this.registerEmail, this.registerPassword);
    this.infoMessage = this.loginService.getInfo();
    this.changeLogin();
  }
  infoRefresh() {
    if (this.loginService.isAuthenticated()) {this.router.navigate(['rules']); }
    return this.loginService.getInfo();
  }

}
