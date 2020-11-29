import { Component, OnInit, Input } from '@angular/core';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { ResetPasswordTokenComponent } from '../reset-password-token/reset-password-token.component';
import { PasswordManagementService } from '../../../services/password-management.service';

@Component({
  selector: 'app-reset-password',
  templateUrl: './reset-password.component.html',
  styleUrls: ['./reset-password.component.scss']
})
export class ResetPasswordComponent implements OnInit {

  password: String;

  @Input() 
  public email: String;

  constructor(
    private modalService: NgbModal,
    private passwordManagementService: PasswordManagementService
  ) { }

  ngOnInit(): void {
    const togglePassword = document.querySelector('#togglePassword');
    const toggleConfirmPassword = document.querySelector('#toggleConfirmPassword');
    const password = document.querySelector('#password');
    const confirmPassword = document.querySelector('#confirmPassword');

    togglePassword.addEventListener('click', function (e) {
      // toggle the type attribute
      const type = password.getAttribute('type') === 'password' ? 'text' : 'password';
      password.setAttribute('type', type);
      // toggle the eye slash icon
      this.classList.toggle('fa-eye-slash');
    });

    toggleConfirmPassword.addEventListener('click', function (e) {
      // toggle the type attribute
      const type = confirmPassword.getAttribute('type') === 'password' ? 'text' : 'password';
      confirmPassword.setAttribute('type', type);
      // toggle the eye slash icon
      this.classList.toggle('fa-eye-slash');
  });
  }

  resetPassword(){
    this.passwordManagementService.resetPassword(this.email, this.password)
    .subscribe(
      data => {
        this.modalService.dismissAll();
      },
      error => {
        console.log(error);
      });
  }
}
