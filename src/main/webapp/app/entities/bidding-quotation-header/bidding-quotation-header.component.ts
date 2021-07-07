import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IBiddingQuotationHeader } from 'app/shared/model/bidding-quotation-header.model';
import { BiddingQuotationHeaderService } from './bidding-quotation-header.service';
import { BiddingQuotationHeaderDeleteDialogComponent } from './bidding-quotation-header-delete-dialog.component';

@Component({
  selector: 'jhi-bidding-quotation-header',
  templateUrl: './bidding-quotation-header.component.html',
})
export class BiddingQuotationHeaderComponent implements OnInit, OnDestroy {
  biddingQuotationHeaders?: IBiddingQuotationHeader[];
  eventSubscriber?: Subscription;

  constructor(
    protected biddingQuotationHeaderService: BiddingQuotationHeaderService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.biddingQuotationHeaderService
      .query()
      .subscribe((res: HttpResponse<IBiddingQuotationHeader[]>) => (this.biddingQuotationHeaders = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInBiddingQuotationHeaders();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IBiddingQuotationHeader): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInBiddingQuotationHeaders(): void {
    this.eventSubscriber = this.eventManager.subscribe('biddingQuotationHeaderListModification', () => this.loadAll());
  }

  delete(biddingQuotationHeader: IBiddingQuotationHeader): void {
    const modalRef = this.modalService.open(BiddingQuotationHeaderDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.biddingQuotationHeader = biddingQuotationHeader;
  }
}
