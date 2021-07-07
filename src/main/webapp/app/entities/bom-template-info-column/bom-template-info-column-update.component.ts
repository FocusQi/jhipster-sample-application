import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IBomTemplateInfoColumn, BomTemplateInfoColumn } from 'app/shared/model/bom-template-info-column.model';
import { BomTemplateInfoColumnService } from './bom-template-info-column.service';
import { IBomTemplateInfo } from 'app/shared/model/bom-template-info.model';
import { BomTemplateInfoService } from 'app/entities/bom-template-info/bom-template-info.service';

@Component({
  selector: 'jhi-bom-template-info-column-update',
  templateUrl: './bom-template-info-column-update.component.html',
})
export class BomTemplateInfoColumnUpdateComponent implements OnInit {
  isSaving = false;
  bomtemplateinfos: IBomTemplateInfo[] = [];

  editForm = this.fb.group({
    id: [],
    sortIndex: [],
    columnName: [],
    columnValue: [],
    info: [],
  });

  constructor(
    protected bomTemplateInfoColumnService: BomTemplateInfoColumnService,
    protected bomTemplateInfoService: BomTemplateInfoService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ bomTemplateInfoColumn }) => {
      this.updateForm(bomTemplateInfoColumn);

      this.bomTemplateInfoService.query().subscribe((res: HttpResponse<IBomTemplateInfo[]>) => (this.bomtemplateinfos = res.body || []));
    });
  }

  updateForm(bomTemplateInfoColumn: IBomTemplateInfoColumn): void {
    this.editForm.patchValue({
      id: bomTemplateInfoColumn.id,
      sortIndex: bomTemplateInfoColumn.sortIndex,
      columnName: bomTemplateInfoColumn.columnName,
      columnValue: bomTemplateInfoColumn.columnValue,
      info: bomTemplateInfoColumn.info,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const bomTemplateInfoColumn = this.createFromForm();
    if (bomTemplateInfoColumn.id !== undefined) {
      this.subscribeToSaveResponse(this.bomTemplateInfoColumnService.update(bomTemplateInfoColumn));
    } else {
      this.subscribeToSaveResponse(this.bomTemplateInfoColumnService.create(bomTemplateInfoColumn));
    }
  }

  private createFromForm(): IBomTemplateInfoColumn {
    return {
      ...new BomTemplateInfoColumn(),
      id: this.editForm.get(['id'])!.value,
      sortIndex: this.editForm.get(['sortIndex'])!.value,
      columnName: this.editForm.get(['columnName'])!.value,
      columnValue: this.editForm.get(['columnValue'])!.value,
      info: this.editForm.get(['info'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IBomTemplateInfoColumn>>): void {
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

  trackById(index: number, item: IBomTemplateInfo): any {
    return item.id;
  }
}
