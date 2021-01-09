import {NgModule} from '@angular/core';
import {NgbdModalConfirm, ProfileComponent} from "./profile.component";
import {CommonModule} from "@angular/common";
import {BrowserModule} from "@angular/platform-browser";
import {UpdatePasswordComponent} from './update-password/update-password.component';
import { NgMultiSelectDropDownModule } from 'ng-multiselect-dropdown';
import { FormsModule } from '@angular/forms';
import {AuctionsModule} from "../auctions/auctions.module";

@NgModule({
  declarations: [UpdatePasswordComponent, ProfileComponent, NgbdModalConfirm],
  imports: [
    BrowserModule,
    CommonModule,
    NgMultiSelectDropDownModule.forRoot(),
    FormsModule,
    AuctionsModule
  ]
})
export class ProfileModule {
}
