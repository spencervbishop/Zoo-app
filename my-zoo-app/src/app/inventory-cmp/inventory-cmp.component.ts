import { Component, OnInit } from '@angular/core';
import { Http, Headers, RequestOptions } from '@angular/http';
import { HttpHeaders } from '@angular/common/http';

@Component({
  selector: 'app-inventory-cmp',
  templateUrl: './inventory-cmp.component.html',
  styleUrls: ['./inventory-cmp.component.css']
})
export class InventoryCmpComponent implements OnInit {
  inventory;

  constructor(private http: Http) {
    const headers = new Headers();
    headers.append('Authorization', 'Bearer'+ ' '+ localStorage.getItem('token'));
    const options = new RequestOptions({headers: headers});
    var body = { "employeeId": +localStorage.getItem("Id")}
    this.http.post('https://zootropolis-thankful-quokka.cfapps.io/Food/',body, options).
    map (
      (response) => response.text()
    ).subscribe (
      (data) => {this.inventory = JSON.parse(data)}
    )

   }

  ngOnInit() {

  }

}
