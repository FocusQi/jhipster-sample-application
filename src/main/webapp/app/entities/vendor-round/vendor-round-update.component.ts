import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IVendorRound, VendorRound } from 'app/shared/model/vendor-round.model';
import { VendorRoundService } from './vendor-round.service';
import { IBiddingRoundInfo } from 'app/shared/model/bidding-round-info.model';
import { BiddingRoundInfoService } from 'app/entities/bidding-round-info/bidding-round-info.service';
import { IVendor } from 'app/shared/model/vendor.model';
import { VendorService } from 'app/entities/vendor/vendor.service';

type SelectableEntity = IBiddingRoundInfo | IVendor;

@Component({
  selector: 'jhi-vendor-round-update',
  templateUrl: './vendor-round-update.component.html',
})
export class VendorRoundUpdateComponent implements OnInit {
  isSaving = false;
  biddingroundinfos: IBiddingRoundInfo[] = [];
  vendors: IVendor[] = [];

  editForm = this.fb.group({
    id: [],
    createdBy: [],
    createdDate: [],
    lastModifiedBy: [],
    lastModifiedDate: [],
    roundInfo: [],
    vendor: [],
  });

  constructor(
    protected vendorRoundService: VendorRoundService,
    protected biddingRoundInfoService: BiddingRoundInfoService,
    protected vendorService: VendorService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ vendorRound }) => {
      if (!vendorRound.id) {
        const today = moment().startOf('day');
        vendorRound.createdDate = today;
        vendorRound.lastModifiedDate = today;
      }

      this.updateForm(vendorRound);

      this.biddingRoundInfoService.query().subscribe((res: HttpResponse<IBiddingRoundInfo[]>) => (this.biddingroundinfos = res.body || []));

      this.vendorService.query().subscribe((res: HttpResponse<IVendor[]>) => (this.vendors = res.body || []));
    });
  }

  updateForm(vendorRound: IVendorRound): void {
    this.editForm.patchValue({
      id: vendorRound.id,
      createdBy: vendorRound.createdBy,
      createdDate: vendorRound.createdDate ? vendorRound.createdDate.format(DATE_TIME_FORMAT) : null,
      lastModifiedBy: vendorRound.lastModifiedBy,
      lastModifiedDate: vendorRound.lastModifiedDate ? vendorRound.lastModifiedDate.format(DATE_TIME_FORMAT) : null,
      roundInfo: vendorRound.roundInfo,
      vendor: vendorRound.vendor,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const vendorRound = this.createFromForm();
    if (vendorRound.id !== undefined) {
      this.subscribeToSaveResponse(this.vendorRoundService.update(vendorRound));
    } else {
      this.subscribeToSaveResponse(this.vendorRoundService.create(vendorRound));
    }
  }

  private createFromForm(): IVendorRound {
    return {
      ...new VendorRound(),
      id: this.editForm.get(['id'])!.value,
      createdBy: this.editForm.get(['createdBy'])!.value,
      createdDate: this.editForm.get(['createdDate'])!.value
        ? moment(this.editForm.get(['createdDate'])!.value, DATE_TIME_FORMAT)
        : undefined,
      lastModifiedBy: this.editForm.get(['lastModifiedBy'])!.value,
      lastModifiedDate: this.editForm.get(['lastModifiedDate'])!.value
        ? moment(this.editForm.get(['lastModifiedDate'])!.value, DATE_TIME_FORMAT)
        : undefined,
      roundInfo: this.editForm.get(['roundInfo'])!.value,
      vendor: this.editForm.get(['vendor'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IVendorRound>>): void {
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
