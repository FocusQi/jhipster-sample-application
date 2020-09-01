import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IBomTemplateInfoColumn } from 'app/shared/model/bom-template-info-column.model';
import { BomTemplateInfoColumnService } from './bom-template-info-column.service';
import { BomTemplateInfoColumnDeleteDialogComponent } from './bom-template-info-column-delete-dialog.component';

@Component({
  selector: 'jhi-bom-template-info-column',
  templateUrl: './bom-template-info-column.component.html',
})
export class BomTemplateInfoColumnComponent implements OnInit, OnDestroy {
  bomTemplateInfoColumns?: IBomTemplateInfoColumn[];
  eventSubscriber?: Subscription;

  constructor(
    protected bomTemplateInfoColumnService: BomTemplateInfoColumnService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.bomTemplateInfoColumnService
      .query()
      .subscribe((res: HttpResponse<IBomTemplateInfoColumn[]>) => (this.bomTemplateInfoColumns = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInBomTemplateInfoColumns();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IBomTemplateInfoColumn): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInBomTemplateInfoColumns(): void {
    this.eventSubscriber = this.eventManager.subscribe('bomTemplateInfoColumnListModification', () => this.loadAll());
  }

  delete(bomTemplateInfoColumn: IBomTemplateInfoColumn): void {
    const modalRef = this.modalService.open(BomTemplateInfoColumnDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.bomTemplateInfoColumn = bomTemplateInfoColumn;
  }
}
