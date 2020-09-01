import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IMaterialRound, MaterialRound } from 'app/shared/model/material-round.model';
import { MaterialRoundService } from './material-round.service';
import { IBiddingRoundInfo } from 'app/shared/model/bidding-round-info.model';
import { BiddingRoundInfoService } from 'app/entities/bidding-round-info/bidding-round-info.service';
import { IMaterial } from 'app/shared/model/material.model';
import { MaterialService } from 'app/entities/material/material.service';

type SelectableEntity = IBiddingRoundInfo | IMaterial;

@Component({
  selector: 'jhi-material-round-update',
  templateUrl: './material-round-update.component.html',
})
export class MaterialRoundUpdateComponent implements OnInit {
  isSaving = false;
  biddingroundinfos: IBiddingRoundInfo[] = [];
  materials: IMaterial[] = [];

  editForm = this.fb.group({
    id: [],
    needQty: [null, [Validators.required]],
    priceCeiling: [],
    fileUrl: [],
    createdBy: [],
    createdDate: [],
    lastModifiedBy: [],
    lastModifiedDate: [],
    roundInfo: [],
    material: [],
  });

  constructor(
    protected materialRoundService: MaterialRoundService,
    protected biddingRoundInfoService: BiddingRoundInfoService,
    protected materialService: MaterialService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ materialRound }) => {
      if (!materialRound.id) {
        const today = moment().startOf('day');
        materialRound.createdDate = today;
        materialRound.lastModifiedDate = today;
      }

      this.updateForm(materialRound);

      this.biddingRoundInfoService.query().subscribe((res: HttpResponse<IBiddingRoundInfo[]>) => (this.biddingroundinfos = res.body || []));

      this.materialService.query().subscribe((res: HttpResponse<IMaterial[]>) => (this.materials = res.body || []));
    });
  }

  updateForm(materialRound: IMaterialRound): void {
    this.editForm.patchValue({
      id: materialRound.id,
      needQty: materialRound.needQty,
      priceCeiling: materialRound.priceCeiling,
      fileUrl: materialRound.fileUrl,
      createdBy: materialRound.createdBy,
      createdDate: materialRound.createdDate ? materialRound.createdDate.format(DATE_TIME_FORMAT) : null,
      lastModifiedBy: materialRound.lastModifiedBy,
      lastModifiedDate: materialRound.lastModifiedDate ? materialRound.lastModifiedDate.format(DATE_TIME_FORMAT) : null,
      roundInfo: materialRound.roundInfo,
      material: materialRound.material,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const materialRound = this.createFromForm();
    if (materialRound.id !== undefined) {
      this.subscribeToSaveResponse(this.materialRoundService.update(materialRound));
    } else {
      this.subscribeToSaveResponse(this.materialRoundService.create(materialRound));
    }
  }

  private createFromForm(): IMaterialRound {
    return {
      ...new MaterialRound(),
      id: this.editForm.get(['id'])!.value,
      needQty: this.editForm.get(['needQty'])!.value,
      priceCeiling: this.editForm.get(['priceCeiling'])!.value,
      fileUrl: this.editForm.get(['fileUrl'])!.value,
      createdBy: this.editForm.get(['createdBy'])!.value,
      createdDate: this.editForm.get(['createdDate'])!.value
        ? moment(this.editForm.get(['createdDate'])!.value, DATE_TIME_FORMAT)
        : undefined,
      lastModifiedBy: this.editForm.get(['lastModifiedBy'])!.value,
      lastModifiedDate: this.editForm.get(['lastModifiedDate'])!.value
        ? moment(this.editForm.get(['lastModifiedDate'])!.value, DATE_TIME_FORMAT)
        : undefined,
      roundInfo: this.editForm.get(['roundInfo'])!.value,
      material: this.editForm.get(['material'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMaterialRound>>): void {
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
