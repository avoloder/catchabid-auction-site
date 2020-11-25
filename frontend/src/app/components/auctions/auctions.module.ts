import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {AuctionsComponent} from './auctions.component';
import {HttpClientModule} from '@angular/common/http';
import {AuctionsListComponent} from '../auctions-list/auctions-list.component';
import {AppRoutingModule} from "../../app-routing.module";
import {BrowserModule} from "@angular/platform-browser";


@NgModule({
  declarations: [AuctionsListComponent, AuctionsComponent],
  imports: [
    CommonModule,
    HttpClientModule,
    AppRoutingModule,
    BrowserModule
  ],
  providers:[],
  exports:[AuctionsListComponent]
})
export class AuctionsModule {
}
