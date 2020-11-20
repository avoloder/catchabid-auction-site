import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { AuctionHouse } from '../models/user';

@Injectable()
export class RegisterService {
    constructor(private http: HttpClient) { }

    registerAuctionHouse(auctionHouse: AuctionHouse) {
        return this.http.post('/api/auctionhouses', auctionHouse);
    }

    registerUser(auctionHouse: AuctionHouse) {
        return this.http.post('/api/auctionhouses', auctionHouse);
    }
}