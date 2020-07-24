import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import {DebugElement } from '@angular/core';
import { CounterComponent } from './counter.component';
import { By } from '@angular/platform-browser';

describe('CounterComponent', () => {
    let component: CounterComponent;
    let fixture: ComponentFixture<CounterComponent>;
    let debugElement: DebugElement;
    let htmlElement: HTMLElement;

    beforeEach(async(() => {
        TestBed.configureTestingModule({
            declarations: [CounterComponent]
        }).compileComponents();
    }));

    beforeEach(()=> {
        fixture = TestBed.createComponent(CounterComponent);
        component = fixture.componentInstance;


        fixture.detectChanges()

        debugElement = fixture.debugElement.query(By.css('p'));
        htmlElement = debugElement.nativeElement;
    });

    it('should increment the counter number by one', ()=> {
        // Arrange
        const intialValue = component.counter;

        //Act
        component.Increment();
        const newValue = component.counter;

        //Assert
        expect(newValue).toBeGreaterThan(intialValue)

    })

    it('should decrement the counter number by one', ()=> {
        // Arrange
        const intialValue = component.counter;

        //Act
        component.Decrement();
        fixture.detectChanges();
        const newValue = component.counter;

        //Assert
        expect(newValue).toBeLessThan(intialValue)

    })


    it('should display the current number screen, after being incremented by one', () =>{
        component.Increment();
        fixture.detectChanges();
        expect(htmlElement.textContent).toEqual('Number: 2');
    })

    it('should display the current number screen, after being decremented by one', () =>{
        component.Decrement();
        fixture.detectChanges();
        expect(htmlElement.textContent).toEqual('Number: 0');
    })


    it('should display the current number of the counter', () =>{
        //Assert that the text on screen is of Number: 1
        expect(htmlElement.textContent).toEqual('Number: 1')
    })

});
