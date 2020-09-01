import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IBiddingQuotationHeader, BiddingQuotationHeader } from 'app/shared/model/bidding-quotation-header.model';
import { BiddingQuotationHeaderService } from './bidding-quotation-header.service';
import { IPriceCompare } from 'app/shared/model/price-compare.model';
import { PriceCompareService } from 'app/entities/price-compare/price-compare.service';

@Component({
  selector: 'jhi-bidding-quotation-header-update',
  templateUrl: './bidding-quotation-header-update.component.html',
})
export class BiddingQuotationHeaderUpdateComponent implements OnInit {
  isSaving = false;
  pricecompares: IPriceCompare[] = [];
  issuanceDateDp: any;

  editForm = this.fb.group({
    id: [],
    biddingCode: [null, [Validators.required]],
    modeType: [null, [Validators.required]],
    biddingType: [null, [Validators.required]],
    issuanceDate: [null, [Validators.required]],
    biddingTimes: [],
    sumTimes: [],
    maxQuotationTimes: [],
    status: [null, [Validators.required]],
    costListRequired: [],
    description: [],
    createdBy: [],
    createdDate: [],
    lastModifiedBy: [],
    lastModifiedDate: [],
    priceCompare: [],
  });

  constructor(
    protected biddingQuotationHeaderService: BiddingQuotationHeaderService,
    protected priceCompareService: PriceCompareService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ biddingQuotationHeader }) => {
      if (!biddingQuotationHeader.id) {
        const today = moment().startOf('day');
        biddingQuotationHeader.createdDate = today;
        biddingQuotationHeader.lastModifiedDate = today;
      }

      this.updateForm(biddingQuotationHeader);

      this.priceCompareService
        .query({ filter: 'bqheader-is-null' })
        .pipe(
          map((res: HttpResponse<IPriceCompare[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: IPriceCompare[]) => {
          if (!biddingQuotationHeader.priceCompare || !biddingQuotationHeader.priceCompare.id) {
            this.pricecompares = resBody;
          } else {
            this.priceCompareService
              .find(biddingQuotationHeader.priceCompare.id)
              .pipe(
                map((subRes: HttpResponse<IPriceCompare>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: IPriceCompare[]) => (this.pricecompares = concatRes));
          }
        });
    });
  }

  updateForm(biddingQuotationHeader: IBiddingQuotationHeader): void {
    this.editForm.patchValue({
      id: biddingQuotationHeader.id,
      biddingCode: biddingQuotationHeader.biddingCode,
      modeType: biddingQuotationHeader.modeType,
      biddingType: biddingQuotationHeader.biddingType,
      issuanceDate: biddingQuotationHeader.issuanceDate,
      biddingTimes: biddingQuotationHeader.biddingTimes,
      sumTimes: biddingQuotationHeader.sumTimes,
      maxQuotationTimes: biddingQuotationHeader.maxQuotationTimes,
      status: biddingQuotationHeader.status,
      costListRequired: biddingQuotationHeader.costListRequired,
      description: biddingQuotationHeader.description,
      createdBy: biddingQuotationHeader.createdBy,
      createdDate: biddingQuotationHeader.createdDate ? biddingQuotationHeader.createdDate.format(DATE_TIME_FORMAT) : null,
      lastModifiedBy: biddingQuotationHeader.lastModifiedBy,
      lastModifiedDate: biddingQuotationHeader.lastModifiedDate ? biddingQuotationHeader.lastModifiedDate.format(DATE_TIME_FORMAT) : null,
      priceCompare: biddingQuotationHeader.priceCompare,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const biddingQuotationHeader = this.createFromForm();
    if (biddingQuotationHeader.id !== undefined) {
      this.subscribeToSaveResponse(this.biddingQuotationHeaderService.update(biddingQuotationHeader));
    } else {
      this.subscribeToSaveResponse(this.biddingQuotationHeaderService.create(biddingQuotationHeader));
    }
  }

  private createFromForm(): IBiddingQuotationHeader {
    return {
      ...new BiddingQuotationHeader(),
      id: this.editForm.get(['id'])!.value,
      biddingCode: this.editForm.get(['biddingCode'])!.value,
      modeType: this.editForm.get(['modeType'])!.value,
      biddingType: this.editForm.get(['biddingType'])!.value,
      issuanceDate: this.editForm.get(['issuanceDate'])!.value,
      biddingTimes: this.editForm.get(['biddingTimes'])!.value,
      sumTimes: this.editForm.get(['sumTimes'])!.value,
      maxQuotationTimes: this.editForm.get(['maxQuotationTimes'])!.value,
      status: this.editForm.get(['status'])!.value,
      costListRequired: this.editForm.get(['costListRequired'])!.value,
      description: this.editForm.get(['description'])!.value,
      createdBy: this.editForm.get(['createdBy'])!.value,
      createdDate: this.editForm.get(['createdDate'])!.value
        ? moment(this.editForm.get(['createdDate'])!.value, DATE_TIME_FORMAT)
        : undefined,
      lastModifiedBy: this.editForm.get(['lastModifiedBy'])!.value,
      lastModifiedDate: this.editForm.get(['lastModifiedDate'])!.value
        ? moment(this.editForm.get(['lastModifiedDate'])!.value, DATE_TIME_FORMAT)
        : undefined,
      priceCompare: this.editForm.get(['priceCompare'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IBiddingQuotationHeader>>): void {
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

  trackById(index: number, item: IPriceCompare): any {
    return item.id;
  }
}
