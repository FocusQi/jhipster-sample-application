import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IBiddingQuotationInfo } from 'app/shared/model/bidding-quotation-info.model';
import { BiddingQuotationInfoService } from './bidding-quotation-info.service';
import { BiddingQuotationInfoDeleteDialogComponent } from './bidding-quotation-info-delete-dialog.component';

@Component({
  selector: 'jhi-bidding-quotation-info',
  templateUrl: './bidding-quotation-info.component.html',
})
export class BiddingQuotationInfoComponent implements OnInit, OnDestroy {
  biddingQuotationInfos?: IBiddingQuotationInfo[];
  eventSubscriber?: Subscription;

  constructor(
    protected biddingQuotationInfoService: BiddingQuotationInfoService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.biddingQuotationInfoService
      .query()
      .subscribe((res: HttpResponse<IBiddingQuotationInfo[]>) => (this.biddingQuotationInfos = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInBiddingQuotationInfos();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IBiddingQuotationInfo): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInBiddingQuotationInfos(): void {
    this.eventSubscriber = this.eventManager.subscribe('biddingQuotationInfoListModification', () => this.loadAll());
  }

  delete(biddingQuotationInfo: IBiddingQuotationInfo): void {
    const modalRef = this.modalService.open(BiddingQuotationInfoDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.biddingQuotationInfo = biddingQuotationInfo;
  }
}
