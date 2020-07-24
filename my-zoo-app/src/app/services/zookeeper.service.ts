import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

@Injectable()
export class ZookeeperService {

  animals: {}[]= [];

  constructor(private http: HttpClient) { }


  public getAllAnimals(): Observable<{}[]>{
    return this.http.get<{}[]>("http://localhost:8080/api/getallanimals");
}

public getAnimalsOfEmployee(id: number): Observable<{}[]>{
  return this.http.get<{}[]>(`http://localhost:8080/api/getallanimalsof?${id}`);
}

public getInventory(): Observable<{}[]>{
  return this.http.get<{}[]>("http://localhost:8080/api/getallinventory");
}
}
