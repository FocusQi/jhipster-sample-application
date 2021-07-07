import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IBomTemplateInfoColumn } from 'app/shared/model/bom-template-info-column.model';
import { BomTemplateInfoColumnService } from './bom-template-info-column.service';

@Component({
  templateUrl: './bom-template-info-column-delete-dialog.component.html',
})
export class BomTemplateInfoColumnDeleteDialogComponent {
  bomTemplateInfoColumn?: IBomTemplateInfoColumn;

  constructor(
    protected bomTemplateInfoColumnService: BomTemplateInfoColumnService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.bomTemplateInfoColumnService.delete(id).subscribe(() => {
      this.eventManager.broadcast('bomTemplateInfoColumnListModification');
      this.activeModal.close();
    });
  }
}
