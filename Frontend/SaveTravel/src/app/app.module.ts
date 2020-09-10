import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ServicesComponent } from './services/services.component';
import { AllTravelWarningsComponent } from './all-travel-warnings/all-travel-warnings.component';

@NgModule({
  declarations: [
    AppComponent,
    ServicesComponent,
    AllTravelWarningsComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
