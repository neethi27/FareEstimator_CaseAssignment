import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {AirportComponent} from './airport/airport.component';
import {FareComponent} from './fare/fare.component';
import {StatisticComponent} from './statistic/statistic.component';

const routes: Routes = [
  { path: '', redirectTo: '', pathMatch: 'full'},
  { path : 'airport', component: AirportComponent },
  { path : 'fare', component: FareComponent },
  { path : 'dashboard', component: StatisticComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
