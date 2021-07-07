import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IOpener } from 'app/shared/model/opener.model';
import { OpenerService } from './opener.service';

@Component({
  templateUrl: './opener-delete-dialog.component.html',
})
export class OpenerDeleteDialogComponent {
  opener?: IOpener;

  constructor(protected openerService: OpenerService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.openerService.delete(id).subscribe(() => {
      this.eventManager.broadcast('openerListModification');
      this.activeModal.close();
    });
  }
}
