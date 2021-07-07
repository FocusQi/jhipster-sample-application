import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IBiddingRoundInfo } from 'app/shared/model/bidding-round-info.model';
import { BiddingRoundInfoService } from './bidding-round-info.service';
import { BiddingRoundInfoDeleteDialogComponent } from './bidding-round-info-delete-dialog.component';

@Component({
  selector: 'jhi-bidding-round-info',
  templateUrl: './bidding-round-info.component.html',
})
export class BiddingRoundInfoComponent implements OnInit, OnDestroy {
  biddingRoundInfos?: IBiddingRoundInfo[];
  eventSubscriber?: Subscription;

  constructor(
    protected biddingRoundInfoService: BiddingRoundInfoService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.biddingRoundInfoService.query().subscribe((res: HttpResponse<IBiddingRoundInfo[]>) => (this.biddingRoundInfos = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInBiddingRoundInfos();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IBiddingRoundInfo): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInBiddingRoundInfos(): void {
    this.eventSubscriber = this.eventManager.subscribe('biddingRoundInfoListModification', () => this.loadAll());
  }

  delete(biddingRoundInfo: IBiddingRoundInfo): void {
    const modalRef = this.modalService.open(BiddingRoundInfoDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.biddingRoundInfo = biddingRoundInfo;
  }
}
