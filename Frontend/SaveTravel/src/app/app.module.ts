import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AllTravelWarningsComponent } from './all-travel-warnings/all-travel-warnings.component';
import { HttpClientModule } from '@angular/common/http';
import { SingleTravelWarningComponent } from './single-travel-warning/single-travel-warning.component';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms'; 
import { CreateTravelListComponent } from './create-travel-list/create-travel-list.component';
import { LoginComponent } from './login/login.component';
import { AppService } from './service/app.service';
import { UserService } from './service/user.service';
import { authInterceptorProviders } from 'src/_helpers/auth.interceptor';
import { RegisterComponent } from './register/register.component';
import { ProfileComponent } from './profile/profile.component';
import { HomeComponent } from './home/home.component';
import { BoardAdminComponent } from './board-admin/board-admin.component';
import { BoardModeratorComponent } from './board-moderator/board-moderator.component';
import { BoardUserComponent } from './board-user/board-user.component';




@NgModule({
  declarations: [
    AppComponent,
    AllTravelWarningsComponent,
    SingleTravelWarningComponent,
    CreateTravelListComponent,
    LoginComponent,
    RegisterComponent,
    ProfileComponent,
    HomeComponent,
    BoardAdminComponent,
    BoardModeratorComponent,
    BoardUserComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    ],
  providers: [AppService, UserService, authInterceptorProviders],
  bootstrap: [AppComponent]
})
export class AppModule { }
