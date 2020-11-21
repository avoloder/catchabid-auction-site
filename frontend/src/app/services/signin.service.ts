import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { AuctionHouse } from '../models/auctionhouse';
import { User } from '../models/user';

@Injectable()
export class SigninService {
    constructor(private http: HttpClient) { }

    endpoint = 'http://localhost:8080';

    login(email: String, password: String) {
        return this.http.post(this.endpoint + '/login', {email: email, passwod: password});
    }
}