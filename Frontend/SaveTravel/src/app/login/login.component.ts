import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { map } from 'rxjs/operators';


import { ActivatedRoute, Router } from '@angular/router';
import { Observable } from 'rxjs';
import { AppService } from '../service/app.service';

@Component({
    selector: 'app-login',
    templateUrl: './login.component.html',
    styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
    model: any = {};
    credentials = {username: '', password: ''};
    error: Boolean = false;

    constructor(
        private route: ActivatedRoute,
        private router: Router,
        private http: HttpClient,
        private app: AppService

    ) { }

    ngOnInit() {
        sessionStorage.setItem('token', '');
    }

    // login() {
    //     let url = 'http://localhost:8080/login';
    //     this.http.post<Observable<boolean>>(url, {
    //         userName: this.model.username,
    //         password: this.model.password
    //     }).subscribe(isValid => {
    //         if (isValid) {
    //             sessionStorage.setItem(
    //                 'token',
    //                 btoa(this.model.username + ':' + this.model.password)
    //             );
    //             this.router.navigate(['savetravel']);
    //         } else {
    //             alert("Authentication failed.")
    //         }
    //     });
    // }

  
    login() {
      this.app.authenticate(this.credentials, () => {
          this.router.navigateByUrl('/savetravel');
      });
      return false;
    }
  
  }



