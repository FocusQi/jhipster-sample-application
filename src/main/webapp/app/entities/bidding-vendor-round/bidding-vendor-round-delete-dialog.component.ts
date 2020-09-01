import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IBiddingVendorRound } from 'app/shared/model/bidding-vendor-round.model';
import { BiddingVendorRoundService } from './bidding-vendor-round.service';

@Component({
  templateUrl: './bidding-vendor-round-delete-dialog.component.html',
})
export class BiddingVendorRoundDeleteDialogComponent {
  biddingVendorRound?: IBiddingVendorRound;

  constructor(
    protected biddingVendorRoundService: BiddingVendorRoundService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.biddingVendorRoundService.delete(id).subscribe(() => {
      this.eventManager.broadcast('biddingVendorRoundListModification');
      this.activeModal.close();
    });
  }
}
