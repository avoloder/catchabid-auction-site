import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {AppComponent} from './app.component';
import { AppRoutingModule } from './app-routing.module';
import { ToastrModule } from 'ngx-toastr';
import { LayoutModule } from './layout/layout.module';
import { AuthGuard } from './shared/services/auth-guard.service';
import { AuctionsModule } from './auctions/auctions.module';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';

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
    NgbModule
  ],
  providers: [AuthGuard],
  bootstrap: [AppComponent]
})
export class AppModule {
}
