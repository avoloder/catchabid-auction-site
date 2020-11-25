import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {AppComponent} from './app.component';
import { AppRoutingModule } from './app-routing.module';
import { ToastrModule } from 'ngx-toastr';
import { LayoutModule } from './layout/layout.module';
import { AuthGuard } from './shared/services/auth-guard.service';
import { AuctionsModule } from './auctions/auctions.module';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { SigninComponent } from './components/signin/signin.component';
import { RegisterComponent } from './components/register/register.component';
import { LayoutModule } from './components/layout/layout.module';
import { RegisterService } from './services/register.service';
import { SigninService } from './services/signin.service';
import { ResetPasswordComponent } from './components/signin/reset-password/reset-password.component';
import { ResetPasswordTokenComponent } from './components/signin/reset-password-token/reset-password-token.component';
import { ForgotPasswordComponent } from './components/signin/forgot-password/forgot-password.component';
import { PasswordManagementService } from './services/password-management.service';
import { TokenInterceptor } from './services/token-interceptor.service';
import { UserService } from './services/user.service';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent
    AppComponent,
    SigninComponent,
    RegisterComponent,
    SigninComponent,
    ResetPasswordComponent,
    ResetPasswordTokenComponent,
    ForgotPasswordComponent
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
  providers: [
    AuthGuard,
    RegisterService,
    SigninService,
    UserService,
    PasswordManagementService,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: TokenInterceptor,
      multi: true
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
