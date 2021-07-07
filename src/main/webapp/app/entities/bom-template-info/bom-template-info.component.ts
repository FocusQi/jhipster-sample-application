import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IBomTemplateInfo } from 'app/shared/model/bom-template-info.model';
import { BomTemplateInfoService } from './bom-template-info.service';
import { BomTemplateInfoDeleteDialogComponent } from './bom-template-info-delete-dialog.component';

@Component({
  selector: 'jhi-bom-template-info',
  templateUrl: './bom-template-info.component.html',
})
export class BomTemplateInfoComponent implements OnInit, OnDestroy {
  bomTemplateInfos?: IBomTemplateInfo[];
  eventSubscriber?: Subscription;

  constructor(
    protected bomTemplateInfoService: BomTemplateInfoService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.bomTemplateInfoService.query().subscribe((res: HttpResponse<IBomTemplateInfo[]>) => (this.bomTemplateInfos = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInBomTemplateInfos();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IBomTemplateInfo): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInBomTemplateInfos(): void {
    this.eventSubscriber = this.eventManager.subscribe('bomTemplateInfoListModification', () => this.loadAll());
  }

  delete(bomTemplateInfo: IBomTemplateInfo): void {
    const modalRef = this.modalService.open(BomTemplateInfoDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.bomTemplateInfo = bomTemplateInfo;
  }
}
