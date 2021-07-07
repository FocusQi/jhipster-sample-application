import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IPoHeader } from 'app/shared/model/po-header.model';

@Component({
  selector: 'jhi-po-header-detail',
  templateUrl: './po-header-detail.component.html',
})
export class PoHeaderDetailComponent implements OnInit {
  poHeader: IPoHeader | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ poHeader }) => (this.poHeader = poHeader));
  }

  previousState(): void {
    window.history.back();
  }
}
