import {Injectable} from '@angular/core';
import {Http} from '@angular/http';

@Injectable()
export class LoginService {
  constructor(private http: Http) {}
  loginAccount(username: string, password: string) {

   return this.http.post('/login' , {'username' : username, 'password' : password });
  }

  registerAccount(username: string, email: string, password: string) {
    return this.http.post('/register' , {'username' : username, 'password' : password, 'email' : email});
  }
}
