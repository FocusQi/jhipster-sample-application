import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IBiddingRoundInfo, BiddingRoundInfo } from 'app/shared/model/bidding-round-info.model';
import { BiddingRoundInfoService } from './bidding-round-info.service';
import { IBiddingQuotationHeader } from 'app/shared/model/bidding-quotation-header.model';
import { BiddingQuotationHeaderService } from 'app/entities/bidding-quotation-header/bidding-quotation-header.service';

@Component({
  selector: 'jhi-bidding-round-info-update',
  templateUrl: './bidding-round-info-update.component.html',
})
export class BiddingRoundInfoUpdateComponent implements OnInit {
  isSaving = false;
  biddingquotationheaders: IBiddingQuotationHeader[] = [];

  editForm = this.fb.group({
    id: [],
    round: [null, [Validators.required]],
    startTime: [null, [Validators.required]],
    endTime: [null, [Validators.required]],
    description: [],
    createdBy: [],
    createdDate: [],
    lastModifiedBy: [],
    lastModifiedDate: [],
    header: [],
  });

  constructor(
    protected biddingRoundInfoService: BiddingRoundInfoService,
    protected biddingQuotationHeaderService: BiddingQuotationHeaderService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ biddingRoundInfo }) => {
      if (!biddingRoundInfo.id) {
        const today = moment().startOf('day');
        biddingRoundInfo.startTime = today;
        biddingRoundInfo.endTime = today;
        biddingRoundInfo.createdDate = today;
        biddingRoundInfo.lastModifiedDate = today;
      }

      this.updateForm(biddingRoundInfo);

      this.biddingQuotationHeaderService
        .query()
        .subscribe((res: HttpResponse<IBiddingQuotationHeader[]>) => (this.biddingquotationheaders = res.body || []));
    });
  }

  updateForm(biddingRoundInfo: IBiddingRoundInfo): void {
    this.editForm.patchValue({
      id: biddingRoundInfo.id,
      round: biddingRoundInfo.round,
      startTime: biddingRoundInfo.startTime ? biddingRoundInfo.startTime.format(DATE_TIME_FORMAT) : null,
      endTime: biddingRoundInfo.endTime ? biddingRoundInfo.endTime.format(DATE_TIME_FORMAT) : null,
      description: biddingRoundInfo.description,
      createdBy: biddingRoundInfo.createdBy,
      createdDate: biddingRoundInfo.createdDate ? biddingRoundInfo.createdDate.format(DATE_TIME_FORMAT) : null,
      lastModifiedBy: biddingRoundInfo.lastModifiedBy,
      lastModifiedDate: biddingRoundInfo.lastModifiedDate ? biddingRoundInfo.lastModifiedDate.format(DATE_TIME_FORMAT) : null,
      header: biddingRoundInfo.header,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const biddingRoundInfo = this.createFromForm();
    if (biddingRoundInfo.id !== undefined) {
      this.subscribeToSaveResponse(this.biddingRoundInfoService.update(biddingRoundInfo));
    } else {
      this.subscribeToSaveResponse(this.biddingRoundInfoService.create(biddingRoundInfo));
    }
  }

  private createFromForm(): IBiddingRoundInfo {
    return {
      ...new BiddingRoundInfo(),
      id: this.editForm.get(['id'])!.value,
      round: this.editForm.get(['round'])!.value,
      startTime: this.editForm.get(['startTime'])!.value ? moment(this.editForm.get(['startTime'])!.value, DATE_TIME_FORMAT) : undefined,
      endTime: this.editForm.get(['endTime'])!.value ? moment(this.editForm.get(['endTime'])!.value, DATE_TIME_FORMAT) : undefined,
      description: this.editForm.get(['description'])!.value,
      createdBy: this.editForm.get(['createdBy'])!.value,
      createdDate: this.editForm.get(['createdDate'])!.value
        ? moment(this.editForm.get(['createdDate'])!.value, DATE_TIME_FORMAT)
        : undefined,
      lastModifiedBy: this.editForm.get(['lastModifiedBy'])!.value,
      lastModifiedDate: this.editForm.get(['lastModifiedDate'])!.value
        ? moment(this.editForm.get(['lastModifiedDate'])!.value, DATE_TIME_FORMAT)
        : undefined,
      header: this.editForm.get(['header'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IBiddingRoundInfo>>): void {
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

  trackById(index: number, item: IBiddingQuotationHeader): any {
    return item.id;
  }
}
