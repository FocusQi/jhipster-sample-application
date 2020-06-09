import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IOrg, Org } from 'app/shared/model/org.model';
import { OrgService } from './org.service';

@Component({
  selector: 'jhi-org-update',
  templateUrl: './org-update.component.html',
})
export class OrgUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    name: [],
  });

  constructor(protected orgService: OrgService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ org }) => {
      this.updateForm(org);
    });
  }

  updateForm(org: IOrg): void {
    this.editForm.patchValue({
      id: org.id,
      name: org.name,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const org = this.createFromForm();
    if (org.id !== undefined) {
      this.subscribeToSaveResponse(this.orgService.update(org));
    } else {
      this.subscribeToSaveResponse(this.orgService.create(org));
    }
  }

  private createFromForm(): IOrg {
    return {
      ...new Org(),
      id: this.editForm.get(['id'])!.value,
      name: this.editForm.get(['name'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IOrg>>): void {
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
