import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders, HttpParams} from '@angular/common/http';
import {AuctionPost} from "./auctions-list.component";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class BackendAuctionsService {
  private REST_API_SERVER = "http://localhost:8080";

  constructor(private httpClient: HttpClient) {
  }

  public sendGetRequest(pageNumber: number, pageSize:number): Observable<any> {
    const headers = new HttpHeaders();
    const params = new HttpParams()
      .set("pageNumber","1")
      .set("pageSize", pageSize.toString());

    const request:string = this.REST_API_SERVER + "/api/auctions/upcoming?pageNr=" + pageNumber +"&pageSize=" + pageSize;

    return this.httpClient.get<Array<AuctionPost>>(request,{params});
  }
}
