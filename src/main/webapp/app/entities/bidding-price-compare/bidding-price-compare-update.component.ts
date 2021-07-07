import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IBiddingPriceCompare, BiddingPriceCompare } from 'app/shared/model/bidding-price-compare.model';
import { BiddingPriceCompareService } from './bidding-price-compare.service';

@Component({
  selector: 'jhi-bidding-price-compare-update',
  templateUrl: './bidding-price-compare-update.component.html',
})
export class BiddingPriceCompareUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    compareCode: [null, [Validators.required]],
    status: [null, [Validators.required]],
    description: [],
    createdBy: [],
    createdDate: [],
    lastModifiedBy: [],
    lastModifiedDate: [],
  });

  constructor(
    protected biddingPriceCompareService: BiddingPriceCompareService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ biddingPriceCompare }) => {
      if (!biddingPriceCompare.id) {
        const today = moment().startOf('day');
        biddingPriceCompare.createdDate = today;
        biddingPriceCompare.lastModifiedDate = today;
      }

      this.updateForm(biddingPriceCompare);
    });
  }

  updateForm(biddingPriceCompare: IBiddingPriceCompare): void {
    this.editForm.patchValue({
      id: biddingPriceCompare.id,
      compareCode: biddingPriceCompare.compareCode,
      status: biddingPriceCompare.status,
      description: biddingPriceCompare.description,
      createdBy: biddingPriceCompare.createdBy,
      createdDate: biddingPriceCompare.createdDate ? biddingPriceCompare.createdDate.format(DATE_TIME_FORMAT) : null,
      lastModifiedBy: biddingPriceCompare.lastModifiedBy,
      lastModifiedDate: biddingPriceCompare.lastModifiedDate ? biddingPriceCompare.lastModifiedDate.format(DATE_TIME_FORMAT) : null,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const biddingPriceCompare = this.createFromForm();
    if (biddingPriceCompare.id !== undefined) {
      this.subscribeToSaveResponse(this.biddingPriceCompareService.update(biddingPriceCompare));
    } else {
      this.subscribeToSaveResponse(this.biddingPriceCompareService.create(biddingPriceCompare));
    }
  }

  private createFromForm(): IBiddingPriceCompare {
    return {
      ...new BiddingPriceCompare(),
      id: this.editForm.get(['id'])!.value,
      compareCode: this.editForm.get(['compareCode'])!.value,
      status: this.editForm.get(['status'])!.value,
      description: this.editForm.get(['description'])!.value,
      createdBy: this.editForm.get(['createdBy'])!.value,
      createdDate: this.editForm.get(['createdDate'])!.value
        ? moment(this.editForm.get(['createdDate'])!.value, DATE_TIME_FORMAT)
        : undefined,
      lastModifiedBy: this.editForm.get(['lastModifiedBy'])!.value,
      lastModifiedDate: this.editForm.get(['lastModifiedDate'])!.value
        ? moment(this.editForm.get(['lastModifiedDate'])!.value, DATE_TIME_FORMAT)
        : undefined,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IBiddingPriceCompare>>): void {
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
}
