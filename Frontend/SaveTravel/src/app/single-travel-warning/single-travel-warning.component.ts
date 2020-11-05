import { Component, OnInit } from '@angular/core';
import { AllInfoObject } from '../Models/AllInfoObject';
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
  toggleShowDetails: boolean = false;
  countryCode: string;
  allInfoObject: AllInfoObject = new AllInfoObject;
  travelRoute: AllInfoObject[] = [];
  usersTravelRoute: AllInfoObject[] = [];

  constructor(private countryDataService: CountryDataServiceService) { }

  ngOnInit(): void {
this.getUsersTravelRoute();
  }

ngOnChanes(): void {
 
}


  public showCountryDetails(countryCode: string) {
    this.countryCode = countryCode;
    this.toggleShowDetails = true;
    this.countryDataService.getSingleTW(this.countryCode)
      .subscribe(fetchedAIO => (this.allInfoObject = fetchedAIO));

  }


  public saveAllInfoObject(): void {
    this.travelRoute.push(this.allInfoObject);
  }

  public saveTravelRoute(): void {
    this.countryDataService.saveTravelRoute(this.travelRoute).subscribe();
    this.getUsersTravelRoute();
  }

public getUsersTravelRoute(): void {
  const userId = 444;
  this.countryDataService.getTravelListForUser(userId).
    subscribe(fetchedTravelRoute => (this.usersTravelRoute = fetchedTravelRoute));

}

}
