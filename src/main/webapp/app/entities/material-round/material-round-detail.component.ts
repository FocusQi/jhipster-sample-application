import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IMaterialRound } from 'app/shared/model/material-round.model';

@Component({
  selector: 'jhi-material-round-detail',
  templateUrl: './material-round-detail.component.html',
})
export class MaterialRoundDetailComponent implements OnInit {
  materialRound: IMaterialRound | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ materialRound }) => (this.materialRound = materialRound));
  }

  previousState(): void {
    window.history.back();
  }
}
