import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IBomTemplateInfo, BomTemplateInfo } from 'app/shared/model/bom-template-info.model';
import { BomTemplateInfoService } from './bom-template-info.service';
import { IBomTemplateHeader } from 'app/shared/model/bom-template-header.model';
import { BomTemplateHeaderService } from 'app/entities/bom-template-header/bom-template-header.service';

@Component({
  selector: 'jhi-bom-template-info-update',
  templateUrl: './bom-template-info-update.component.html',
})
export class BomTemplateInfoUpdateComponent implements OnInit {
  isSaving = false;
  bomtemplateheaders: IBomTemplateHeader[] = [];

  editForm = this.fb.group({
    id: [],
    uom: [],
    useQty: [],
    unitPrice: [],
    total: [],
    tax: [],
    taxTotal: [],
    remark: [],
    createdBy: [],
    createdDate: [],
    lastModifiedBy: [],
    lastModifiedDate: [],
    header: [],
  });

  constructor(
    protected bomTemplateInfoService: BomTemplateInfoService,
    protected bomTemplateHeaderService: BomTemplateHeaderService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ bomTemplateInfo }) => {
      if (!bomTemplateInfo.id) {
        const today = moment().startOf('day');
        bomTemplateInfo.createdDate = today;
        bomTemplateInfo.lastModifiedDate = today;
      }

      this.updateForm(bomTemplateInfo);

      this.bomTemplateHeaderService
        .query()
        .subscribe((res: HttpResponse<IBomTemplateHeader[]>) => (this.bomtemplateheaders = res.body || []));
    });
  }

  updateForm(bomTemplateInfo: IBomTemplateInfo): void {
    this.editForm.patchValue({
      id: bomTemplateInfo.id,
      uom: bomTemplateInfo.uom,
      useQty: bomTemplateInfo.useQty,
      unitPrice: bomTemplateInfo.unitPrice,
      total: bomTemplateInfo.total,
      tax: bomTemplateInfo.tax,
      taxTotal: bomTemplateInfo.taxTotal,
      remark: bomTemplateInfo.remark,
      createdBy: bomTemplateInfo.createdBy,
      createdDate: bomTemplateInfo.createdDate ? bomTemplateInfo.createdDate.format(DATE_TIME_FORMAT) : null,
      lastModifiedBy: bomTemplateInfo.lastModifiedBy,
      lastModifiedDate: bomTemplateInfo.lastModifiedDate ? bomTemplateInfo.lastModifiedDate.format(DATE_TIME_FORMAT) : null,
      header: bomTemplateInfo.header,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const bomTemplateInfo = this.createFromForm();
    if (bomTemplateInfo.id !== undefined) {
      this.subscribeToSaveResponse(this.bomTemplateInfoService.update(bomTemplateInfo));
    } else {
      this.subscribeToSaveResponse(this.bomTemplateInfoService.create(bomTemplateInfo));
    }
  }

  private createFromForm(): IBomTemplateInfo {
    return {
      ...new BomTemplateInfo(),
      id: this.editForm.get(['id'])!.value,
      uom: this.editForm.get(['uom'])!.value,
      useQty: this.editForm.get(['useQty'])!.value,
      unitPrice: this.editForm.get(['unitPrice'])!.value,
      total: this.editForm.get(['total'])!.value,
      tax: this.editForm.get(['tax'])!.value,
      taxTotal: this.editForm.get(['taxTotal'])!.value,
      remark: this.editForm.get(['remark'])!.value,
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

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IBomTemplateInfo>>): void {
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

  trackById(index: number, item: IBomTemplateHeader): any {
    return item.id;
  }
}
