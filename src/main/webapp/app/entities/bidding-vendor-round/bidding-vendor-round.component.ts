import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IBiddingVendorRound } from 'app/shared/model/bidding-vendor-round.model';
import { BiddingVendorRoundService } from './bidding-vendor-round.service';
import { BiddingVendorRoundDeleteDialogComponent } from './bidding-vendor-round-delete-dialog.component';

@Component({
  selector: 'jhi-bidding-vendor-round',
  templateUrl: './bidding-vendor-round.component.html',
})
export class BiddingVendorRoundComponent implements OnInit, OnDestroy {
  biddingVendorRounds?: IBiddingVendorRound[];
  eventSubscriber?: Subscription;

  constructor(
    protected biddingVendorRoundService: BiddingVendorRoundService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.biddingVendorRoundService
      .query()
      .subscribe((res: HttpResponse<IBiddingVendorRound[]>) => (this.biddingVendorRounds = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInBiddingVendorRounds();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IBiddingVendorRound): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInBiddingVendorRounds(): void {
    this.eventSubscriber = this.eventManager.subscribe('biddingVendorRoundListModification', () => this.loadAll());
  }

  delete(biddingVendorRound: IBiddingVendorRound): void {
    const modalRef = this.modalService.open(BiddingVendorRoundDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.biddingVendorRound = biddingVendorRound;
  }
}
