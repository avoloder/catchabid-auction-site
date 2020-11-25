import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {AuctionsComponent} from './auctions.component';
import {HttpClientModule} from '@angular/common/http';
import {AuctionsListComponent} from '../auctions-list/auctions-list.component';
import {AppRoutingModule} from "../../app-routing.module";
import {BrowserModule} from "@angular/platform-browser";
import {BackendAuctionsService} from "../auctions-list/backend-auctions.service";
import {CoordinatesModule} from 'angular-coordinates';


@NgModule({
  declarations: [AuctionsListComponent, AuctionsComponent],
  imports: [
    CommonModule,
    HttpClientModule,
    AppRoutingModule,
    BrowserModule,
    CoordinatesModule
  ],
  providers:[BackendAuctionsService],
  exports:[AuctionsListComponent]
})
export class AuctionsModule {
}
