import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IQuotationInfo } from 'app/shared/model/quotation-info.model';

@Component({
  selector: 'jhi-quotation-info-detail',
  templateUrl: './quotation-info-detail.component.html',
})
export class QuotationInfoDetailComponent implements OnInit {
  quotationInfo: IQuotationInfo | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ quotationInfo }) => (this.quotationInfo = quotationInfo));
  }

  previousState(): void {
    window.history.back();
  }
}
