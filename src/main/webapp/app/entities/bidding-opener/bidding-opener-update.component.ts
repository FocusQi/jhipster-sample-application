import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IBiddingOpener, BiddingOpener } from 'app/shared/model/bidding-opener.model';
import { BiddingOpenerService } from './bidding-opener.service';
import { IBiddingQuotationHeader } from 'app/shared/model/bidding-quotation-header.model';
import { BiddingQuotationHeaderService } from 'app/entities/bidding-quotation-header/bidding-quotation-header.service';

@Component({
  selector: 'jhi-bidding-opener-update',
  templateUrl: './bidding-opener-update.component.html',
})
export class BiddingOpenerUpdateComponent implements OnInit {
  isSaving = false;
  biddingquotationheaders: IBiddingQuotationHeader[] = [];

  editForm = this.fb.group({
    id: [],
    userId: [null, [Validators.required]],
    secretKey: [null, [Validators.required]],
    createdBy: [],
    createdDate: [],
    lastModifiedBy: [],
    lastModifiedDate: [],
    bqHeader: [],
  });

  constructor(
    protected biddingOpenerService: BiddingOpenerService,
    protected biddingQuotationHeaderService: BiddingQuotationHeaderService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ biddingOpener }) => {
      if (!biddingOpener.id) {
        const today = moment().startOf('day');
        biddingOpener.createdDate = today;
        biddingOpener.lastModifiedDate = today;
      }

      this.updateForm(biddingOpener);

      this.biddingQuotationHeaderService
        .query()
        .subscribe((res: HttpResponse<IBiddingQuotationHeader[]>) => (this.biddingquotationheaders = res.body || []));
    });
  }

  updateForm(biddingOpener: IBiddingOpener): void {
    this.editForm.patchValue({
      id: biddingOpener.id,
      userId: biddingOpener.userId,
      secretKey: biddingOpener.secretKey,
      createdBy: biddingOpener.createdBy,
      createdDate: biddingOpener.createdDate ? biddingOpener.createdDate.format(DATE_TIME_FORMAT) : null,
      lastModifiedBy: biddingOpener.lastModifiedBy,
      lastModifiedDate: biddingOpener.lastModifiedDate ? biddingOpener.lastModifiedDate.format(DATE_TIME_FORMAT) : null,
      bqHeader: biddingOpener.bqHeader,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const biddingOpener = this.createFromForm();
    if (biddingOpener.id !== undefined) {
      this.subscribeToSaveResponse(this.biddingOpenerService.update(biddingOpener));
    } else {
      this.subscribeToSaveResponse(this.biddingOpenerService.create(biddingOpener));
    }
  }

  private createFromForm(): IBiddingOpener {
    return {
      ...new BiddingOpener(),
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
      bqHeader: this.editForm.get(['bqHeader'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IBiddingOpener>>): void {
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
