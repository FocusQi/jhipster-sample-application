import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IOpener } from 'app/shared/model/opener.model';
import { OpenerService } from './opener.service';
import { OpenerDeleteDialogComponent } from './opener-delete-dialog.component';

@Component({
  selector: 'jhi-opener',
  templateUrl: './opener.component.html',
})
export class OpenerComponent implements OnInit, OnDestroy {
  openers?: IOpener[];
  eventSubscriber?: Subscription;

  constructor(protected openerService: OpenerService, protected eventManager: JhiEventManager, protected modalService: NgbModal) {}

  loadAll(): void {
    this.openerService.query().subscribe((res: HttpResponse<IOpener[]>) => (this.openers = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInOpeners();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IOpener): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInOpeners(): void {
    this.eventSubscriber = this.eventManager.subscribe('openerListModification', () => this.loadAll());
  }

  delete(opener: IOpener): void {
    const modalRef = this.modalService.open(OpenerDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.opener = opener;
  }
}
