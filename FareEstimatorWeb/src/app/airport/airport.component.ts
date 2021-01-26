import { Component, OnInit,Inject, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { FormBuilder} from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import {MatTableDataSource,MAT_DIALOG_DATA,MatPaginator,MatSort} from '@angular/material';
import { AlertService } from 'ngx-alerts';
import { NgxSpinnerService } from 'ngx-spinner';

export interface PeriodicElement {
  code: string;
  name: string;
  country: string;
}

@Component({
  selector: 'app-airport',
  templateUrl: './airport.component.html',
  styleUrls: ['./airport.component.css']
})
export class AirportComponent implements OnInit {

  dataSource:any;
  tableShow:boolean;
  noRecord:boolean;
  displayedColumns: string[] = ['code', 'name', 'country'];
  @ViewChild(MatPaginator,{static: false}) paginator: MatPaginator;
  @ViewChild(MatSort,{static: false}) sort: MatSort;

  constructor(@Inject(MAT_DIALOG_DATA) public result: any,
              private router:Router,
              private fb: FormBuilder,
              private http:HttpClient,
              private spinner: NgxSpinnerService,
              private alertService: AlertService) { }

  ngOnInit() {
    this.findAllAirports();
  }

  findAllAirports(){
    this.spinner.show();
    this.http.get('/api/airports').subscribe(result => {
      this.spinner.hide();
        if (<any>result !=null) {
          this.tableShow = true;
          this.noRecord = false;
          this.dataSource = new MatTableDataSource<PeriodicElement>(<any>result);
          setTimeout(() => {
            this.dataSource.paginator = this.paginator;
            this.dataSource.sort = this.sort;
          }, 0);
        } else {
          this.tableShow = false;
          this.noRecord = true;
          this.alertService.danger("No airport found");
        }
      },
      (error) => {
        this.spinner.hide();
        this.alertService.danger("Error finding airport");
      })
  }

}
