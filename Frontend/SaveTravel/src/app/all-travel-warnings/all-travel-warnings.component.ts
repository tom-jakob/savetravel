import { Component, OnInit } from '@angular/core';
import { CountryDataServiceService } from '../service/country-data-service.service';
import { TravelWarningObject } from '../Models/TravelWarningObject';
import { CountryCode } from '../Models/CountryCode';

@Component({
  selector: 'app-all-travel-warnings',
  templateUrl: './all-travel-warnings.component.html',
  styleUrls: ['./all-travel-warnings.component.css']
})
export class AllTravelWarningsComponent implements OnInit {

travelWarningObject = new TravelWarningObject;
countryCodeMap = new Map<string, CountryCode>();

  constructor(private countryDataService: CountryDataServiceService) { }

  ngOnInit(): void {

    this.countryDataService.getAllTWs().subscribe(fetchedTWO => (this.travelWarningObject = fetchedTWO));
           
  }

}
