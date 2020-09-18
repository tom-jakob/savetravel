import { Component, OnInit } from '@angular/core';
import { CountryID } from '../Models/CountryID';
import { TravelWarningObject } from '../Models/TravelWarningObject';
import { CountryDataServiceService } from '../service/country-data-service.service';

@Component({
  selector: 'app-single-travel-warning',
  templateUrl: './single-travel-warning.component.html',
  styleUrls: ['./single-travel-warning.component.css']
})
export class SingleTravelWarningComponent implements OnInit {

countryList: CountryID = new CountryID;
toggleShowDetails:boolean = false;
countryCode:string;
travelWarningObject:TravelWarningObject = new TravelWarningObject;

  constructor(private countryDataService:CountryDataServiceService) { }

  ngOnInit(): void {
  }


public showCountryDetails(countryCode:string){
  this.countryCode = countryCode;
this.toggleShowDetails = true;
this.countryDataService.getSingleTW(this.countryCode)
.subscribe(fetchedTWO => (this.travelWarningObject = fetchedTWO)); 

}

}
