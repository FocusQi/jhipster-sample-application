import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IBiddingPriceCompare } from 'app/shared/model/bidding-price-compare.model';
import { BiddingPriceCompareService } from './bidding-price-compare.service';

@Component({
  templateUrl: './bidding-price-compare-delete-dialog.component.html',
})
export class BiddingPriceCompareDeleteDialogComponent {
  biddingPriceCompare?: IBiddingPriceCompare;

  constructor(
    protected biddingPriceCompareService: BiddingPriceCompareService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.biddingPriceCompareService.delete(id).subscribe(() => {
      this.eventManager.broadcast('biddingPriceCompareListModification');
      this.activeModal.close();
    });
  }
}
