import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { PaymentIntent } from '../models/paymentIntent';


@Injectable({
  providedIn: 'root'
})
export class PaymentsService {

  private api = 'api/payments';

  constructor(
    private http: HttpClient
  ) {
  }

  createPaymentIntent(auctionId: number): Observable<PaymentIntent> {
    const paymentIntentReq = {
      "auctionId": auctionId
    }
    return this.http.post<PaymentIntent>(this.api, paymentIntentReq);
  }
}
