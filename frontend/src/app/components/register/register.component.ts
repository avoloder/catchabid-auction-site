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
      this.checkPass()
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

  checkPass(){
    //Store the password field objects into variables ...
    var pass1 = <HTMLInputElement>document.getElementById('password');
    var pass2 = <HTMLInputElement>document.getElementById('confirmPassword');
    //Store the Confimation Message Object ...
    var message = document.getElementById('confirmMessage');
    //Set the colors we will be using ...
    var goodColor = "#66cc66";
    var badColor = "#ff6666";
    //Compare the values in the password field 
    //and the confirmation field
    console.log(pass1)
    console.log(pass2)
    if(pass1.value == pass2.value){
        return true;
    }else{
        pass2.style.backgroundColor = "red";
        return false;
    } 
} 

}
