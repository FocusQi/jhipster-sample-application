import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IPriceCompare } from 'app/shared/model/price-compare.model';
import { PriceCompareService } from './price-compare.service';
import { PriceCompareDeleteDialogComponent } from './price-compare-delete-dialog.component';

@Component({
  selector: 'jhi-price-compare',
  templateUrl: './price-compare.component.html',
})
export class PriceCompareComponent implements OnInit, OnDestroy {
  priceCompares?: IPriceCompare[];
  eventSubscriber?: Subscription;

  constructor(
    protected priceCompareService: PriceCompareService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.priceCompareService.query().subscribe((res: HttpResponse<IPriceCompare[]>) => (this.priceCompares = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInPriceCompares();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IPriceCompare): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInPriceCompares(): void {
    this.eventSubscriber = this.eventManager.subscribe('priceCompareListModification', () => this.loadAll());
  }

  delete(priceCompare: IPriceCompare): void {
    const modalRef = this.modalService.open(PriceCompareDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.priceCompare = priceCompare;
  }
}
