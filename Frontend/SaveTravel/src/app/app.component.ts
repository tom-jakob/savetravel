import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AppService } from './service/app.service';
import { finalize } from "rxjs/operators";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})



export class AppComponent {
  title = 'SaveTravel';
  greeting:any = {};
  constructor(private http: HttpClient, private app:AppService, private router: Router) {
  this.app.authenticate(undefined, undefined);
  //  http.get('http://localhost:8080/hello').subscribe(data => this.greeting = data);
  }

  logout() {
    this.http.post('logout', {}).pipe(finalize(() => {
        this.app.authenticated = false;
        this.router.navigateByUrl('/login');
    })).subscribe();
  }
  
}
