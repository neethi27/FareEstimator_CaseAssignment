import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import {HttpClientModule} from "@angular/common/http";
import { ReactiveFormsModule} from "@angular/forms";
import { AlertModule } from 'ngx-alerts';
import { NgxSpinnerModule } from 'ngx-spinner';


import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './home/home.component';
import { AirportComponent } from './airport/airport.component';
import { FareComponent } from './fare/fare.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {
  MatMenuModule,
  MatPaginatorModule,
  MatIconModule,
  MatTableModule,
  MAT_DIALOG_DATA,
  MatFormFieldModule,
  MatInputModule,
  MatDialogModule,
  MatButtonModule,
  MatCheckboxModule,
  MatSortModule
} from '@angular/material';

import {MatAutocompleteModule} from '@angular/material/autocomplete';
import { StatisticComponent } from './statistic/statistic.component';


@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    AirportComponent,
    FareComponent,
    StatisticComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    BrowserAnimationsModule,
    ReactiveFormsModule,
    AlertModule.forRoot({maxMessages: 1, timeout: 6000}),
    NgxSpinnerModule,
    MatIconModule,
    MatDialogModule,
    MatFormFieldModule,
    MatButtonModule,
    MatInputModule,
    MatCheckboxModule,
    MatTableModule,
    MatAutocompleteModule,
    MatPaginatorModule,
    MatMenuModule,
    MatSortModule
  ],
  providers: [{ provide: MAT_DIALOG_DATA, useValue: {} }],
  bootstrap: [AppComponent]
})
export class AppModule { }
