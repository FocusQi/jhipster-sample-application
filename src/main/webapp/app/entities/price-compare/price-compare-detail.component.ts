import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IPriceCompare } from 'app/shared/model/price-compare.model';

@Component({
  selector: 'jhi-price-compare-detail',
  templateUrl: './price-compare-detail.component.html',
})
export class PriceCompareDetailComponent implements OnInit {
  priceCompare: IPriceCompare | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ priceCompare }) => (this.priceCompare = priceCompare));
  }

  previousState(): void {
    window.history.back();
  }
}
