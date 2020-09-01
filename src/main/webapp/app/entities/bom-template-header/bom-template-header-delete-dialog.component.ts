import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IBomTemplateHeader } from 'app/shared/model/bom-template-header.model';
import { BomTemplateHeaderService } from './bom-template-header.service';

@Component({
  templateUrl: './bom-template-header-delete-dialog.component.html',
})
export class BomTemplateHeaderDeleteDialogComponent {
  bomTemplateHeader?: IBomTemplateHeader;

  constructor(
    protected bomTemplateHeaderService: BomTemplateHeaderService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.bomTemplateHeaderService.delete(id).subscribe(() => {
      this.eventManager.broadcast('bomTemplateHeaderListModification');
      this.activeModal.close();
    });
  }
}
