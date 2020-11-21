import { Component, OnInit } from '@angular/core';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { ResetPasswordTokenComponent } from '../reset-password-token/reset-password-token.component';
import { PasswordManagementService } from '../../../services/password-management.service';

@Component({
  selector: 'app-reset-password',
  templateUrl: './reset-password.component.html',
  styleUrls: ['./reset-password.component.scss']
})
export class ResetPasswordComponent implements OnInit {

  email: String;
  password: String;

  constructor(
    private modalService: NgbModal,
    private passwordManagementService: PasswordManagementService
  ) { }

  ngOnInit(): void {
  }

  resetPassword(){
    this.passwordManagementService.resetPassword(this.email, this.password)
    .subscribe(
      data => {
      },
      error => {
        console.log(error);
      });
  }
}
