import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IQuotationInfo } from 'app/shared/model/quotation-info.model';
import { QuotationInfoService } from './quotation-info.service';

@Component({
  templateUrl: './quotation-info-delete-dialog.component.html',
})
export class QuotationInfoDeleteDialogComponent {
  quotationInfo?: IQuotationInfo;

  constructor(
    protected quotationInfoService: QuotationInfoService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.quotationInfoService.delete(id).subscribe(() => {
      this.eventManager.broadcast('quotationInfoListModification');
      this.activeModal.close();
    });
  }
}
