import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IPoHeader } from 'app/shared/model/po-header.model';
import { PoHeaderService } from './po-header.service';
import { PoHeaderDeleteDialogComponent } from './po-header-delete-dialog.component';

@Component({
  selector: 'jhi-po-header',
  templateUrl: './po-header.component.html',
})
export class PoHeaderComponent implements OnInit, OnDestroy {
  poHeaders?: IPoHeader[];
  eventSubscriber?: Subscription;

  constructor(protected poHeaderService: PoHeaderService, protected eventManager: JhiEventManager, protected modalService: NgbModal) {}

  loadAll(): void {
    this.poHeaderService.query().subscribe((res: HttpResponse<IPoHeader[]>) => (this.poHeaders = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInPoHeaders();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IPoHeader): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInPoHeaders(): void {
    this.eventSubscriber = this.eventManager.subscribe('poHeaderListModification', () => this.loadAll());
  }

  delete(poHeader: IPoHeader): void {
    const modalRef = this.modalService.open(PoHeaderDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.poHeader = poHeader;
  }
}
