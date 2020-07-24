import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SupportCmpComponent } from './support-cmp.component';

describe('SupportCmpComponent', () => {
  let component: SupportCmpComponent;
  let fixture: ComponentFixture<SupportCmpComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SupportCmpComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SupportCmpComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
