import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IBiddingQuotationHeader } from 'app/shared/model/bidding-quotation-header.model';
import { BiddingQuotationHeaderService } from './bidding-quotation-header.service';

@Component({
  templateUrl: './bidding-quotation-header-delete-dialog.component.html',
})
export class BiddingQuotationHeaderDeleteDialogComponent {
  biddingQuotationHeader?: IBiddingQuotationHeader;

  constructor(
    protected biddingQuotationHeaderService: BiddingQuotationHeaderService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.biddingQuotationHeaderService.delete(id).subscribe(() => {
      this.eventManager.broadcast('biddingQuotationHeaderListModification');
      this.activeModal.close();
    });
  }
}
