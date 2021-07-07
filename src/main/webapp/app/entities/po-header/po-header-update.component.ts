import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IPoHeader, PoHeader } from 'app/shared/model/po-header.model';
import { PoHeaderService } from './po-header.service';
import { IPriceCompare } from 'app/shared/model/price-compare.model';
import { PriceCompareService } from 'app/entities/price-compare/price-compare.service';

@Component({
  selector: 'jhi-po-header-update',
  templateUrl: './po-header-update.component.html',
})
export class PoHeaderUpdateComponent implements OnInit {
  isSaving = false;
  pricecompares: IPriceCompare[] = [];

  editForm = this.fb.group({
    id: [],
    priceCompare: [],
  });

  constructor(
    protected poHeaderService: PoHeaderService,
    protected priceCompareService: PriceCompareService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ poHeader }) => {
      this.updateForm(poHeader);

      this.priceCompareService.query().subscribe((res: HttpResponse<IPriceCompare[]>) => (this.pricecompares = res.body || []));
    });
  }

  updateForm(poHeader: IPoHeader): void {
    this.editForm.patchValue({
      id: poHeader.id,
      priceCompare: poHeader.priceCompare,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const poHeader = this.createFromForm();
    if (poHeader.id !== undefined) {
      this.subscribeToSaveResponse(this.poHeaderService.update(poHeader));
    } else {
      this.subscribeToSaveResponse(this.poHeaderService.create(poHeader));
    }
  }

  private createFromForm(): IPoHeader {
    return {
      ...new PoHeader(),
      id: this.editForm.get(['id'])!.value,
      priceCompare: this.editForm.get(['priceCompare'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IPoHeader>>): void {
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

  trackById(index: number, item: IPriceCompare): any {
    return item.id;
  }
}
