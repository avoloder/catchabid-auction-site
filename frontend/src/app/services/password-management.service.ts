import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { User } from '../models/user';
import { AuctionHouse } from '../models/auctionhouse';

@Injectable()
export class PasswordManagementService {
    constructor(private http: HttpClient) { }

    endpoint = 'http://localhost:8080';

    requestPasswordReset(email: String) {
        console.log(email)
        return this.http.post(this.endpoint + '/requestPasswordReset', {email: email});
    }

    sendResetPasswordToken(token: Number) {
        console.log(token)
        return this.http.post(this.endpoint + '/sendResetPasswordToken', {passwordResetToken: token});
    }

    resetPassword(email: String, password:String){
        console.log(email, password)
        return this.http.post(this.endpoint + '/resetPassworf', {email: email, password: password});
    }

}