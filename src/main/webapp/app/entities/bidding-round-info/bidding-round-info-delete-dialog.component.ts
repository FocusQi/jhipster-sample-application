import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IBiddingRoundInfo } from 'app/shared/model/bidding-round-info.model';
import { BiddingRoundInfoService } from './bidding-round-info.service';

@Component({
  templateUrl: './bidding-round-info-delete-dialog.component.html',
})
export class BiddingRoundInfoDeleteDialogComponent {
  biddingRoundInfo?: IBiddingRoundInfo;

  constructor(
    protected biddingRoundInfoService: BiddingRoundInfoService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.biddingRoundInfoService.delete(id).subscribe(() => {
      this.eventManager.broadcast('biddingRoundInfoListModification');
      this.activeModal.close();
    });
  }
}
