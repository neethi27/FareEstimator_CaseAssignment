import {Component, OnInit} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import { AlertService } from 'ngx-alerts';

@Component({
  selector: 'app-statistic',
  templateUrl: './statistic.component.html',
  styleUrls: ['./statistic.component.css']
})

export class StatisticComponent implements OnInit {

  metric:any = {};

  constructor(private http: HttpClient,
              private alertService: AlertService) {
  }

  ngOnInit() {
    this.getTotalRequest();
  }

  getTotalRequest() {
    this.http.get('/api/statistics').subscribe(result => {

        if (<any>result != null) {
          this.metric = result;
        }
      }, (error) => {
          this.alertService.danger("Error in getting the metrics");
      }
    )
  }

}
