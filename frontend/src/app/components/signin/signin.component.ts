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

  email: String;
  password: String;

  constructor(
    private modalService: NgbModal,
    private signinService: SigninService,
  ) { }

  ngOnInit(): void {
    const togglePassword = document.querySelector('#togglePassword');
    const password = document.querySelector('#password');

    togglePassword.addEventListener('click', function (e) {
      // toggle the type attribute
      const type = password.getAttribute('type') === 'password' ? 'text' : 'password';
      password.setAttribute('type', type);
      // toggle the eye slash icon
      this.classList.toggle('fa-eye-slash');
    });
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
    this.signinService.login(this.email, this.password)
        .subscribe(
            data => {
              console.log(data)
              this.modalService.dismissAll(); 
            },
            error => {
              console.log("greska")
              console.log(error);
            });
  }

}
