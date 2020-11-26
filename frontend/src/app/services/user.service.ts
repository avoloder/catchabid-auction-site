import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

const endpoint = 'http://localhost:8080';

@Injectable()
export class UserService {
    constructor(private http: HttpClient) { }
    
    findByEmail(email: String) {
        return this.http.get(endpoint + '/getUser').subscribe(x=> console.log(x));
    }
}