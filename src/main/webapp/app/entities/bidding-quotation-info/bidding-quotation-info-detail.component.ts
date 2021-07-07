import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IBiddingQuotationInfo } from 'app/shared/model/bidding-quotation-info.model';

@Component({
  selector: 'jhi-bidding-quotation-info-detail',
  templateUrl: './bidding-quotation-info-detail.component.html',
})
export class BiddingQuotationInfoDetailComponent implements OnInit {
  biddingQuotationInfo: IBiddingQuotationInfo | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ biddingQuotationInfo }) => (this.biddingQuotationInfo = biddingQuotationInfo));
  }

  previousState(): void {
    window.history.back();
  }
}
