import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class PaymentsService {

  private api = 'api/payments';

  constructor(
    private http: HttpClient
  ) {
  }

  createPaymentIntent(auctionId: number): Observable<any> {
    const paymentIntentReq = {
      "auctionId": auctionId
    }
    return this.http.post(this.api, paymentIntentReq);
  }
}
