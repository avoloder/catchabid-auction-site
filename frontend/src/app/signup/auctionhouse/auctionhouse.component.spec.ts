import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AuctionhouseComponent } from './auctionhouse.component';

describe('AuctionhouseComponent', () => {
  let component: AuctionhouseComponent;
  let fixture: ComponentFixture<AuctionhouseComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AuctionhouseComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AuctionhouseComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
