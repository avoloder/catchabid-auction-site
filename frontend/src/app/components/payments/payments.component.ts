import { Component, OnInit, ViewChild, Input } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from "@angular/forms";

import { StripeService, StripeCardComponent } from 'ngx-stripe';
import {
  StripeCardElementOptions,
  StripeElementsOptions
} from '@stripe/stripe-js';
import { AuctionPost } from 'src/app/models/auctionpost';
import { PaymentsService } from 'src/app/services/payments.service';
import { LoadingSpinnerService } from 'src/app/services/loading-spinner.service';
import { PaymentIntent } from 'src/app/models/paymentIntent';
import { switchMap } from 'rxjs/operators';

@Component({
  selector: 'app-payments',
  templateUrl: './payments.component.html',
  styleUrls: ['./payments.component.css']
})
export class PaymentsComponent implements OnInit {

  @ViewChild(StripeCardComponent) card: StripeCardComponent;

  @Input() auction: AuctionPost;

  cardOptions: StripeCardElementOptions = {
    style: {
      base: {
        iconColor: '#666EE8',
        color: '#31325F',
        fontWeight: '300',
        fontFamily: '"Helvetica Neue", Helvetica, sans-serif',
        fontSize: '18px',
        '::placeholder': {
          color: '#CFD7E0'
        }
      }
    }
  };

  elementsOptions: StripeElementsOptions = {
    locale: 'en'
  };

  stripeTest: FormGroup;

  paymentIntent: PaymentIntent;

  constructor(
    private fb: FormBuilder,
    private stripeService: StripeService,
    private paymentService: PaymentsService,
    private _loadingSpinnerService: LoadingSpinnerService) {}

  ngOnInit(): void {
    this.stripeTest = this.fb.group({
      name: ['', [Validators.required]]
    });
  }

  processPayment(): void {
    this.paymentService.createPaymentIntent(this.auction.id)
      .pipe(
        switchMap(
          (pi) => this.stripeService.confirmCardPayment(pi.clientSecret, {
            payment_method: {
              card: this.card.element,
              billing_details: {
                name: 'Stefan Puhalo'
              },
            },
          })
        )
      )
      .subscribe(
        (result) => console.log(result),
        (error) => console.log(error)
      );




    // this.stripeService
    //   .confirmPaymentIntent(this.paymentIntent.clientSecret,
    //     this.card.element, { name })
    //   .subscribe((result) => {
    //     if (result.paymentIntent) {
    //       console.log(result);
    //     } else if (result.error) {
    //       console.log(result.error.message);
    //     }
    //   });
  }

  // createPaymentIntent(): void  {
  //   this.paymentService.createPaymentIntent(
  //     this.auction.id).subscribe(
  //       (paymentIntentRes) => {
  //         console.log(paymentIntentRes)
  //         this.paymentIntent = paymentIntentRes;
  //         this._loadingSpinnerService.hide();
  //       },
  //       (err) => console.log(err)
  //     );
  // }

}
