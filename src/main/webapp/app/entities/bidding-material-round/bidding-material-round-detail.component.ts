import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IBiddingMaterialRound } from 'app/shared/model/bidding-material-round.model';

@Component({
  selector: 'jhi-bidding-material-round-detail',
  templateUrl: './bidding-material-round-detail.component.html',
})
export class BiddingMaterialRoundDetailComponent implements OnInit {
  biddingMaterialRound: IBiddingMaterialRound | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ biddingMaterialRound }) => (this.biddingMaterialRound = biddingMaterialRound));
  }

  previousState(): void {
    window.history.back();
  }
}
