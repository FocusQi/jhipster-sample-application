import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IPriceCompare } from 'app/shared/model/price-compare.model';
import { PriceCompareService } from './price-compare.service';

@Component({
  templateUrl: './price-compare-delete-dialog.component.html',
})
export class PriceCompareDeleteDialogComponent {
  priceCompare?: IPriceCompare;

  constructor(
    protected priceCompareService: PriceCompareService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.priceCompareService.delete(id).subscribe(() => {
      this.eventManager.broadcast('priceCompareListModification');
      this.activeModal.close();
    });
  }
}
