import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IReturnHeader } from 'app/shared/model/return-header.model';
import { ReturnHeaderService } from './return-header.service';
import { ReturnHeaderDeleteDialogComponent } from './return-header-delete-dialog.component';

@Component({
  selector: 'jhi-return-header',
  templateUrl: './return-header.component.html',
})
export class ReturnHeaderComponent implements OnInit, OnDestroy {
  returnHeaders?: IReturnHeader[];
  eventSubscriber?: Subscription;

  constructor(
    protected returnHeaderService: ReturnHeaderService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.returnHeaderService.query().subscribe((res: HttpResponse<IReturnHeader[]>) => (this.returnHeaders = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInReturnHeaders();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IReturnHeader): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInReturnHeaders(): void {
    this.eventSubscriber = this.eventManager.subscribe('returnHeaderListModification', () => this.loadAll());
  }

  delete(returnHeader: IReturnHeader): void {
    const modalRef = this.modalService.open(ReturnHeaderDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.returnHeader = returnHeader;
  }
}
