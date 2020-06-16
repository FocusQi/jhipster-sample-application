import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { ISrmMsgList, SrmMsgList } from 'app/shared/model/srm-msg-list.model';
import { SrmMsgListService } from './srm-msg-list.service';

@Component({
  selector: 'jhi-srm-msg-list-update',
  templateUrl: './srm-msg-list-update.component.html',
})
export class SrmMsgListUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    msgId: [],
    msgTopic: [null, [Validators.maxLength(500)]],
    msgContent: [null, [Validators.maxLength(4000)]],
    msgMailTo: [null, [Validators.maxLength(4000)]],
    msgSmsTo: [null, [Validators.maxLength(500)]],
    msgCreateTime: [],
    msgSendTime: [],
    msgStatus: [null, [Validators.maxLength(3)]],
    msfMemo: [null, [Validators.maxLength(50)]],
    msgToBackUp: [null, [Validators.maxLength(2)]],
    remark: [null, [Validators.maxLength(4000)]],
    lastModifiedBy: [],
    createdBy: [],
    createdDate: [],
    lastModifiedDate: [],
  });

  constructor(protected srmMsgListService: SrmMsgListService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ srmMsgList }) => {
      if (!srmMsgList.id) {
        const today = moment().startOf('day');
        srmMsgList.msgCreateTime = today;
        srmMsgList.msgSendTime = today;
        srmMsgList.createdDate = today;
        srmMsgList.lastModifiedDate = today;
      }

      this.updateForm(srmMsgList);
    });
  }

  updateForm(srmMsgList: ISrmMsgList): void {
    this.editForm.patchValue({
      id: srmMsgList.id,
      msgId: srmMsgList.msgId,
      msgTopic: srmMsgList.msgTopic,
      msgContent: srmMsgList.msgContent,
      msgMailTo: srmMsgList.msgMailTo,
      msgSmsTo: srmMsgList.msgSmsTo,
      msgCreateTime: srmMsgList.msgCreateTime ? srmMsgList.msgCreateTime.format(DATE_TIME_FORMAT) : null,
      msgSendTime: srmMsgList.msgSendTime ? srmMsgList.msgSendTime.format(DATE_TIME_FORMAT) : null,
      msgStatus: srmMsgList.msgStatus,
      msfMemo: srmMsgList.msfMemo,
      msgToBackUp: srmMsgList.msgToBackUp,
      remark: srmMsgList.remark,
      lastModifiedBy: srmMsgList.lastModifiedBy,
      createdBy: srmMsgList.createdBy,
      createdDate: srmMsgList.createdDate ? srmMsgList.createdDate.format(DATE_TIME_FORMAT) : null,
      lastModifiedDate: srmMsgList.lastModifiedDate ? srmMsgList.lastModifiedDate.format(DATE_TIME_FORMAT) : null,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const srmMsgList = this.createFromForm();
    if (srmMsgList.id !== undefined) {
      this.subscribeToSaveResponse(this.srmMsgListService.update(srmMsgList));
    } else {
      this.subscribeToSaveResponse(this.srmMsgListService.create(srmMsgList));
    }
  }

  private createFromForm(): ISrmMsgList {
    return {
      ...new SrmMsgList(),
      id: this.editForm.get(['id'])!.value,
      msgId: this.editForm.get(['msgId'])!.value,
      msgTopic: this.editForm.get(['msgTopic'])!.value,
      msgContent: this.editForm.get(['msgContent'])!.value,
      msgMailTo: this.editForm.get(['msgMailTo'])!.value,
      msgSmsTo: this.editForm.get(['msgSmsTo'])!.value,
      msgCreateTime: this.editForm.get(['msgCreateTime'])!.value
        ? moment(this.editForm.get(['msgCreateTime'])!.value, DATE_TIME_FORMAT)
        : undefined,
      msgSendTime: this.editForm.get(['msgSendTime'])!.value
        ? moment(this.editForm.get(['msgSendTime'])!.value, DATE_TIME_FORMAT)
        : undefined,
      msgStatus: this.editForm.get(['msgStatus'])!.value,
      msfMemo: this.editForm.get(['msfMemo'])!.value,
      msgToBackUp: this.editForm.get(['msgToBackUp'])!.value,
      remark: this.editForm.get(['remark'])!.value,
      lastModifiedBy: this.editForm.get(['lastModifiedBy'])!.value,
      createdBy: this.editForm.get(['createdBy'])!.value,
      createdDate: this.editForm.get(['createdDate'])!.value
        ? moment(this.editForm.get(['createdDate'])!.value, DATE_TIME_FORMAT)
        : undefined,
      lastModifiedDate: this.editForm.get(['lastModifiedDate'])!.value
        ? moment(this.editForm.get(['lastModifiedDate'])!.value, DATE_TIME_FORMAT)
        : undefined,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ISrmMsgList>>): void {
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
