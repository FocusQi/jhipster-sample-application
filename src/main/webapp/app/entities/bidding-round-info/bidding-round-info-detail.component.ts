import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IBiddingRoundInfo } from 'app/shared/model/bidding-round-info.model';

@Component({
  selector: 'jhi-bidding-round-info-detail',
  templateUrl: './bidding-round-info-detail.component.html',
})
export class BiddingRoundInfoDetailComponent implements OnInit {
  biddingRoundInfo: IBiddingRoundInfo | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ biddingRoundInfo }) => (this.biddingRoundInfo = biddingRoundInfo));
  }

  previousState(): void {
    window.history.back();
  }
}
