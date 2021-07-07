import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IPriceCompare, PriceCompare } from 'app/shared/model/price-compare.model';
import { PriceCompareService } from './price-compare.service';

@Component({
  selector: 'jhi-price-compare-update',
  templateUrl: './price-compare-update.component.html',
})
export class PriceCompareUpdateComponent implements OnInit {
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

  constructor(protected priceCompareService: PriceCompareService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ priceCompare }) => {
      if (!priceCompare.id) {
        const today = moment().startOf('day');
        priceCompare.createdDate = today;
        priceCompare.lastModifiedDate = today;
      }

      this.updateForm(priceCompare);
    });
  }

  updateForm(priceCompare: IPriceCompare): void {
    this.editForm.patchValue({
      id: priceCompare.id,
      compareCode: priceCompare.compareCode,
      status: priceCompare.status,
      description: priceCompare.description,
      createdBy: priceCompare.createdBy,
      createdDate: priceCompare.createdDate ? priceCompare.createdDate.format(DATE_TIME_FORMAT) : null,
      lastModifiedBy: priceCompare.lastModifiedBy,
      lastModifiedDate: priceCompare.lastModifiedDate ? priceCompare.lastModifiedDate.format(DATE_TIME_FORMAT) : null,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const priceCompare = this.createFromForm();
    if (priceCompare.id !== undefined) {
      this.subscribeToSaveResponse(this.priceCompareService.update(priceCompare));
    } else {
      this.subscribeToSaveResponse(this.priceCompareService.create(priceCompare));
    }
  }

  private createFromForm(): IPriceCompare {
    return {
      ...new PriceCompare(),
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

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IPriceCompare>>): void {
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
