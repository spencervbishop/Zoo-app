import { fakeAsync, ComponentFixture, TestBed, tick } from '@angular/core/testing';
import {RouterTestingModule} from "@angular/router/testing";
import { Location } from '@angular/common';
import { AppComponent } from './app.component';
import { Routes } from '@angular/router';
import {Router} from "@angular/router";
import { LoginCmpComponent } from './login-cmp/login-cmp.component';
import { CounterComponent } from './counter/counter.component';
import { MapCmpComponent } from './map-cmp/map-cmp.component';
import { SupportCmpComponent } from './support-cmp/support-cmp.component';
import { ScheduleCmpComponent } from './schedule-cmp/schedule-cmp.component';
import { HomeCmpComponent } from './home-cmp/home-cmp.component';
import { AdministratorCmpComponent } from './administrator-cmp/administrator-cmp.component';
import { PieCmpComponent } from './pie-cmp/pie-cmp.component';
import { AnimalCmpComponent } from './animal-cmp/animal-cmp.component';
import { InventoryCmpComponent } from './inventory-cmp/inventory-cmp.component';
//const routerSpy = jasmine.createSpyObj('Router', ['navigate']);
export const appRoutes: Routes = [
  { path: '', redirectTo: '/app-home-cmp', pathMatch: 'full' },
  {path: 'app-administrator-cmp', component: AdministratorCmpComponent},
  {path: 'app-login-cmp', component: LoginCmpComponent},
  {path: 'app-home-cmp', component: HomeCmpComponent },
  {path: 'app-schedule-cmp', component: ScheduleCmpComponent },
  {path: 'app-map-cmp', component: MapCmpComponent },
  {path: 'app-support-cmp', component: SupportCmpComponent },
  {path: 'app-pie-cmp', component: PieCmpComponent}
]


describe('Router: App', () => {
  let fixture;
  let router: Router;
  let location: Location;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [RouterTestingModule.withRoutes(appRoutes)],
      declarations: [ 
        AppComponent, 
        CounterComponent,
        AdministratorCmpComponent,
        MapCmpComponent,
        SupportCmpComponent,
        ScheduleCmpComponent,
        PieCmpComponent,
        HomeCmpComponent,
        LoginCmpComponent,
        AnimalCmpComponent,
        InventoryCmpComponent
       ]
    });

    router = TestBed.get(Router); 
    location = TestBed.get(Location); 

    fixture = TestBed.createComponent(AppComponent); 
    router.initialNavigation(); 
  });

    // it('true should be true', ()=> {
    //   expect(true).toBe(true);
    // })


    it('navigate to "" redirects you to /app-home-cmp', fakeAsync(()=>{
      router.navigate(['app-home-cmp']);
      tick(50);
      expect(location.path()).toBe('');
    }));

    it('should tell navigate to LoginComponent when we type `app-administration`', fakeAsync(() => {
      router.navigate(['app-login-cmp']);
      tick();
      expect(location.path()).toBe('');
    }));

    // it('should navigate to Schedule Componenet when we click the schedule button', fakeAsync(() =>{
    //   //fixture.onClickSchedule(event);
    //   router.navigate(['']);
    //   tick();
    //   expect(location.path()).toBe('/app-home-cmp');
    // }))
});

    // it(`should tell ROUTER to navigate when Administration is clicked`, ()=>{
    
    // component.onClickLogin(event);
    
    //   // args passed to router.navigateByUrl() spy
    //   const spy = component.router.navigate as jasmine.Spy;
    //   const navArgs = spy.calls.first().args[0];
    
    //   // expecting to navigate to the login page
    //   expect(navArgs).toBe('/app-login-cmp',
    //     'should nav to the Login page');
    // })