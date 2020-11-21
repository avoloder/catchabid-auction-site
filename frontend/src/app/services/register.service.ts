import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { User } from '../models/user';
import { AuctionHouse } from '../models/auctionhouse';

@Injectable()
export class RegisterService {
    constructor(private http: HttpClient) { }

    endpoint = 'http://localhost:8080';

    registerAuctionHouse(auctionHouse: AuctionHouse) {
        return this.http.post(this.endpoint + '/registerHouse', auctionHouse);
    }

    registerUser(user: User) {
        return this.http.post(this.endpoint + '/registerUser', user);
    }
}