import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IQuotationInfo, QuotationInfo } from 'app/shared/model/quotation-info.model';
import { QuotationInfoService } from './quotation-info.service';
import { IMaterialRound } from 'app/shared/model/material-round.model';
import { MaterialRoundService } from 'app/entities/material-round/material-round.service';
import { IPriceCompare } from 'app/shared/model/price-compare.model';
import { PriceCompareService } from 'app/entities/price-compare/price-compare.service';

type SelectableEntity = IMaterialRound | IPriceCompare;

@Component({
  selector: 'jhi-quotation-info-update',
  templateUrl: './quotation-info-update.component.html',
})
export class QuotationInfoUpdateComponent implements OnInit {
  isSaving = false;
  materialrounds: IMaterialRound[] = [];
  pricecompares: IPriceCompare[] = [];
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
    protected quotationInfoService: QuotationInfoService,
    protected materialRoundService: MaterialRoundService,
    protected priceCompareService: PriceCompareService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ quotationInfo }) => {
      if (!quotationInfo.id) {
        const today = moment().startOf('day');
        quotationInfo.createdDate = today;
        quotationInfo.lastModifiedDate = today;
      }

      this.updateForm(quotationInfo);

      this.materialRoundService.query().subscribe((res: HttpResponse<IMaterialRound[]>) => (this.materialrounds = res.body || []));

      this.priceCompareService.query().subscribe((res: HttpResponse<IPriceCompare[]>) => (this.pricecompares = res.body || []));
    });
  }

  updateForm(quotationInfo: IQuotationInfo): void {
    this.editForm.patchValue({
      id: quotationInfo.id,
      latestQuotation: quotationInfo.latestQuotation,
      minQty: quotationInfo.minQty,
      validDate: quotationInfo.validDate,
      deliveryDays: quotationInfo.deliveryDays,
      historyPrice: quotationInfo.historyPrice,
      currentTaxPrice: quotationInfo.currentTaxPrice,
      currentPriceValidDate: quotationInfo.currentPriceValidDate,
      netPrice: quotationInfo.netPrice,
      netUrl: quotationInfo.netUrl,
      status: quotationInfo.status,
      remark: quotationInfo.remark,
      createdBy: quotationInfo.createdBy,
      createdDate: quotationInfo.createdDate ? quotationInfo.createdDate.format(DATE_TIME_FORMAT) : null,
      lastModifiedBy: quotationInfo.lastModifiedBy,
      lastModifiedDate: quotationInfo.lastModifiedDate ? quotationInfo.lastModifiedDate.format(DATE_TIME_FORMAT) : null,
      materialRound: quotationInfo.materialRound,
      priceCompare: quotationInfo.priceCompare,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const quotationInfo = this.createFromForm();
    if (quotationInfo.id !== undefined) {
      this.subscribeToSaveResponse(this.quotationInfoService.update(quotationInfo));
    } else {
      this.subscribeToSaveResponse(this.quotationInfoService.create(quotationInfo));
    }
  }

  private createFromForm(): IQuotationInfo {
    return {
      ...new QuotationInfo(),
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

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IQuotationInfo>>): void {
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
