import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { TravelWarningObject } from '../Models/TravelWarningObject';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CountryDataServiceService {

  constructor(private http: HttpClient) { }

rootApiPath:string = "http://localhost:8080/gettravelwarnings"


public getAllTWs(): Observable<TravelWarningObject> {
return this.http.get<TravelWarningObject>(this.rootApiPath);

}


}
