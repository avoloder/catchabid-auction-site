import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {AuctionPostModel} from '../models/auctionPost.model';
import {Observable} from 'rxjs';

const endpoint = 'http://localhost:8080';

@Injectable({
  providedIn: 'root'
})
export class AuctionsService {


  constructor(private http: HttpClient) {}

  saveAuction(auctionPost: AuctionPostModel): Observable<AuctionPostModel> {
    return this.http.post<AuctionPostModel>(endpoint + '/api/auctions', auctionPost);
  }

}
