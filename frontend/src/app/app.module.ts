import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {AppComponent} from './app.component';
import { AppRoutingModule } from './app-routing.module';
import { ToastrModule } from 'ngx-toastr';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { LayoutModule } from './components/layout/layout.module';
import { AuctionsModule } from './components/auctions/auctions.module';
import { AuthGuard } from './services/auth/auth-guard.service';
import {HomeModule} from "./components/home/home.module";
import {AuctionsService} from "./services/auctionsService.service";

@NgModule({
  declarations: [
    AppComponent
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
    HomeModule
  ],
  providers: [AuthGuard, AuctionsService],
  bootstrap: [AppComponent]
})
export class AppModule {
}
