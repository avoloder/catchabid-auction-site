import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders, HttpParams} from '@angular/common/http';
import {AuctionPost} from "../model/auctionPost.model";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class AuctionsService {

  private REST_API_SERVER:string = "http://localhost:8080/";

  constructor(private http: HttpClient) {}

  getRecentPosts(pageNumber:number, pageSize:number): Observable<Array<AuctionPost>> {
    const params = new HttpParams()
      .set("pageNumber",pageNumber.toString())
      .set("auctionsPerPage", pageSize.toString());

    return this.http.get<Array<AuctionPost>>(this.REST_API_SERVER + "api/auctions/recent",{params});
  }

  public getUpcomingRequests(pageNumber: number, pageSize:number): Observable<Array<AuctionPost>> {
    const params = new HttpParams()
      .set("pageNumber", pageNumber.toString())
      .set("pageSize", pageSize.toString());

    const request:string = this.REST_API_SERVER + "api/auctions/upcoming";

    return this.http.get<Array<AuctionPost>>(request,{params});
  }
}
