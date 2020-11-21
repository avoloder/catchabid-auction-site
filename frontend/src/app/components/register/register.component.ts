import { Component, OnInit } from '@angular/core';
import { Route } from '@angular/compiler/src/core';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { SigninComponent } from '../signin/signin.component';
import { RegisterService } from '../../services/register.service';
import { Address } from 'src/app/models/address';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss'],
})
export class RegisterComponent {

  model: any = {};
  address: Address = new Address();

  constructor(
      private registerService: RegisterService,
      private modalService: NgbModal) { }

  openLoginModal() {
      this.modalService.dismissAll();
      this.modalService.open(SigninComponent);
  }

  registerUser() {
    let phoneNumber = +this.model.phoneNumber;
    let finalPhoneNumber = this.model.areaCode + phoneNumber;
    this.model.phoneNr = finalPhoneNumber;
    this.address.city = this.model.city;
    this.address.country = this.model.country;
    this.address.street = this.model.street;
    this.address.houseNr = this.model.houseNr;
    this.model.address = this.address;
    this.registerService.registerUser(this.model)
        .subscribe(
            data => {
                this.openLoginModal();
            },
            error => {
              console.log(error);
            });
  }

  registerAuctionHouse() {
      let phoneNumber = +this.model.phoneNumber;
      let finalPhoneNumber = this.model.areaCode + phoneNumber;
      this.model.phoneNr = finalPhoneNumber;
      this.address.city = this.model.city;
      this.address.country = this.model.country;
      this.address.street = this.model.street;
      this.address.houseNr = this.model.houseNr;
      this.model.address = this.address;
      this.registerService.registerAuctionHouse(this.model)
          .subscribe(
              data => {
                  this.openLoginModal();
              },
              error => {
                console.log(error);
              });
  }
}
