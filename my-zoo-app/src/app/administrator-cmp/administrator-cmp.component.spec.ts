//  import { async, ComponentFixture, TestBed } from '@angular/core/testing';

//  import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing'
//  import { HttpClient, HttpClientModule } from '@angular/common/http';
//  import { Observable } from 'rxjs/Observable';
//  import { AdministratorCmpComponent } from './administrator-cmp.component';

// describe('AdministratorCmpComponent', () => {
//   let component: AdministratorCmpComponent;
//   let fixture: ComponentFixture<AdministratorCmpComponent>;
//   let mockClient;

//   beforeEach(() => {
//     mockClient = jasmine.createSpyObj('HttpClient', ['get', 'post']);
//     TestBed.configureTestingModule({
//       declarations: [ AdministratorCmpComponent ],
//       providers: [
//         { provide: HttpClient, useValue: mockClient }
//       ]
//     })
//     .compileComponents();

//     fixture = TestBed.createComponent(AdministratorCmpComponent);
//     component = fixture.componentInstance;
//     fixture.detectChanges();
//     mockClient =TestBed.get(HttpClient);
//   });

//   it('should create', () => {
//     expect(component).toBeTruthy();
//   });

//   it('should fetch data async', async(() => {
//     mockClient.post.and.returnValue(Observable.create(observer=>{
//       observer.next(['firstName: TestFirstName, lastName: TestLastName']);
//     }));
    
//     fixture.detectChanges();

//     fixture.whenStable().then(() => {
//       expect(component.data2).toBe(['firstName: TestFirstName, lastName: TestLastName']);
//     });
//   }));

// });
