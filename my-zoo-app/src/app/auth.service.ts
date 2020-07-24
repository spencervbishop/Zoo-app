import { Injectable } from '@angular/core';
import { JwtHelperService } from '@auth0/angular-jwt';
import { Router} from '@angular/router';

@Injectable()
export class AuthService {

  constructor(private router: Router) { }

  public isAuthenticated(){
    const token = localStorage.getItem("token");

    if(token == null){
      alert("Please Login")
      this.router.navigate(['app-login-cmp'])
    }
    else {
    const helper = new JwtHelperService();
    const decodedToken = helper.decodeToken(token);
    const expirationDate = helper.getTokenExpirationDate(token);
    const isExpired = helper.isTokenExpired(token);
    return !isExpired;
    }
  }

}
