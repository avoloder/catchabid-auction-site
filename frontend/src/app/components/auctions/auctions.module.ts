import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {AuctionsComponent} from './auctions.component';
import {HttpClientModule} from '@angular/common/http';
import {AppRoutingModule} from "../../app-routing.module";
import {BrowserModule} from "@angular/platform-browser";
import {AuctionsListComponent} from "./auctions-list/auctions-list.component";
import {AuctionFormComponent} from "./auction-form/auction-form.component";
import {ReactiveFormsModule} from "@angular/forms";
import {NgbModule} from "@ng-bootstrap/ng-bootstrap";


@NgModule({
  declarations: [AuctionsListComponent, AuctionsComponent, AuctionFormComponent],
  imports: [
    CommonModule,
    HttpClientModule,
    AppRoutingModule,
    BrowserModule,
    ReactiveFormsModule,
    NgbModule
  ],
  providers:[],
  exports:[AuctionsListComponent]
})
export class AuctionsModule {
}
