import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IOrg } from 'app/shared/model/org.model';
import { OrgService } from './org.service';
import { OrgDeleteDialogComponent } from './org-delete-dialog.component';

@Component({
  selector: 'jhi-org',
  templateUrl: './org.component.html',
})
export class OrgComponent implements OnInit, OnDestroy {
  orgs?: IOrg[];
  eventSubscriber?: Subscription;

  constructor(protected orgService: OrgService, protected eventManager: JhiEventManager, protected modalService: NgbModal) {}

  loadAll(): void {
    this.orgService.query().subscribe((res: HttpResponse<IOrg[]>) => (this.orgs = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInOrgs();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IOrg): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInOrgs(): void {
    this.eventSubscriber = this.eventManager.subscribe('orgListModification', () => this.loadAll());
  }

  delete(org: IOrg): void {
    const modalRef = this.modalService.open(OrgDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.org = org;
  }
}
