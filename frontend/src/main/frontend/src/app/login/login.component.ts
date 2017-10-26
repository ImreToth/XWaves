import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor() { }

  logActive = "active";
  regActive = "deactive";

  regBlock= "none";
  logBlock = "block";

  ngOnInit() {
  }

  changeRegister(){
    this.logActive = "deactive";
    this.regActive = "active";

    this.logBlock = "none";
    this.regBlock = "block";

  }

  changeLogin(){
    this.regActive = "deactive";
    this.logActive = "active";

    this.logBlock = "block";
    this.regBlock = "none";
  }
  getLogValue(){
    return this.logBlock;
  }

  getRegValue(){
    return this.regBlock;
  }

}
