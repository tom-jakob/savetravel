import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { AllInfoObject } from '../Models/AllInfoObject';
import { CountryID } from '../Models/CountryID';
import { TravelWarningObject } from '../Models/TravelWarningObject';
import { AppService } from '../service/app.service';
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
  greeting = {};

  constructor(private countryDataService: CountryDataServiceService, private app: AppService, private http: HttpClient) { 
    http.get('localhost:8080/hello').subscribe(data => this.greeting = data)
  }

  ngOnInit(): void {

  }

ngOnChanes(): void {
 
}

authenticated() { return this.app.authenticated; }

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
