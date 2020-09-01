import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IMaterialRound } from 'app/shared/model/material-round.model';
import { MaterialRoundService } from './material-round.service';
import { MaterialRoundDeleteDialogComponent } from './material-round-delete-dialog.component';

@Component({
  selector: 'jhi-material-round',
  templateUrl: './material-round.component.html',
})
export class MaterialRoundComponent implements OnInit, OnDestroy {
  materialRounds?: IMaterialRound[];
  eventSubscriber?: Subscription;

  constructor(
    protected materialRoundService: MaterialRoundService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.materialRoundService.query().subscribe((res: HttpResponse<IMaterialRound[]>) => (this.materialRounds = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInMaterialRounds();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IMaterialRound): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInMaterialRounds(): void {
    this.eventSubscriber = this.eventManager.subscribe('materialRoundListModification', () => this.loadAll());
  }

  delete(materialRound: IMaterialRound): void {
    const modalRef = this.modalService.open(MaterialRoundDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.materialRound = materialRound;
  }
}
