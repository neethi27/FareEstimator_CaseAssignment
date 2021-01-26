import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  constructor(private router:Router) { }

  onClickFareEstimator(): void {
    this.router.navigate(['/fare']);
  }

  onClickFindAirports():void{
    this.router.navigate(['/airport']);
  }

  onClickDashboard():void{
    this.router.navigate(['/dashboard']);
  }

  ngOnInit() {
  }

}
