import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { AuctionHouse } from '../models/auctionhouse';
import { User } from '../models/user';

@Injectable()
export class SigninService {
    constructor(private http: HttpClient) { }

    endpoint = 'http://localhost:8080';

    login(user: User | AuctionHouse) {
        return this.http.post(this.endpoint + '/login', user);
    }
}