import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IVendorRound } from 'app/shared/model/vendor-round.model';

@Component({
  selector: 'jhi-vendor-round-detail',
  templateUrl: './vendor-round-detail.component.html',
})
export class VendorRoundDetailComponent implements OnInit {
  vendorRound: IVendorRound | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ vendorRound }) => (this.vendorRound = vendorRound));
  }

  previousState(): void {
    window.history.back();
  }
}
