import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IBiddingMaterialRound } from 'app/shared/model/bidding-material-round.model';
import { BiddingMaterialRoundService } from './bidding-material-round.service';

@Component({
  templateUrl: './bidding-material-round-delete-dialog.component.html',
})
export class BiddingMaterialRoundDeleteDialogComponent {
  biddingMaterialRound?: IBiddingMaterialRound;

  constructor(
    protected biddingMaterialRoundService: BiddingMaterialRoundService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.biddingMaterialRoundService.delete(id).subscribe(() => {
      this.eventManager.broadcast('biddingMaterialRoundListModification');
      this.activeModal.close();
    });
  }
}
