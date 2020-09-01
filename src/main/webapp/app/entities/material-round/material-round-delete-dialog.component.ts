import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMaterialRound } from 'app/shared/model/material-round.model';
import { MaterialRoundService } from './material-round.service';

@Component({
  templateUrl: './material-round-delete-dialog.component.html',
})
export class MaterialRoundDeleteDialogComponent {
  materialRound?: IMaterialRound;

  constructor(
    protected materialRoundService: MaterialRoundService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.materialRoundService.delete(id).subscribe(() => {
      this.eventManager.broadcast('materialRoundListModification');
      this.activeModal.close();
    });
  }
}
