import { Component, OnInit } from '@angular/core';
import {LoginService} from './login.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(private loginService: LoginService) { }

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
          console.log(suc);
          this.infoMessage = suc.text();
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

}
