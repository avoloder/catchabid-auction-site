import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {AuctionsService} from '../auctions.service';
import {AuctionPostModel} from '../../models/auctionPost.model';
import {ToastrService} from 'ngx-toastr';

@Component({
  selector: 'app-auction-form',
  templateUrl: './auction-form.component.html',
  styleUrls: ['./auction-form.component.css']
})
export class AuctionFormComponent implements OnInit {

  image = 'Choose file';
  imgPreview = 'assets/img/placeholder-image-300x225.png';
  imageFile: File;
  nameForm = new FormControl('', [Validators.required]);
  categoryForm = new FormControl('', [Validators.required]);
  startDateForm = new FormControl('', [Validators.required]);
  startTimeForm = new FormControl('', [Validators.required]);
  endDateForm = new FormControl('', [Validators.required]);
  endTimeForm = new FormControl('', [Validators.required, Validators.min(0)]);
  locationForm = new FormControl();
  priceForm = new FormControl('', [Validators.required]);
  descriptionForm = new FormControl();
  imageForm = new FormControl('', [Validators.required]);
  form = new FormGroup({
    name: this.nameForm,
    category: this.categoryForm,
    startDate: this.startDateForm,
    startTime: this.startTimeForm,
    endDate: this.endDateForm,
    endTime: this.endTimeForm,
    location: this.locationForm,
    minPrice: this.priceForm,
    description: this.descriptionForm,
    image: this.imageForm
  });
  categories = ['jewelry', 'electronics', 'cars', 'experience', 'travel', 'furniture', 'music', 'other'];

  constructor(private auctionsService: AuctionsService, private toast: ToastrService) { }

  ngOnInit(): void {
    console.log('opened');
    // this.resetForm();
  }

  inputImage($event: Event): void {
    const file = ($event.target as HTMLInputElement).files[0];
    console.log(file);
    this.image = file.name;
    this.imgPreview = 'assets/img/image-test.png';
  }

  getErrorMessage(): string {
    return 'You must enter a value';
  }

  resetForm(): void {
    this.form.reset();
    this.nameForm.setErrors(null);
    // for (let conts in this.form.controls) {
    //   this.form.get(conts).setErrors(null);
    // }
  }

  onModalClose(): void {
    this.auctionsService.auctionFormModalClosed.next();
  }

  saveAuction(): void{
    const startDate = new Date(this.startDateForm.value.day, this.startDateForm.value.month,
      this.startDateForm.value.year, this.startTimeForm.value.hour, this.startTimeForm.value.minute);

    const endDate = new Date(this.endDateForm.value.day, this.endDateForm.value.month,
      this.endDateForm.value.year, this.endTimeForm.value.hour, this.endTimeForm.value.minute);

    const auctionPost = new AuctionPostModel(null, this.nameForm.value, this.categoryForm.value.toUpperCase(),
      new Date(startDate), new Date(endDate), this.priceForm.value, this.descriptionForm.value, this.imageFile);

    console.log(auctionPost);
    this.auctionsService.saveAuction(auctionPost).subscribe(post => {
      this.toast.success('Auction successfully saved');
    }, error => {
      this.toast.error('Failed to save the auction');
    });
    console.log(auctionPost);
  }

}
