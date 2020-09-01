import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IBiddingQuotationHeader } from 'app/shared/model/bidding-quotation-header.model';

@Component({
  selector: 'jhi-bidding-quotation-header-detail',
  templateUrl: './bidding-quotation-header-detail.component.html',
})
export class BiddingQuotationHeaderDetailComponent implements OnInit {
  biddingQuotationHeader: IBiddingQuotationHeader | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ biddingQuotationHeader }) => (this.biddingQuotationHeader = biddingQuotationHeader));
  }

  previousState(): void {
    window.history.back();
  }
}
