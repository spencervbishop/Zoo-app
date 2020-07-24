import { Component } from '@angular/core';
import { Router} from '@angular/router';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'app';

  constructor(private router: Router,){

  }
  ngOnInit() {
    // this.router.navigate(['app-home-cmp'])
  }

  onClickHome(event){
    this.router.navigate(['app-home-cmp'])
  }

  
  onClickSchedule(event){
    this.router.navigate(['app-schedule-cmp'])
  }

  onClickMap(event){
    this.router.navigate(['app-map-cmp'])
  }

  onClickSupport(event){
    this.router.navigate(['app-support-cmp'])
  }


}
