import { TestBed } from '@angular/core/testing';

import { BackendAuctionsService } from './backend-auctions.service';

describe('BackendAuctionsService', () => {
  let service: BackendAuctionsService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(BackendAuctionsService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
