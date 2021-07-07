import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IBiddingOpener } from 'app/shared/model/bidding-opener.model';
import { BiddingOpenerService } from './bidding-opener.service';
import { BiddingOpenerDeleteDialogComponent } from './bidding-opener-delete-dialog.component';

@Component({
  selector: 'jhi-bidding-opener',
  templateUrl: './bidding-opener.component.html',
})
export class BiddingOpenerComponent implements OnInit, OnDestroy {
  biddingOpeners?: IBiddingOpener[];
  eventSubscriber?: Subscription;

  constructor(
    protected biddingOpenerService: BiddingOpenerService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.biddingOpenerService.query().subscribe((res: HttpResponse<IBiddingOpener[]>) => (this.biddingOpeners = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInBiddingOpeners();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IBiddingOpener): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInBiddingOpeners(): void {
    this.eventSubscriber = this.eventManager.subscribe('biddingOpenerListModification', () => this.loadAll());
  }

  delete(biddingOpener: IBiddingOpener): void {
    const modalRef = this.modalService.open(BiddingOpenerDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.biddingOpener = biddingOpener;
  }
}
