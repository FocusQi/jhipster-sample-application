import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IBiddingVendorRound } from 'app/shared/model/bidding-vendor-round.model';

@Component({
  selector: 'jhi-bidding-vendor-round-detail',
  templateUrl: './bidding-vendor-round-detail.component.html',
})
export class BiddingVendorRoundDetailComponent implements OnInit {
  biddingVendorRound: IBiddingVendorRound | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ biddingVendorRound }) => (this.biddingVendorRound = biddingVendorRound));
  }

  previousState(): void {
    window.history.back();
  }
}
