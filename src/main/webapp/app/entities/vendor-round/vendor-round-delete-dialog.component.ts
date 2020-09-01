import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IVendorRound } from 'app/shared/model/vendor-round.model';
import { VendorRoundService } from './vendor-round.service';

@Component({
  templateUrl: './vendor-round-delete-dialog.component.html',
})
export class VendorRoundDeleteDialogComponent {
  vendorRound?: IVendorRound;

  constructor(
    protected vendorRoundService: VendorRoundService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.vendorRoundService.delete(id).subscribe(() => {
      this.eventManager.broadcast('vendorRoundListModification');
      this.activeModal.close();
    });
  }
}
