import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ISrmMsgList } from 'app/shared/model/srm-msg-list.model';
import { SrmMsgListService } from './srm-msg-list.service';

@Component({
  templateUrl: './srm-msg-list-delete-dialog.component.html',
})
export class SrmMsgListDeleteDialogComponent {
  srmMsgList?: ISrmMsgList;

  constructor(
    protected srmMsgListService: SrmMsgListService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.srmMsgListService.delete(id).subscribe(() => {
      this.eventManager.broadcast('srmMsgListListModification');
      this.activeModal.close();
    });
  }
}
