import {Injectable} from '@angular/core';
import {HttpClient, HttpParameterCodec, HttpParams} from '@angular/common/http';
import {Observable, Subject} from "rxjs";
import 'rxjs/add/operator/map';
import {AuctionPost} from '../models/auctionpost';
import {AuctionSearchQuery} from "../models/auctionSearchQuery";
import 'rxjs/add/operator/map'
import {ContactForm} from "../models/contact-form";

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

  public findAuctions(query: AuctionSearchQuery) {

    let params = new HttpParams()
      .set("pageNumber", query.pageNumber.toString())
      .set("pageSize", query.pageSize.toString())
      .set("categories", query.categories.join(","))
      .set("searchKeys", query.searchKeys.join(","))
      .set("countries", query.countries.join(","))
      .set("sortBy", query.sortBy)
      .set("sortOrder", query.sortOrder);

    if(query.auctionsStartUntil) {
      params = params.append("auctionsStartUntil", query.auctionsStartUntil.toISOString());
    }
    if(query.auctionsStartFrom) {
      params = params.append("auctionsStartFrom", (query.auctionsStartFrom.toISOString()));
    }
    if(query.auctionsEndFrom) {
      params = params.append("auctionsEndFrom", (query.auctionsEndFrom.toISOString()));
    }
    if(query.auctionsEndUntil) {
      params = params.append("auctionsEndUntil", (query.auctionsEndUntil.toISOString()));
    }
    return this.http.get<Array<AuctionPost>>(this.REST_API_SERVER + api, {params});
  }

  getRecentPosts(pageNumber: number, pageSize: number): Observable<Array<AuctionPost>> {
    const params = new HttpParams()
      .set("pageNumber", pageNumber.toString())
      .set("auctionsPerPage", pageSize.toString());

    return this.http.get<Array<AuctionPost>>(this.REST_API_SERVER + api + "/recent", {params});
  }

  public getUpcomingRequests(pageNumber: number, pageSize: number): Observable<Array<AuctionPost>> {
    const params = new HttpParams()
      .set("pageNumber", pageNumber.toString())
      .set("pageSize", pageSize.toString());

    const request: string = this.REST_API_SERVER + api + "/upcoming";

    return this.http.get<Array<AuctionPost>>(request, {params});
  }


  saveAuction(auctionPost: AuctionPost): Observable<AuctionPost> {
    return this.http.post<AuctionPost>(api, auctionPost);
  }

  getCategories(): Observable<Object> {
    return this.http.get(this.REST_API_SERVER + api + "/getCategories");
  }

  getCountriesWhereAuctionsExist() : Observable<Object> {
    return this.http.get(this.REST_API_SERVER + api + "/countriesWhereAuctionsExist");
  }

  postContactForm(contactForm: ContactForm): Observable<ContactForm> {
    return this.http.post<ContactForm>(this.REST_API_SERVER + api + '/postContactForm', contactForm);
  }
}
