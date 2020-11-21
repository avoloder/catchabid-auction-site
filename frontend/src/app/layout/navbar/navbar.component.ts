import { Component, OnInit } from '@angular/core';
import {ModalDismissReasons, NgbModal} from '@ng-bootstrap/ng-bootstrap';
import {AuctionsComponent} from '../../auctions/auctions.component';
import {modalConfigDefaults} from 'ngx-bootstrap/modal/modal-options.class';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss']
})
export class NavbarComponent implements OnInit {

  public focus;

  signedIn = false;

  closeResult = '';
  constructor(private modalService: NgbModal) { }

  ngOnInit(): void {
  }

  changeLogin(): void {
    this.signedIn = !this.signedIn;
  }

  createAuction(): void {
    this.modalService.open(AuctionsComponent, { size: 'lg', backdrop: 'static' });
  }

  private getDismissReason(reason: any): string {
    if (reason === ModalDismissReasons.ESC) {
      return 'by pressing ESC';
    } else if (reason === ModalDismissReasons.BACKDROP_CLICK) {
      return 'by clicking on a backdrop';
    } else {
      return `with: ${reason}`;
    }
  }
}
