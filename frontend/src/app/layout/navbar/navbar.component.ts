import { Component, OnInit } from '@angular/core';
import {ModalDismissReasons, NgbModal, NgbModalRef} from '@ng-bootstrap/ng-bootstrap';
import { AuctionFormComponent } from 'src/app/auctions/auction-form/auction-form.component';
import { AuctionsService } from 'src/app/auctions/auctions.service';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss']
})
export class NavbarComponent implements OnInit {

  public focus;

  signedIn = false;

  closeResult = '';

  auctionClosedSub: Subscription;

  modalRef: NgbModalRef;

  constructor(
    private modalService: NgbModal,
    private auctionsService: AuctionsService) { }

  ngOnInit(): void {
  }

  changeLogin(): void {
    this.signedIn = !this.signedIn;
  }

  createAuction(): void {
    this.modalRef = this.modalService.open(AuctionFormComponent, { size: 'lg', backdrop: 'static' });
    this.auctionClosedSub = this.auctionsService.auctionFormModalClosed.subscribe(
      () => this.onAuctionFormClose()
    );
  }

  onAuctionFormClose(): void {
    this.modalRef.close();
    this.auctionClosedSub.unsubscribe();
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
