import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IBiddingPriceCompare } from 'app/shared/model/bidding-price-compare.model';

@Component({
  selector: 'jhi-bidding-price-compare-detail',
  templateUrl: './bidding-price-compare-detail.component.html',
})
export class BiddingPriceCompareDetailComponent implements OnInit {
  biddingPriceCompare: IBiddingPriceCompare | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ biddingPriceCompare }) => (this.biddingPriceCompare = biddingPriceCompare));
  }

  previousState(): void {
    window.history.back();
  }
}
