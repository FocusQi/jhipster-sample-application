import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IReturnHeader } from 'app/shared/model/return-header.model';
import { ReturnHeaderService } from './return-header.service';

@Component({
  templateUrl: './return-header-delete-dialog.component.html',
})
export class ReturnHeaderDeleteDialogComponent {
  returnHeader?: IReturnHeader;

  constructor(
    protected returnHeaderService: ReturnHeaderService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.returnHeaderService.delete(id).subscribe(() => {
      this.eventManager.broadcast('returnHeaderListModification');
      this.activeModal.close();
    });
  }
}
