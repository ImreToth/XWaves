import {Injectable} from '@angular/core';
import {Http} from '@angular/http';

@Injectable()
export class LoginService {
  constructor(private http: Http) {}
  loginAccount(username: string, password: string) {
    let x:[string, string]
    x = [username, password]
   return this.http.post('/login' , x);
  }
}
