import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { User } from '../models/user';
import { AuctionHouse } from '../models/auctionhouse';

@Injectable()
export class PasswordManagementService {
    constructor(private http: HttpClient) { }

    endpoint = 'http://localhost:8080';

    requestPasswordReset(email: String) {
        return this.http.post(this.endpoint + '/requestPasswordReset', email);
    }

    sendResetPasswordToken(token: Number) {
        return this.http.post(this.endpoint + '/sendResetPasswordToken', token);
    }

    resetPassword(email: String, password:String){
        console.log("usla sam tu")
        return this.http.post(this.endpoint + '/resetPassword', {email: email, password: password});
    }

}