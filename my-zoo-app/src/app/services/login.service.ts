import { Injectable } from '@angular/core';
import { Zookeeper } from '../domain/zookeeper';
import { Observable } from 'rxjs/Observable';
import { HttpClient } from '@angular/common/http';

@Injectable()
export class LoginService {

  userToken: {};

  constructor(private http : HttpClient) { }

  public postLogin(zookeeper: Zookeeper): Observable<Zookeeper>{
    return this.http.post<Zookeeper>("http://localhost:8080/api/login", zookeeper);
    // .pipe(
    //   retry(3), // retry a failed request up to 3 times
    //   catchError(this.handleError) // then handle the error
    //);
  }
  

// private handleError(error: HttpErrorResponse) {
//   if (error.error instanceof ErrorEvent) {
//     // A client-side or network error occurred. Handle it accordingly.
//     console.error('An error occurred:', error.error.message);
//   } else {
//     // The backend returned an unsuccessful response code.
//     // The response body may contain clues as to what went wrong,
//     console.error(
//       `Backend returned code ${error.status}, ` +
//       `body was: ${error.error}`);
//   }
//   // return an ErrorObservable with a user-facing error message
//   return new ErrorObservable(
//     'Something bad happened; please try again later.');
// };


}
