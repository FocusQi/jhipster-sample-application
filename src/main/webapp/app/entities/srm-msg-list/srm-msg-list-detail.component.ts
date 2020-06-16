import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ISrmMsgList } from 'app/shared/model/srm-msg-list.model';

@Component({
  selector: 'jhi-srm-msg-list-detail',
  templateUrl: './srm-msg-list-detail.component.html',
})
export class SrmMsgListDetailComponent implements OnInit {
  srmMsgList: ISrmMsgList | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ srmMsgList }) => (this.srmMsgList = srmMsgList));
  }

  previousState(): void {
    window.history.back();
  }
}
