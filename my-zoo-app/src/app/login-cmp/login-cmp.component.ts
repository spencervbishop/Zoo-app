import { Component, OnInit } from '@angular/core';
import { Router} from '@angular/router';
import { Http, Response } from '@angular/http';
import { JwtHelperService } from '@auth0/angular-jwt';
import * as jwtDecode from "jwt-decode";
import { HttpClientModule } from '@angular/common/http';
import 'rxjs/add/operator/map';
import { error } from 'protractor';


@Component({
  selector: 'app-login-cmp',
  templateUrl: './login-cmp.component.html',
  styleUrls: ['./login-cmp.component.css']
})
export class LoginCmpComponent implements OnInit {

  step = "start";
  username: string;
  password: string;

 
  constructor(private router: Router,private http: Http) { }

  ngOnInit() {
  }



  onClickSubmit(event){
   this.step = 'Good'
    var body = { "username":this.username,"password":this.password}

    this.http.post('https://zootropolis-thankful-quokka.cfapps.io/users/signin',body).
    map (
      (response) => response.text()
    ).subscribe (
      (data) => {this.submitData(data)},
      (error) =>{this.step = 'Wrong'}
    )
   
  }

  submitData(data){
    
    var token = data;
    var data2 = jwtDecode(token);
    localStorage.setItem("token",data);
    localStorage.setItem("Username",this.username);
    localStorage.setItem("Role",data2['aud']);
    localStorage.setItem("Id",data2['sub']);
    this.router.navigate(['app-administrator-cmp'])
  }


}
