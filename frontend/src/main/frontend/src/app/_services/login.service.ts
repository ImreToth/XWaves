import {Injectable} from '@angular/core';
import {Http} from '@angular/http';

@Injectable()
export class LoginService {
  public token: string;
  public info: string;
  constructor(private http: Http) {
    this.token = null;
    const currentUser = JSON.parse(localStorage.getItem('currentUser'));
    this.token = currentUser && currentUser.token;
    this.info = '';
  }


  loginAccount(username: string, password: string) {
    this.http.post('/api/login' , {'username' : username, 'password' : password })
      .subscribe(suc => {
          const token = suc.text();
          if (token) {
            // set token property
            this.token = token;
            localStorage.setItem('currentUser', JSON.stringify({ username: username, token: token }));
            console.log(suc);
            this.info = '';
            return true;
          } else {
            this.info = suc.text();
            return this.info;
          }
        },
        err => {
          console.log(err );
          this.info = err.text();
          return this.info;
        });
  }

  registerAccount(username: string, email: string, password: string) {
    this.http.post('/api/register' , {'username' : username, 'password' : password, 'email' : email})
      .subscribe(suc => {
            this.info = suc.text();
          return this.info;
          },
        err => {
          console.log(err );
          this.info = err.text();
          return this.info;
        });
  }

  logout(): void {
    this.token = null;
    localStorage.removeItem('currentUser');
  }

  isAuthenticated() {
    return this.token != null;
  }

  getInfo() {
    return this.info;
  }
}
