import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IBomTemplateHeader } from 'app/shared/model/bom-template-header.model';
import { BomTemplateHeaderService } from './bom-template-header.service';
import { BomTemplateHeaderDeleteDialogComponent } from './bom-template-header-delete-dialog.component';

@Component({
  selector: 'jhi-bom-template-header',
  templateUrl: './bom-template-header.component.html',
})
export class BomTemplateHeaderComponent implements OnInit, OnDestroy {
  bomTemplateHeaders?: IBomTemplateHeader[];
  eventSubscriber?: Subscription;

  constructor(
    protected bomTemplateHeaderService: BomTemplateHeaderService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.bomTemplateHeaderService
      .query()
      .subscribe((res: HttpResponse<IBomTemplateHeader[]>) => (this.bomTemplateHeaders = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInBomTemplateHeaders();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IBomTemplateHeader): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInBomTemplateHeaders(): void {
    this.eventSubscriber = this.eventManager.subscribe('bomTemplateHeaderListModification', () => this.loadAll());
  }

  delete(bomTemplateHeader: IBomTemplateHeader): void {
    const modalRef = this.modalService.open(BomTemplateHeaderDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.bomTemplateHeader = bomTemplateHeader;
  }
}
