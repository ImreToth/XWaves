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

  logActive = 'active';
  regActive = 'deactive';

  regBlock= 'none';
  logBlock = 'block';

  ngOnInit() {
  }

  changeRegister() {
    this.logActive = 'deactive';
    this.regActive = 'active';

    this.logBlock = 'none';
    this.regBlock = 'block';

  }

  changeLogin() {
    this.regActive = 'deactive';
    this.logActive = 'active';

    this.logBlock = 'block';
    this.regBlock = 'none';


    console.log(this.loginUsername);
  }
  getLogValue() {
    return this.logBlock;
  }

  getRegValue() {
    return this.regBlock;
  }

  submitLogin() {
    console.log(this.loginUsername + this.loginPassword);
    this.loginService.loginAccount(this.loginUsername, this.loginPassword)
      .subscribe();
  }

  submitRegister() {
    this.loginService.registerAccount(this.registerUsername, this.registerEmail, this.registerPassword)
      .subscribe();
  }

}
