import { Component, OnInit } from '@angular/core';
import { Zookeeper } from '../domain/zookeeper';
import { Animal } from '../domain/animal';
import {Http, Headers, RequestOptions} from '@angular/http';
import { LoginService } from '../services/login.service';
import { HttpHeaders } from '@angular/common/http';
import { Router} from '@angular/router';
import 'rxjs/add/operator/map';



@Component({
  selector: 'app-administrator-cmp',
  templateUrl: './administrator-cmp.component.html',
  styleUrls: ['./administrator-cmp.component.css']
})
export class AdministratorCmpComponent implements OnInit {

  creaturs;
  data2;
  zookeeper: {};
  animals: Animal[] = [];
  inventory: {}[] = [];

  constructor(private loginService: LoginService,private http: Http,private router: Router) { 
    const headers = new Headers();
    headers.append('Authorization', 'Bearer'+ ' '+ localStorage.getItem('token'));
    const options = new RequestOptions({headers: headers});
    var body = { "employeeId": +localStorage.getItem("Id")}
    this.http.post('https://zootropolis.herokuapp.com/users/info',body, options).
    map (
      (response) => response.text()
    ).subscribe (
      (data) => {this.setUserInfo(data)}
    )

  }


  setUserInfo(data){
    var data2 = JSON.parse(data);
    localStorage.setItem("Firstname", data2["firstName"]);
    localStorage.setItem("Lastname",data2["lastName"])    
    console.log(data2)
  }

  ngOnInit() {
     this.zookeeper = new Zookeeper( localStorage.getItem("Username"), localStorage.getItem("Firstname"), localStorage.getItem("Lastname"), localStorage.getItem("Role"));
   

  }

  signOut(event){
    alert("Successfully logged out")
    localStorage.removeItem("token")
    this.router.navigate(['app-login-cmp'])
  }
}
