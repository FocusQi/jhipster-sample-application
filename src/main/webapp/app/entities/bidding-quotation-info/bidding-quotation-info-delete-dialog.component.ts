import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IBiddingQuotationInfo } from 'app/shared/model/bidding-quotation-info.model';
import { BiddingQuotationInfoService } from './bidding-quotation-info.service';

@Component({
  templateUrl: './bidding-quotation-info-delete-dialog.component.html',
})
export class BiddingQuotationInfoDeleteDialogComponent {
  biddingQuotationInfo?: IBiddingQuotationInfo;

  constructor(
    protected biddingQuotationInfoService: BiddingQuotationInfoService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.biddingQuotationInfoService.delete(id).subscribe(() => {
      this.eventManager.broadcast('biddingQuotationInfoListModification');
      this.activeModal.close();
    });
  }
}
