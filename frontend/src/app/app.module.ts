import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {AppComponent} from './app.component';
import { AppRoutingModule } from './app-routing.module';
import { ToastrModule } from 'ngx-toastr';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import {Ng2TelInputModule} from 'ng2-tel-input';
import { SigninComponent } from './components/signin/signin.component';
import { RegisterComponent } from './components/register/register.component';
import { LayoutModule } from './components/layout/layout.module';
import { AuctionsModule } from './components/auctions/auctions.module';
import { AuthGuard } from './services/auth-guard.service';
import { RegisterService } from './services/register.service';

@NgModule({
  declarations: [
    AppComponent,
    SigninComponent,
    RegisterComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    BrowserAnimationsModule,
    ToastrModule.forRoot(),
    LayoutModule,
    AuctionsModule,
    NgbModule,
    Ng2TelInputModule
  ],
  providers: [AuthGuard, RegisterService],
  bootstrap: [AppComponent]
})
export class AppModule {
}
