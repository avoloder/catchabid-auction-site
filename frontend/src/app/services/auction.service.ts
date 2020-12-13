import {Injectable} from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';
import {Observable, Subject} from "rxjs";
import {AuctionPostModel} from "../models/auctionPost.model";
import 'rxjs/add/operator/map'
import {ContactForm} from "../models/contact-form";

const api = 'api/auctions';

@Injectable({
  providedIn: 'root'
})
export class AuctionsService {
  auctionFormModalClosed: Subject<void> = new Subject<void>();

  private REST_API_SERVER: string = "http://localhost:8080/";

  constructor(private http: HttpClient) {
  }

  getRecentPosts(pageNumber: number, pageSize: number): Observable<Array<AuctionPostModel>> {
    const params = new HttpParams()
      .set("pageNumber", pageNumber.toString())
      .set("auctionsPerPage", pageSize.toString());

    return this.http.get<Array<AuctionPostModel>>(this.REST_API_SERVER + api + "/recent", {params});
  }

  public getUpcomingRequests(pageNumber: number, pageSize: number): Observable<Array<AuctionPostModel>> {
    const params = new HttpParams()
      .set("pageNumber", pageNumber.toString())
      .set("pageSize", pageSize.toString());

    const request: string = this.REST_API_SERVER + api + "/upcoming";

    return this.http.get<Array<AuctionPostModel>>(request, {params});
  }


  saveAuction(auctionPost: AuctionPostModel): Observable<AuctionPostModel> {
    return this.http.post<AuctionPostModel>(api, auctionPost);
  }

  getCategories(): Observable<Object> {
    return this.http.get(this.REST_API_SERVER + api + "/getCategories");
  }

  postContactForm(contactForm: ContactForm): Observable<ContactForm> {
    return this.http.post<ContactForm>(this.REST_API_SERVER + api + '/postContactForm', contactForm);
  }
}
