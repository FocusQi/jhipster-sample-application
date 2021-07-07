import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IBiddingOpener } from 'app/shared/model/bidding-opener.model';

@Component({
  selector: 'jhi-bidding-opener-detail',
  templateUrl: './bidding-opener-detail.component.html',
})
export class BiddingOpenerDetailComponent implements OnInit {
  biddingOpener: IBiddingOpener | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ biddingOpener }) => (this.biddingOpener = biddingOpener));
  }

  previousState(): void {
    window.history.back();
  }
}
