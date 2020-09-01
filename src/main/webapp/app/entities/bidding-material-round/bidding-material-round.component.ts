import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IBiddingMaterialRound } from 'app/shared/model/bidding-material-round.model';
import { BiddingMaterialRoundService } from './bidding-material-round.service';
import { BiddingMaterialRoundDeleteDialogComponent } from './bidding-material-round-delete-dialog.component';

@Component({
  selector: 'jhi-bidding-material-round',
  templateUrl: './bidding-material-round.component.html',
})
export class BiddingMaterialRoundComponent implements OnInit, OnDestroy {
  biddingMaterialRounds?: IBiddingMaterialRound[];
  eventSubscriber?: Subscription;

  constructor(
    protected biddingMaterialRoundService: BiddingMaterialRoundService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.biddingMaterialRoundService
      .query()
      .subscribe((res: HttpResponse<IBiddingMaterialRound[]>) => (this.biddingMaterialRounds = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInBiddingMaterialRounds();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IBiddingMaterialRound): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInBiddingMaterialRounds(): void {
    this.eventSubscriber = this.eventManager.subscribe('biddingMaterialRoundListModification', () => this.loadAll());
  }

  delete(biddingMaterialRound: IBiddingMaterialRound): void {
    const modalRef = this.modalService.open(BiddingMaterialRoundDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.biddingMaterialRound = biddingMaterialRound;
  }
}
