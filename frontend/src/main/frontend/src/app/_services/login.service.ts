import {Injectable} from '@angular/core';
import {Http} from '@angular/http';

@Injectable()
export class LoginService {
  public token: string;
  constructor(private http: Http) {
    const currentUser = JSON.parse(localStorage.getItem('currentUser'));
    this.token = currentUser && currentUser.token;
  }


  loginAccount(username: string, password: string) {
    let info: string;
    this.http.post('/api/login' , {'username' : username, 'password' : password })
      .subscribe(suc => {
          const token = suc.text();
          if (token) {
            // set token property
            this.token = token;
            localStorage.setItem('currentUser', JSON.stringify({ username: username, token: token }));
            console.log(suc);
            return true;
          } else {
            info = suc.text();
          }
        },
        err => {
          console.log(err );
          info = err.text();
        });
    return info;
  }

  registerAccount(username: string, email: string, password: string) {
    let info: string;
    this.http.post('/api/register' , {'username' : username, 'password' : password, 'email' : email})
      .subscribe(suc => {
            info = suc.text();
          },
        err => {
          console.log(err );
          info = err.text();
        });
    return info;
  }

  logout(): void {
    this.token = null;
    localStorage.removeItem('currentUser');
  }

  isAuthenticated() {
    return this.token != null;
  }
}
