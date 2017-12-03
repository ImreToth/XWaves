import { Component, OnInit } from '@angular/core';
import {LoginService} from '../_services/login.service';
import {Http} from '@angular/http';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  public token: string;
  constructor(private loginService: LoginService, private http: Http, private router: Router) {
    const currentUser = JSON.parse(localStorage.getItem('currentUser'));
    this.token = currentUser && currentUser.token;
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
    this.loginService.loginAccount(this.loginUsername, this.loginPassword)
      .subscribe(suc => {
          const token = suc.text();
          if (token) {
            // set token property
            this.token = token;
            localStorage.setItem('currentUser', JSON.stringify({ username: this.loginUsername, token: token }));
            this.router.navigate(['play']);
            console.log(suc);
            return true;
          } else {
            this.infoMessage = suc.text();
            return false;
          }
        },
        err => {
          console.log(err );
          this.infoMessage = err.text();
        });
  }

  submitRegister() {
    this.loginService.registerAccount(this.registerUsername, this.registerEmail, this.registerPassword)
      .subscribe(suc => {
          console.log(suc);
          if (suc.text() === 'Sikeresen regisztráltál!') {
            this.changeLogin();
          } else {
            this.infoMessage = suc.text();
          }


          console.log(this.infoMessage);
        },
        err => {
          console.log(err );
          this.infoMessage = err.text();
        });
  }
  logout(): void {
    this.token = null;
    localStorage.removeItem('currentUser');
  }

}
