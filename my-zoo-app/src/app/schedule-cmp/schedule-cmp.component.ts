import { Component, OnInit } from '@angular/core';
import { Http, Response } from '@angular/http';


@Component({
  selector: 'app-schedule-cmp',
  templateUrl: './schedule-cmp.component.html',
  styleUrls: ['./schedule-cmp.component.css']
})
export class ScheduleCmpComponent implements OnInit {

  httpdata;
  constructor(private http: Http) { }

  ngOnInit() {

    this.http.get('https://zootropolis.herokuapp.com/Events/').
    map (
      (response) => response.text()
    ).subscribe (
      (data) => {this.displaydata(data)}
    )
  }
   displaydata(data){
      this.httpdata = JSON.parse(data);
     
    }

}
