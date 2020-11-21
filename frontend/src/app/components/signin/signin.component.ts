import { Component, OnInit, Output } from '@angular/core';
import { RegisterComponent } from '../register/register.component';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { EventEmitter } from '@angular/core';
import { SigninService } from '../../services/signin.service';
import { ForgotPasswordComponent } from './forgot-password/forgot-password.component';

@Component({
  selector: 'app-signin',
  templateUrl: './signin.component.html',
  styleUrls: ['./signin.component.scss']
})
export class SigninComponent implements OnInit {

  model: any = {};

  constructor(
    private modalService: NgbModal,
    private signinService: SigninService,
  ) { }

  ngOnInit(): void {
  }

  openRegisterModal(){
    this.modalService.dismissAll();
    this.modalService.open(RegisterComponent);
  }

  openForgotPasswordModal(){
    this.modalService.dismissAll();
    this.modalService.open(ForgotPasswordComponent);
  }

  login() {
    this.signinService.login(this.model)
        .subscribe(
            data => {
              this.modalService.dismissAll(); 
            },
            error => {
              console.log(error);
            });
  }

}
