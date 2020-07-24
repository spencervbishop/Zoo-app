import { Component, OnInit } from '@angular/core';
import { Http, Response, RequestOptions, Headers } from '@angular/http';

@Component({
  selector: 'app-bar-cmp',
  templateUrl: './bar-cmp.component.html',
  styleUrls: ['./bar-cmp.component.css']
})
export class BarCmpComponent implements OnInit {
    stuff: {}[] = [];
    data;
    httpdata;
    data2;
  width = 600;
    height = 400;
    type = 'column2d';
    dataFormat = 'json';
    dataSource;

  constructor(private http: Http) {
    const headers = new Headers();
    headers.append('Content-Type', "application/json");
    headers.append('Authorization', 'Bearer'+ ' '+localStorage.getItem("token"));
    const options = new RequestOptions({headers: headers});
    var body = ' '
    this.http.post('https://zootropolis.herokuapp.com/Animal/topfive',body,options).
    map (
      (response) => response.text()
    ).subscribe (
      (data) => {this.display(data)}
    )



    this.dataSource = this.dataSource = {
      "chart": {
          "caption": "Animal Popularity",
          "subCaption": "Top 5 popular animals this year",
        //   "numberprefix": "$",
          "theme": "fint"
      },
      "data": this.stuff
  }
   }

   display(data){
    
    var i = 0;
    this.httpdata = JSON.parse(data)
    console.log(this.httpdata)
   for (var key in this.httpdata) {
         if (this.httpdata.hasOwnProperty(key)) {
       var val = this.httpdata[key];
       this.data ={label: val["animalName"], value: val["tracking"].toString()};
       this.stuff.push(this.data)
     }
    
   }
}

  ngOnInit() {
  }

}
