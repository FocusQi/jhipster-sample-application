import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IOpener } from 'app/shared/model/opener.model';

@Component({
  selector: 'jhi-opener-detail',
  templateUrl: './opener-detail.component.html',
})
export class OpenerDetailComponent implements OnInit {
  opener: IOpener | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ opener }) => (this.opener = opener));
  }

  previousState(): void {
    window.history.back();
  }
}
