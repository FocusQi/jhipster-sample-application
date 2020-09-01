import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IPoHeader } from 'app/shared/model/po-header.model';
import { PoHeaderService } from './po-header.service';

@Component({
  templateUrl: './po-header-delete-dialog.component.html',
})
export class PoHeaderDeleteDialogComponent {
  poHeader?: IPoHeader;

  constructor(protected poHeaderService: PoHeaderService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.poHeaderService.delete(id).subscribe(() => {
      this.eventManager.broadcast('poHeaderListModification');
      this.activeModal.close();
    });
  }
}
