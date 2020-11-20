import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { User } from '../models/user';
import { AuctionHouse } from '../models/auctionhouse';

@Injectable()
export class RegisterService {
    constructor(private http: HttpClient) { }

    registerAuctionHouse(auctionHouse: AuctionHouse) {
        return this.http.post('/registerAuctionHouse', auctionHouse);
    }

    registerUser(user: User) {
        return this.http.post('/registerUser', user);
    }
}