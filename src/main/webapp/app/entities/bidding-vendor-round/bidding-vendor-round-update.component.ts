import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IBiddingVendorRound, BiddingVendorRound } from 'app/shared/model/bidding-vendor-round.model';
import { BiddingVendorRoundService } from './bidding-vendor-round.service';
import { IVendor } from 'app/shared/model/vendor.model';
import { VendorService } from 'app/entities/vendor/vendor.service';
import { IBiddingRoundInfo } from 'app/shared/model/bidding-round-info.model';
import { BiddingRoundInfoService } from 'app/entities/bidding-round-info/bidding-round-info.service';

type SelectableEntity = IVendor | IBiddingRoundInfo;

@Component({
  selector: 'jhi-bidding-vendor-round-update',
  templateUrl: './bidding-vendor-round-update.component.html',
})
export class BiddingVendorRoundUpdateComponent implements OnInit {
  isSaving = false;
  vendors: IVendor[] = [];
  biddingroundinfos: IBiddingRoundInfo[] = [];

  editForm = this.fb.group({
    id: [],
    createdBy: [],
    createdDate: [],
    lastModifiedBy: [],
    lastModifiedDate: [],
    vendor: [],
    roundInfo: [],
  });

  constructor(
    protected biddingVendorRoundService: BiddingVendorRoundService,
    protected vendorService: VendorService,
    protected biddingRoundInfoService: BiddingRoundInfoService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ biddingVendorRound }) => {
      if (!biddingVendorRound.id) {
        const today = moment().startOf('day');
        biddingVendorRound.createdDate = today;
        biddingVendorRound.lastModifiedDate = today;
      }

      this.updateForm(biddingVendorRound);

      this.vendorService.query().subscribe((res: HttpResponse<IVendor[]>) => (this.vendors = res.body || []));

      this.biddingRoundInfoService.query().subscribe((res: HttpResponse<IBiddingRoundInfo[]>) => (this.biddingroundinfos = res.body || []));
    });
  }

  updateForm(biddingVendorRound: IBiddingVendorRound): void {
    this.editForm.patchValue({
      id: biddingVendorRound.id,
      createdBy: biddingVendorRound.createdBy,
      createdDate: biddingVendorRound.createdDate ? biddingVendorRound.createdDate.format(DATE_TIME_FORMAT) : null,
      lastModifiedBy: biddingVendorRound.lastModifiedBy,
      lastModifiedDate: biddingVendorRound.lastModifiedDate ? biddingVendorRound.lastModifiedDate.format(DATE_TIME_FORMAT) : null,
      vendor: biddingVendorRound.vendor,
      roundInfo: biddingVendorRound.roundInfo,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const biddingVendorRound = this.createFromForm();
    if (biddingVendorRound.id !== undefined) {
      this.subscribeToSaveResponse(this.biddingVendorRoundService.update(biddingVendorRound));
    } else {
      this.subscribeToSaveResponse(this.biddingVendorRoundService.create(biddingVendorRound));
    }
  }

  private createFromForm(): IBiddingVendorRound {
    return {
      ...new BiddingVendorRound(),
      id: this.editForm.get(['id'])!.value,
      createdBy: this.editForm.get(['createdBy'])!.value,
      createdDate: this.editForm.get(['createdDate'])!.value
        ? moment(this.editForm.get(['createdDate'])!.value, DATE_TIME_FORMAT)
        : undefined,
      lastModifiedBy: this.editForm.get(['lastModifiedBy'])!.value,
      lastModifiedDate: this.editForm.get(['lastModifiedDate'])!.value
        ? moment(this.editForm.get(['lastModifiedDate'])!.value, DATE_TIME_FORMAT)
        : undefined,
      vendor: this.editForm.get(['vendor'])!.value,
      roundInfo: this.editForm.get(['roundInfo'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IBiddingVendorRound>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }

  trackById(index: number, item: SelectableEntity): any {
    return item.id;
  }
}
