import { Component, OnInit } from '@angular/core';
import { Http, Response, Headers, RequestOptions  } from '@angular/http'; 


@Component({
  selector: 'app-map-cmp',
  templateUrl: './map-cmp.component.html',
  styleUrls: ['./map-cmp.component.css']
})
export class MapCmpComponent implements OnInit {
  id;
  httpdata;
  lat: number = 38.931001;
  lng: number = -77.0512221;
  pictures={
    default : "assets/Bobcat.jpg",
    "American Alligator":  "assets/American-Alligator.jpg",
    "American Bison": "assets/American-Bison.jpg",
    "Asian Elephant": "assets/Asian-Elephant.jpg",
    "Asian Otter": "assets/Asian-Otter.jpg",
    "Bald Eagle": "assets/Bald-Eagle.jpg",
    "Bobcat": "assets/Bobcat.jpg",
    "Carpet Python": "assets/Carpet-Python.jpg",
    "California Sea Lion": "assets/California-Sea-Lion.jpg",
    "Cheetah": "assets/Cheetah.jpg",
    "Giant Panda": "assets/Giant-Panda.JPG", 
    "Lion": "assets/Lion.jpg",
    "Maned wolf": "assets/Maned-Wolf.jpg",
    "Orangutan": "assets/Orangutan.JPG",
    "Red Panda": "assets/Red-Panda.JPG",
    "Tiger": "assets/Tiger.jpg",
    "Western Lowland Gorilla": "assets/Western-Lowland-Gorilla.jpg",
    "Gray wolf": "assets/Gray-Wolf.jpg",
    "Komodo Dragon": "assets/Komodo-Dragon.jpg",
    "Gray mouse lemur": "assets/Gray-Mouse-Lemur.JPG",
    "Grevy's Zebra": "assets/Grevy's-Zebra.jpg"   
  };
  picture: string = this.pictures["Gray wolf"];

  myclick(event){
    
  }
  constructor(private http: Http) {



   }

  ngOnInit() {
    const headers = new Headers();
    headers.append('Authorization', 'Bearer'+ ' '+"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJhdWQiOiJtYW5hZ2VyIiwic3ViIjoiNiIsImlzcyI6Ilpvb3Ryb3BvbGlzIiwiZXhwIjoxNTIyNDU4NTM3fQ.lfnW2eAuW9h6hTEyf9oYLEufAPsZuWuayCmSr1zPD0q-xBXydRDIrio2rDmL4nlpUyq4rr40H6poCmJFaJj0rw");
    const options = new RequestOptions({headers: headers});
    var body = ' '
    this.http.post('https://zootropolis-thankful-quokka.cfapps.io/Location/',body,options).
    map (
      (response) => response.text()
    ).subscribe (
      (data) => {this.displaydata(data)}
    )
  
  }

  displaydata(data){
    this.httpdata = JSON.parse(data);
  }


  myfunction(id: any){
    const headers = new Headers();
    headers.append('Content-Type', "application/json");
    const options = new RequestOptions({headers: headers});
    console.log(id)
    var body = id
    this.http.post('https://zootropolis.herokuapp.com/Animal/increment',id.toString(),options).
    map (
      (response) => response.text()
    ).subscribe (
      (data) => {console.log(data)}
    )
    
  }




}
