import { Component, OnInit } from '@angular/core';
import { Route } from '@angular/compiler/src/core';
import { Router, RouterModule } from '@angular/router';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { SigninComponent } from '../signin/signin.component';
import { RegisterService } from '../../services/register.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss'],
})
export class RegisterComponent {

  model: any = {};

  constructor(
      private router: Router,
      private registerService: RegisterService,
      private modalService: NgbModal) { }

  openLoginModal() {
      this.modalService.dismissAll();
      this.modalService.open(SigninComponent);
  }

  registerUser() {
    console.log("saving user")
  }

  registerAuctionHouse() {
      let phoneNumber = +this.model.phoneNumber
      let finalPhoneNumber = this.model.areaCode + phoneNumber
      this.model.phoneNumber = finalPhoneNumber
      this.registerService.registerAuctionHouse(this.model)
          .subscribe(
              data => {
                  this.openLoginModal()
              },
              error => {
                console.log(error)
              });
  }

}
