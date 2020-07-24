import { BrowserModule } from '@angular/platform-browser';
import { Injectable } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { NgModule } from '@angular/core';
import { RouterModule, Routes, CanActivate} from '@angular/router';
import { HttpModule } from '@angular/http'; 
import { HttpClientModule } from '@angular/common/http';
import { AgmCoreModule } from '@agm/core';
import { AgmSnazzyInfoWindowModule } from '@agm/snazzy-info-window';
import * as FusionCharts from 'fusioncharts';
import * as Charts from 'fusioncharts/fusioncharts.charts';
import * as FintTheme from 'fusioncharts/themes/fusioncharts.theme.fint';
import { FusionChartsModule } from 'angular4-fusioncharts';

import { AdministratorCmpComponent } from './administrator-cmp/administrator-cmp.component';
import { AppComponent } from './app.component';
import { CounterComponent } from './counter/counter.component';
import { MapCmpComponent } from './map-cmp/map-cmp.component';
import { SupportCmpComponent } from './support-cmp/support-cmp.component';
import { ScheduleCmpComponent } from './schedule-cmp/schedule-cmp.component';
import { HomeCmpComponent } from './home-cmp/home-cmp.component';
import { LoginCmpComponent } from './login-cmp/login-cmp.component';
import { LoginService } from './services/login.service';
import { PieCmpComponent } from './pie-cmp/pie-cmp.component';
import { BarCmpComponent } from './bar-cmp/bar-cmp.component';
import { AuthGaurdService } from './auth-gaurd.service';
import { AuthService } from './auth.service';
import { InventoryCmpComponent } from './inventory-cmp/inventory-cmp.component';
import { AnimalCmpComponent } from './animal-cmp/animal-cmp.component';

export const appRoutes: Routes = [
  { path: '', redirectTo: '/app-home-cmp', pathMatch: 'full' },
  {path: 'app-administrator-cmp', component: AdministratorCmpComponent, canActivate: [AuthGaurdService]},
  {path: 'app-login-cmp', component: LoginCmpComponent},
  {path: 'app-home-cmp', component: HomeCmpComponent },
  {path: 'app-schedule-cmp', component: ScheduleCmpComponent },
  {path: 'app-map-cmp', component: MapCmpComponent },
  {path: 'app-support-cmp', component: SupportCmpComponent },
  {path: 'app-pie-cmp', component: PieCmpComponent}
]
// Pass FusionCharts, Charts and FintTheme as dependencies.
// You can also pass all other FusionCharts modules such as
// PowerCharts, FusionMaps, Map Definitions, Widgets, Themes etc after FusionCharts.
FusionChartsModule.fcRoot(FusionCharts, Charts, FintTheme);

@NgModule({
  declarations: [
    AdministratorCmpComponent,
    AppComponent,
    CounterComponent,
    MapCmpComponent,
    SupportCmpComponent,
    ScheduleCmpComponent,
    HomeCmpComponent,
    LoginCmpComponent,
    PieCmpComponent,
    BarCmpComponent,
    InventoryCmpComponent,
    AnimalCmpComponent
  ],
  imports: [
    BrowserModule,
    HttpModule,
    FormsModule,
    FusionChartsModule,
    RouterModule.forRoot(appRoutes),
    HttpClientModule,
    AgmCoreModule.forRoot({
      apiKey: 'AIzaSyCzmUXpFk8e2YyzNvUzz0PJ12T5bCkKO7s'
    }),
    AgmSnazzyInfoWindowModule
  ],
  providers: [AuthService, AuthGaurdService, LoginService ],
  bootstrap: [AppComponent]
})
export class AppModule { }
