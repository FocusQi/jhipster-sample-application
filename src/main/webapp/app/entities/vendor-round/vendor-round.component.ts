import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IVendorRound } from 'app/shared/model/vendor-round.model';
import { VendorRoundService } from './vendor-round.service';
import { VendorRoundDeleteDialogComponent } from './vendor-round-delete-dialog.component';

@Component({
  selector: 'jhi-vendor-round',
  templateUrl: './vendor-round.component.html',
})
export class VendorRoundComponent implements OnInit, OnDestroy {
  vendorRounds?: IVendorRound[];
  eventSubscriber?: Subscription;

  constructor(
    protected vendorRoundService: VendorRoundService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.vendorRoundService.query().subscribe((res: HttpResponse<IVendorRound[]>) => (this.vendorRounds = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInVendorRounds();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IVendorRound): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInVendorRounds(): void {
    this.eventSubscriber = this.eventManager.subscribe('vendorRoundListModification', () => this.loadAll());
  }

  delete(vendorRound: IVendorRound): void {
    const modalRef = this.modalService.open(VendorRoundDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.vendorRound = vendorRound;
  }
}
