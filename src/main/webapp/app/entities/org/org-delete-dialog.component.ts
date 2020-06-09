import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IOrg } from 'app/shared/model/org.model';
import { OrgService } from './org.service';

@Component({
  templateUrl: './org-delete-dialog.component.html',
})
export class OrgDeleteDialogComponent {
  org?: IOrg;

  constructor(protected orgService: OrgService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.orgService.delete(id).subscribe(() => {
      this.eventManager.broadcast('orgListModification');
      this.activeModal.close();
    });
  }
}
