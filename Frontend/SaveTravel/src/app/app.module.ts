import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AllTravelWarningsComponent } from './all-travel-warnings/all-travel-warnings.component';
import { HttpClientModule } from '@angular/common/http';
import { SingleTravelWarningComponent } from './single-travel-warning/single-travel-warning.component';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms'; 
import { CommonModule } from '@angular/common';
import { CreateTravelListComponent } from './create-travel-list/create-travel-list.component';
import { LoginComponent } from './login/login.component';
import { AppService } from './service/app.service';
import { UserService } from './service/user.service';




@NgModule({
  declarations: [
    AppComponent,
    AllTravelWarningsComponent,
    SingleTravelWarningComponent,
    CreateTravelListComponent,
    LoginComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    CommonModule,
    FormsModule,
    ],
  providers: [AppService, UserService],
  bootstrap: [AppComponent]
})
export class AppModule { }
