import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IBiddingMaterialRound, BiddingMaterialRound } from 'app/shared/model/bidding-material-round.model';
import { BiddingMaterialRoundService } from './bidding-material-round.service';
import { IMaterial } from 'app/shared/model/material.model';
import { MaterialService } from 'app/entities/material/material.service';
import { IBiddingRoundInfo } from 'app/shared/model/bidding-round-info.model';
import { BiddingRoundInfoService } from 'app/entities/bidding-round-info/bidding-round-info.service';

type SelectableEntity = IMaterial | IBiddingRoundInfo;

@Component({
  selector: 'jhi-bidding-material-round-update',
  templateUrl: './bidding-material-round-update.component.html',
})
export class BiddingMaterialRoundUpdateComponent implements OnInit {
  isSaving = false;
  materials: IMaterial[] = [];
  biddingroundinfos: IBiddingRoundInfo[] = [];

  editForm = this.fb.group({
    id: [],
    needQty: [null, [Validators.required]],
    priceCeiling: [],
    fileUrl: [],
    createdBy: [],
    createdDate: [],
    lastModifiedBy: [],
    lastModifiedDate: [],
    material: [],
    roundInfo: [],
  });

  constructor(
    protected biddingMaterialRoundService: BiddingMaterialRoundService,
    protected materialService: MaterialService,
    protected biddingRoundInfoService: BiddingRoundInfoService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ biddingMaterialRound }) => {
      if (!biddingMaterialRound.id) {
        const today = moment().startOf('day');
        biddingMaterialRound.createdDate = today;
        biddingMaterialRound.lastModifiedDate = today;
      }

      this.updateForm(biddingMaterialRound);

      this.materialService.query().subscribe((res: HttpResponse<IMaterial[]>) => (this.materials = res.body || []));

      this.biddingRoundInfoService.query().subscribe((res: HttpResponse<IBiddingRoundInfo[]>) => (this.biddingroundinfos = res.body || []));
    });
  }

  updateForm(biddingMaterialRound: IBiddingMaterialRound): void {
    this.editForm.patchValue({
      id: biddingMaterialRound.id,
      needQty: biddingMaterialRound.needQty,
      priceCeiling: biddingMaterialRound.priceCeiling,
      fileUrl: biddingMaterialRound.fileUrl,
      createdBy: biddingMaterialRound.createdBy,
      createdDate: biddingMaterialRound.createdDate ? biddingMaterialRound.createdDate.format(DATE_TIME_FORMAT) : null,
      lastModifiedBy: biddingMaterialRound.lastModifiedBy,
      lastModifiedDate: biddingMaterialRound.lastModifiedDate ? biddingMaterialRound.lastModifiedDate.format(DATE_TIME_FORMAT) : null,
      material: biddingMaterialRound.material,
      roundInfo: biddingMaterialRound.roundInfo,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const biddingMaterialRound = this.createFromForm();
    if (biddingMaterialRound.id !== undefined) {
      this.subscribeToSaveResponse(this.biddingMaterialRoundService.update(biddingMaterialRound));
    } else {
      this.subscribeToSaveResponse(this.biddingMaterialRoundService.create(biddingMaterialRound));
    }
  }

  private createFromForm(): IBiddingMaterialRound {
    return {
      ...new BiddingMaterialRound(),
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
      material: this.editForm.get(['material'])!.value,
      roundInfo: this.editForm.get(['roundInfo'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IBiddingMaterialRound>>): void {
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
