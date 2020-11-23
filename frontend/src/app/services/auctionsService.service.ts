import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders, HttpParams} from '@angular/common/http';
import {AuctionModel} from "../model/auction.model";
import {Observable} from "rxjs";

const endpoint = 'http://localhost:8080/';

@Injectable({
  providedIn: 'root'
})
export class AuctionsService {

  private baseUrl:string = "http://localhost:8080/";

  constructor(private http: HttpClient) {}

  getRecentPosts(pageNumber:number, pageSize:number): Observable<AuctionModel[]> {
    const headers = new HttpHeaders();
    const params = new HttpParams()
      .set("pageNumber",pageNumber.toString())
      .set("auctionsPerPage", pageSize.toString());

    return this.http.get<AuctionModel[]>(this.baseUrl + "auction/recent",{params});
  }
}
