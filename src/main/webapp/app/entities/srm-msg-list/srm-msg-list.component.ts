import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ISrmMsgList } from 'app/shared/model/srm-msg-list.model';
import { SrmMsgListService } from './srm-msg-list.service';
import { SrmMsgListDeleteDialogComponent } from './srm-msg-list-delete-dialog.component';

@Component({
  selector: 'jhi-srm-msg-list',
  templateUrl: './srm-msg-list.component.html',
})
export class SrmMsgListComponent implements OnInit, OnDestroy {
  srmMsgLists?: ISrmMsgList[];
  eventSubscriber?: Subscription;

  constructor(protected srmMsgListService: SrmMsgListService, protected eventManager: JhiEventManager, protected modalService: NgbModal) {}

  loadAll(): void {
    this.srmMsgListService.query().subscribe((res: HttpResponse<ISrmMsgList[]>) => (this.srmMsgLists = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInSrmMsgLists();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: ISrmMsgList): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInSrmMsgLists(): void {
    this.eventSubscriber = this.eventManager.subscribe('srmMsgListListModification', () => this.loadAll());
  }

  delete(srmMsgList: ISrmMsgList): void {
    const modalRef = this.modalService.open(SrmMsgListDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.srmMsgList = srmMsgList;
  }
}
