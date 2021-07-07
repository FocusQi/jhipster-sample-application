import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IReturnHeader, ReturnHeader } from 'app/shared/model/return-header.model';
import { ReturnHeaderService } from './return-header.service';

@Component({
  selector: 'jhi-return-header-update',
  templateUrl: './return-header-update.component.html',
})
export class ReturnHeaderUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    fromNum: [],
    vCode: [null, [Validators.maxLength(32)]],
    mCode: [null, [Validators.maxLength(32)]],
    quantity: [],
    createdBy: [null, [Validators.maxLength(32)]],
    createdDate: [],
    lastModifiedBy: [null, [Validators.maxLength(32)]],
    lastModifiedDate: [],
  });

  constructor(protected returnHeaderService: ReturnHeaderService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ returnHeader }) => {
      if (!returnHeader.id) {
        const today = moment().startOf('day');
        returnHeader.createdDate = today;
        returnHeader.lastModifiedDate = today;
      }

      this.updateForm(returnHeader);
    });
  }

  updateForm(returnHeader: IReturnHeader): void {
    this.editForm.patchValue({
      id: returnHeader.id,
      fromNum: returnHeader.fromNum,
      vCode: returnHeader.vCode,
      mCode: returnHeader.mCode,
      quantity: returnHeader.quantity,
      createdBy: returnHeader.createdBy,
      createdDate: returnHeader.createdDate ? returnHeader.createdDate.format(DATE_TIME_FORMAT) : null,
      lastModifiedBy: returnHeader.lastModifiedBy,
      lastModifiedDate: returnHeader.lastModifiedDate ? returnHeader.lastModifiedDate.format(DATE_TIME_FORMAT) : null,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const returnHeader = this.createFromForm();
    if (returnHeader.id !== undefined) {
      this.subscribeToSaveResponse(this.returnHeaderService.update(returnHeader));
    } else {
      this.subscribeToSaveResponse(this.returnHeaderService.create(returnHeader));
    }
  }

  private createFromForm(): IReturnHeader {
    return {
      ...new ReturnHeader(),
      id: this.editForm.get(['id'])!.value,
      fromNum: this.editForm.get(['fromNum'])!.value,
      vCode: this.editForm.get(['vCode'])!.value,
      mCode: this.editForm.get(['mCode'])!.value,
      quantity: this.editForm.get(['quantity'])!.value,
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

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IReturnHeader>>): void {
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
