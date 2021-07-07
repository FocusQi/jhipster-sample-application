import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IBiddingOpener } from 'app/shared/model/bidding-opener.model';
import { BiddingOpenerService } from './bidding-opener.service';

@Component({
  templateUrl: './bidding-opener-delete-dialog.component.html',
})
export class BiddingOpenerDeleteDialogComponent {
  biddingOpener?: IBiddingOpener;

  constructor(
    protected biddingOpenerService: BiddingOpenerService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.biddingOpenerService.delete(id).subscribe(() => {
      this.eventManager.broadcast('biddingOpenerListModification');
      this.activeModal.close();
    });
  }
}
