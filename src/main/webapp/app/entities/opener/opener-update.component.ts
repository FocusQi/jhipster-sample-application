import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IOpener, Opener } from 'app/shared/model/opener.model';
import { OpenerService } from './opener.service';

@Component({
  selector: 'jhi-opener-update',
  templateUrl: './opener-update.component.html',
})
export class OpenerUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    userId: [null, [Validators.required]],
    secretKey: [null, [Validators.required]],
    createdBy: [],
    createdDate: [],
    lastModifiedBy: [],
    lastModifiedDate: [],
  });

  constructor(protected openerService: OpenerService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ opener }) => {
      if (!opener.id) {
        const today = moment().startOf('day');
        opener.createdDate = today;
        opener.lastModifiedDate = today;
      }

      this.updateForm(opener);
    });
  }

  updateForm(opener: IOpener): void {
    this.editForm.patchValue({
      id: opener.id,
      userId: opener.userId,
      secretKey: opener.secretKey,
      createdBy: opener.createdBy,
      createdDate: opener.createdDate ? opener.createdDate.format(DATE_TIME_FORMAT) : null,
      lastModifiedBy: opener.lastModifiedBy,
      lastModifiedDate: opener.lastModifiedDate ? opener.lastModifiedDate.format(DATE_TIME_FORMAT) : null,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const opener = this.createFromForm();
    if (opener.id !== undefined) {
      this.subscribeToSaveResponse(this.openerService.update(opener));
    } else {
      this.subscribeToSaveResponse(this.openerService.create(opener));
    }
  }

  private createFromForm(): IOpener {
    return {
      ...new Opener(),
      id: this.editForm.get(['id'])!.value,
      userId: this.editForm.get(['userId'])!.value,
      secretKey: this.editForm.get(['secretKey'])!.value,
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

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IOpener>>): void {
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
