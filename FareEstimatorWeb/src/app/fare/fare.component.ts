import { Component, OnInit, Inject } from '@angular/core';
import { Router } from '@angular/router';
import { FormBuilder, Validators,FormGroup, RequiredValidator } from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import {MatTableDataSource,MAT_DIALOG_DATA,MatAutocompleteModule} from '@angular/material';
import {Observable} from 'rxjs';
import {startWith} from 'rxjs/operators';
import {map} from 'rxjs/operators';
import { AlertService } from 'ngx-alerts';
import { NgxSpinnerService } from 'ngx-spinner';

export interface PeriodicElement {
  amount: string;
  currency: string;
  origin: string;
  destination:string;
}
export interface Airport {
  code: string;
  name: string;
  country:string
}

@Component({
  selector: 'app-fare',
  templateUrl: './fare.component.html',
  styleUrls: ['./fare.component.css']
})
export class FareComponent implements OnInit {

  myForm: FormGroup;
  origin:string;
  destination:string;
  dataSource:any;
  tableShow:boolean;
  noRecord:boolean;
  displayedColumns: string[] = ['origin', 'destination','amount', 'currency' ];

  options:Airport[]=[];
  filteredOptions: Observable<Airport[]>;
  filteredOptions1:Observable<Airport[]>;


  constructor(
    @Inject(MAT_DIALOG_DATA) public result: any,
    private spinner: NgxSpinnerService,
    private router:Router,
    private http:HttpClient,
    private fb: FormBuilder,
    private alertService: AlertService) {
    this.http.get('/api/airports').subscribe(result=>{
      for(let prop in result){
        this.options.push(result[prop]);
      }
      this.filter();
    });

  }

  reInitialize() {
    this.myForm = this.fb.group({
      originCode: ['', Validators.required],
      destinationCode: ['',Validators.required],
    })

  }

  ngOnInit() {
    this.reInitialize();
  }

  get f() {
    return this.myForm.controls;
  }

  filter(){
    this.filteredOptions = this.myForm.controls['originCode'].valueChanges
      .pipe(
        startWith<string | Airport>(''),
        map(value => typeof value === 'string' ? value : value.code),
        map(code => code ? this._filter(code) : this.options.slice())
      );
    this.filteredOptions1 = this.myForm.controls['destinationCode'].valueChanges
      .pipe(
        startWith<string | Airport>(''),
        map(value => typeof value === 'string' ? value : value.code),
        map(code => code ? this._filter(code) : this.options.slice())
      );
  }

  displayFn(airport?: Airport): string | undefined {
    return airport ? airport.code : undefined;
  }
  private _filter(code: string): Airport[] {
    const filterValue = code.toLowerCase();
    return this.options.filter(option => option.code.toLowerCase().indexOf(filterValue) === 0);
  }

  onSubmit() {

    if(this.myForm.controls['originCode'].hasError('required')||this.myForm.controls['destinationCode'].hasError('required')){
      return;
    }else if(this.myForm.controls['originCode'].value.code == undefined ||this.myForm.controls['destinationCode'].value.code == undefined){
      return;
    }else{
      this.spinner.show();
      let origin = this.myForm.controls['originCode'].value.code;
      let destination = this.myForm.controls['destinationCode'].value.code;

      this.http.get('/api/getFare',  {
        params: {
          origin: origin,
          destination: destination
        }
      })
        .subscribe(result => {
            this.spinner.hide();
            if (<any>result !=null) {
              let data :any[] = [];
              data.push(result);
              this.tableShow = true;
              this.noRecord = false;
              this.dataSource = new MatTableDataSource<PeriodicElement>(<any>data);
            } else {
              this.tableShow = false;
              this.noRecord = true;
            }
          },
          (error) => {
            this.spinner.hide();
            this.alertService.danger("Error in estimating fare");

          })
    }
  }

  validateOrigin() {
    let origin = this.myForm.controls['originCode'].value.code;
    let airportCode = [];
    this.filteredOptions.subscribe(code => {
      airportCode = code as any[]
    })
    if (!airportCode.includes(origin)) {
      this.myForm.controls['originCode'].setErrors({'incorrect': true});
      return false;
    } else {
      this.myForm.controls['originCode'].setErrors(null);
    }
  }

  validateDestination() {
    let destination = this.myForm.controls['destinationCode'].value.code;
    let airportCode = [];
    this.filteredOptions.subscribe(code => {
      airportCode = code as any[]
    })
    if (!airportCode.includes(destination)) {
      this.myForm.controls['destinationCode'].setErrors({'incorrect': true});
      return false;
    } else {
      this.myForm.controls['destinationCode'].setErrors(null);
    }
  }
}
