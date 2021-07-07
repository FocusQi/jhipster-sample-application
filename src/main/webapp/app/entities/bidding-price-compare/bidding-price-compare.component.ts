import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IBiddingPriceCompare } from 'app/shared/model/bidding-price-compare.model';
import { BiddingPriceCompareService } from './bidding-price-compare.service';
import { BiddingPriceCompareDeleteDialogComponent } from './bidding-price-compare-delete-dialog.component';

@Component({
  selector: 'jhi-bidding-price-compare',
  templateUrl: './bidding-price-compare.component.html',
})
export class BiddingPriceCompareComponent implements OnInit, OnDestroy {
  biddingPriceCompares?: IBiddingPriceCompare[];
  eventSubscriber?: Subscription;

  constructor(
    protected biddingPriceCompareService: BiddingPriceCompareService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.biddingPriceCompareService
      .query()
      .subscribe((res: HttpResponse<IBiddingPriceCompare[]>) => (this.biddingPriceCompares = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInBiddingPriceCompares();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IBiddingPriceCompare): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInBiddingPriceCompares(): void {
    this.eventSubscriber = this.eventManager.subscribe('biddingPriceCompareListModification', () => this.loadAll());
  }

  delete(biddingPriceCompare: IBiddingPriceCompare): void {
    const modalRef = this.modalService.open(BiddingPriceCompareDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.biddingPriceCompare = biddingPriceCompare;
  }
}
