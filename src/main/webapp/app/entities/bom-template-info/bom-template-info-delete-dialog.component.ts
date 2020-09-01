import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IBomTemplateInfo } from 'app/shared/model/bom-template-info.model';
import { BomTemplateInfoService } from './bom-template-info.service';

@Component({
  templateUrl: './bom-template-info-delete-dialog.component.html',
})
export class BomTemplateInfoDeleteDialogComponent {
  bomTemplateInfo?: IBomTemplateInfo;

  constructor(
    protected bomTemplateInfoService: BomTemplateInfoService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.bomTemplateInfoService.delete(id).subscribe(() => {
      this.eventManager.broadcast('bomTemplateInfoListModification');
      this.activeModal.close();
    });
  }
}
