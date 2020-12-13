import {Injectable} from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';
import {Observable, Subject} from "rxjs";
import 'rxjs/add/operator/map'
import { AuctionPost } from '../models/auctionpost';

const api = 'api/auctions';

@Injectable({
  providedIn: 'root'
})
export class AuctionsService {
  auctionFormModalClosed: Subject<void> = new Subject<void>();
  auctionDetailModalClosed: Subject<void> = new Subject<void>();

  private REST_API_SERVER: string = "http://localhost:8080/";

  constructor(private http: HttpClient) {
  }

  getRecentPosts(pageNumber: number, pageSize: number): Observable<Array<AuctionPost>> {
    const params = new HttpParams()
      .set("pageNumber", pageNumber.toString())
      .set("auctionsPerPage", pageSize.toString())
      .set("userEmail", localStorage.getItem("email"));

    return this.http.get<Array<AuctionPost>>(this.REST_API_SERVER + api + "/recent", {params});
  }

  public getUpcomingRequests(pageNumber: number, pageSize: number): Observable<Array<AuctionPost>> {
    const params = new HttpParams()
      .set("pageNumber", pageNumber.toString())
      .set("pageSize", pageSize.toString())
      .set("userEmail", localStorage.getItem("email"));

    const request: string = this.REST_API_SERVER + api + "/upcoming";

    return this.http.get<Array<AuctionPost>>(request, {params});
  }


  saveAuction(auctionPost: AuctionPost): Observable<AuctionPost> {
    return this.http.post<AuctionPost>(api, auctionPost);
  }

  getCategories() : Observable<Object> {
    return this.http.get(this.REST_API_SERVER + api + "/getCategories");
  }
}
