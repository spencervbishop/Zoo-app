import { Component, OnInit } from '@angular/core';
import { Http, Response, RequestOptions, Headers } from '@angular/http';

@Component({
  selector: 'app-pie-cmp',
  templateUrl: './pie-cmp.component.html',
  styleUrls: ['./pie-cmp.component.css']
})
export class PieCmpComponent implements OnInit {
  stuff: {}[] = [];
  data;
  httpdata;
  width = 600;
  height = 400;
  type = 'pie3d';
  dataFormat = 'json';
  dataSource = {
    "chart": {
      "caption": "Animal Population Proportions",
      "subcaption": "This Year",
      "startingangle": "120",
      "showlabels": "0",
      "showlegend": "1",
      "enablemultislicing": "0",
      "slicingdistance": "15",
      "showpercentvalues": "1",
      "showpercentintooltip": "0",
      "plottooltext": "Animal : $label Total  : $datavalue",
      "theme": "ocean"
  },
  "data":this.stuff
  };

  
  constructor(private http: Http) {
   
   
    const headers = new Headers();
    headers.append('Authorization', 'Bearer'+ ' '+"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJhdWQiOiJtYW5hZ2VyIiwic3ViIjoiNiIsImlzcyI6Ilpvb3Ryb3BvbGlzIiwiZXhwIjoxNTIyNDU4NTM3fQ.lfnW2eAuW9h6hTEyf9oYLEufAPsZuWuayCmSr1zPD0q-xBXydRDIrio2rDmL4nlpUyq4rr40H6poCmJFaJj0rw");
    const options = new RequestOptions({headers: headers});
    var body = ' '
    this.http.post('https://zootropolis-thankful-quokka.cfapps.io/Location/',body,options).
    map (
      (response) => response.text()
    ).subscribe (
      (data) => {this.display(data)}
    )


   }

   display(data){
     var i = 0;
     this.httpdata = JSON.parse(data)
    for (var key in this.httpdata) {
          if (this.httpdata.hasOwnProperty(key)) {
        var val = this.httpdata[key];
        this.data ={label: val["animal"]["animalName"], value: val["animal"]["numOfAnimal"].toString()};
        this.stuff.push(this.data)
      }
     
    }
   }





  ngOnInit() {
  }

}
