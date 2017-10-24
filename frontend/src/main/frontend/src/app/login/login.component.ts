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

  regBlock= "false";
  logBlock = "false";


  myStyle = {
    "display" : "none",
  }

  ngOnInit() {
  }

  changeRegister(){
    this.logActive = "deactive";
    this.regActive = "active";

    this.regBlock= "display: block;";
    this.logBlock = "display: none;";
  }

  changeLogin(){
    this.regActive = "deactive";
    this.logActive = "active";

    this.regBlock= "\"display: none;\"";
    this.logBlock = "\"display: block;\"";

  }
  getTheValue(){
    return "none";
  }
}
