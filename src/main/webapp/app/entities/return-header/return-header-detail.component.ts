import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IReturnHeader } from 'app/shared/model/return-header.model';

@Component({
  selector: 'jhi-return-header-detail',
  templateUrl: './return-header-detail.component.html',
})
export class ReturnHeaderDetailComponent implements OnInit {
  returnHeader: IReturnHeader | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ returnHeader }) => (this.returnHeader = returnHeader));
  }

  previousState(): void {
    window.history.back();
  }
}
