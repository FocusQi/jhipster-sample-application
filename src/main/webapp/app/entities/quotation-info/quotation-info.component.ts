import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IQuotationInfo } from 'app/shared/model/quotation-info.model';
import { QuotationInfoService } from './quotation-info.service';
import { QuotationInfoDeleteDialogComponent } from './quotation-info-delete-dialog.component';

@Component({
  selector: 'jhi-quotation-info',
  templateUrl: './quotation-info.component.html',
})
export class QuotationInfoComponent implements OnInit, OnDestroy {
  quotationInfos?: IQuotationInfo[];
  eventSubscriber?: Subscription;

  constructor(
    protected quotationInfoService: QuotationInfoService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.quotationInfoService.query().subscribe((res: HttpResponse<IQuotationInfo[]>) => (this.quotationInfos = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInQuotationInfos();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IQuotationInfo): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInQuotationInfos(): void {
    this.eventSubscriber = this.eventManager.subscribe('quotationInfoListModification', () => this.loadAll());
  }

  delete(quotationInfo: IQuotationInfo): void {
    const modalRef = this.modalService.open(QuotationInfoDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.quotationInfo = quotationInfo;
  }
}
