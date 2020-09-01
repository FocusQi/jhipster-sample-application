import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IBiddingQuotationInfo, BiddingQuotationInfo } from 'app/shared/model/bidding-quotation-info.model';
import { BiddingQuotationInfoService } from './bidding-quotation-info.service';
import { IBiddingMaterialRound } from 'app/shared/model/bidding-material-round.model';
import { BiddingMaterialRoundService } from 'app/entities/bidding-material-round/bidding-material-round.service';
import { IBiddingPriceCompare } from 'app/shared/model/bidding-price-compare.model';
import { BiddingPriceCompareService } from 'app/entities/bidding-price-compare/bidding-price-compare.service';

type SelectableEntity = IBiddingMaterialRound | IBiddingPriceCompare;

@Component({
  selector: 'jhi-bidding-quotation-info-update',
  templateUrl: './bidding-quotation-info-update.component.html',
})
export class BiddingQuotationInfoUpdateComponent implements OnInit {
  isSaving = false;
  biddingmaterialrounds: IBiddingMaterialRound[] = [];
  biddingpricecompares: IBiddingPriceCompare[] = [];
  validDateDp: any;
  currentPriceValidDateDp: any;

  editForm = this.fb.group({
    id: [],
    latestQuotation: [],
    minQty: [],
    validDate: [],
    deliveryDays: [],
    historyPrice: [],
    currentTaxPrice: [],
    currentPriceValidDate: [],
    netPrice: [],
    netUrl: [],
    status: [null, [Validators.required]],
    remark: [],
    createdBy: [],
    createdDate: [],
    lastModifiedBy: [],
    lastModifiedDate: [],
    materialRound: [],
    priceCompare: [],
  });

  constructor(
    protected biddingQuotationInfoService: BiddingQuotationInfoService,
    protected biddingMaterialRoundService: BiddingMaterialRoundService,
    protected biddingPriceCompareService: BiddingPriceCompareService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ biddingQuotationInfo }) => {
      if (!biddingQuotationInfo.id) {
        const today = moment().startOf('day');
        biddingQuotationInfo.createdDate = today;
        biddingQuotationInfo.lastModifiedDate = today;
      }

      this.updateForm(biddingQuotationInfo);

      this.biddingMaterialRoundService
        .query()
        .subscribe((res: HttpResponse<IBiddingMaterialRound[]>) => (this.biddingmaterialrounds = res.body || []));

      this.biddingPriceCompareService
        .query()
        .subscribe((res: HttpResponse<IBiddingPriceCompare[]>) => (this.biddingpricecompares = res.body || []));
    });
  }

  updateForm(biddingQuotationInfo: IBiddingQuotationInfo): void {
    this.editForm.patchValue({
      id: biddingQuotationInfo.id,
      latestQuotation: biddingQuotationInfo.latestQuotation,
      minQty: biddingQuotationInfo.minQty,
      validDate: biddingQuotationInfo.validDate,
      deliveryDays: biddingQuotationInfo.deliveryDays,
      historyPrice: biddingQuotationInfo.historyPrice,
      currentTaxPrice: biddingQuotationInfo.currentTaxPrice,
      currentPriceValidDate: biddingQuotationInfo.currentPriceValidDate,
      netPrice: biddingQuotationInfo.netPrice,
      netUrl: biddingQuotationInfo.netUrl,
      status: biddingQuotationInfo.status,
      remark: biddingQuotationInfo.remark,
      createdBy: biddingQuotationInfo.createdBy,
      createdDate: biddingQuotationInfo.createdDate ? biddingQuotationInfo.createdDate.format(DATE_TIME_FORMAT) : null,
      lastModifiedBy: biddingQuotationInfo.lastModifiedBy,
      lastModifiedDate: biddingQuotationInfo.lastModifiedDate ? biddingQuotationInfo.lastModifiedDate.format(DATE_TIME_FORMAT) : null,
      materialRound: biddingQuotationInfo.materialRound,
      priceCompare: biddingQuotationInfo.priceCompare,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const biddingQuotationInfo = this.createFromForm();
    if (biddingQuotationInfo.id !== undefined) {
      this.subscribeToSaveResponse(this.biddingQuotationInfoService.update(biddingQuotationInfo));
    } else {
      this.subscribeToSaveResponse(this.biddingQuotationInfoService.create(biddingQuotationInfo));
    }
  }

  private createFromForm(): IBiddingQuotationInfo {
    return {
      ...new BiddingQuotationInfo(),
      id: this.editForm.get(['id'])!.value,
      latestQuotation: this.editForm.get(['latestQuotation'])!.value,
      minQty: this.editForm.get(['minQty'])!.value,
      validDate: this.editForm.get(['validDate'])!.value,
      deliveryDays: this.editForm.get(['deliveryDays'])!.value,
      historyPrice: this.editForm.get(['historyPrice'])!.value,
      currentTaxPrice: this.editForm.get(['currentTaxPrice'])!.value,
      currentPriceValidDate: this.editForm.get(['currentPriceValidDate'])!.value,
      netPrice: this.editForm.get(['netPrice'])!.value,
      netUrl: this.editForm.get(['netUrl'])!.value,
      status: this.editForm.get(['status'])!.value,
      remark: this.editForm.get(['remark'])!.value,
      createdBy: this.editForm.get(['createdBy'])!.value,
      createdDate: this.editForm.get(['createdDate'])!.value
        ? moment(this.editForm.get(['createdDate'])!.value, DATE_TIME_FORMAT)
        : undefined,
      lastModifiedBy: this.editForm.get(['lastModifiedBy'])!.value,
      lastModifiedDate: this.editForm.get(['lastModifiedDate'])!.value
        ? moment(this.editForm.get(['lastModifiedDate'])!.value, DATE_TIME_FORMAT)
        : undefined,
      materialRound: this.editForm.get(['materialRound'])!.value,
      priceCompare: this.editForm.get(['priceCompare'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IBiddingQuotationInfo>>): void {
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
