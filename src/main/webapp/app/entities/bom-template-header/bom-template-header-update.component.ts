import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IBomTemplateHeader, BomTemplateHeader } from 'app/shared/model/bom-template-header.model';
import { BomTemplateHeaderService } from './bom-template-header.service';
import { IBiddingMaterialRound } from 'app/shared/model/bidding-material-round.model';
import { BiddingMaterialRoundService } from 'app/entities/bidding-material-round/bidding-material-round.service';

@Component({
  selector: 'jhi-bom-template-header-update',
  templateUrl: './bom-template-header-update.component.html',
})
export class BomTemplateHeaderUpdateComponent implements OnInit {
  isSaving = false;
  biddingmaterialrounds: IBiddingMaterialRound[] = [];

  editForm = this.fb.group({
    id: [],
    templateCode: [null, [Validators.required]],
    templateName: [null, [Validators.required]],
    templateType: [null, [Validators.required]],
    remark: [],
    createdBy: [],
    createdDate: [],
    lastModifiedBy: [],
    lastModifiedDate: [],
    materialRound: [],
  });

  constructor(
    protected bomTemplateHeaderService: BomTemplateHeaderService,
    protected biddingMaterialRoundService: BiddingMaterialRoundService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ bomTemplateHeader }) => {
      if (!bomTemplateHeader.id) {
        const today = moment().startOf('day');
        bomTemplateHeader.createdDate = today;
        bomTemplateHeader.lastModifiedDate = today;
      }

      this.updateForm(bomTemplateHeader);

      this.biddingMaterialRoundService
        .query()
        .subscribe((res: HttpResponse<IBiddingMaterialRound[]>) => (this.biddingmaterialrounds = res.body || []));
    });
  }

  updateForm(bomTemplateHeader: IBomTemplateHeader): void {
    this.editForm.patchValue({
      id: bomTemplateHeader.id,
      templateCode: bomTemplateHeader.templateCode,
      templateName: bomTemplateHeader.templateName,
      templateType: bomTemplateHeader.templateType,
      remark: bomTemplateHeader.remark,
      createdBy: bomTemplateHeader.createdBy,
      createdDate: bomTemplateHeader.createdDate ? bomTemplateHeader.createdDate.format(DATE_TIME_FORMAT) : null,
      lastModifiedBy: bomTemplateHeader.lastModifiedBy,
      lastModifiedDate: bomTemplateHeader.lastModifiedDate ? bomTemplateHeader.lastModifiedDate.format(DATE_TIME_FORMAT) : null,
      materialRound: bomTemplateHeader.materialRound,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const bomTemplateHeader = this.createFromForm();
    if (bomTemplateHeader.id !== undefined) {
      this.subscribeToSaveResponse(this.bomTemplateHeaderService.update(bomTemplateHeader));
    } else {
      this.subscribeToSaveResponse(this.bomTemplateHeaderService.create(bomTemplateHeader));
    }
  }

  private createFromForm(): IBomTemplateHeader {
    return {
      ...new BomTemplateHeader(),
      id: this.editForm.get(['id'])!.value,
      templateCode: this.editForm.get(['templateCode'])!.value,
      templateName: this.editForm.get(['templateName'])!.value,
      templateType: this.editForm.get(['templateType'])!.value,
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
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IBomTemplateHeader>>): void {
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

  trackById(index: number, item: IBiddingMaterialRound): any {
    return item.id;
  }
}
