import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IBomTemplateInfo } from 'app/shared/model/bom-template-info.model';

@Component({
  selector: 'jhi-bom-template-info-detail',
  templateUrl: './bom-template-info-detail.component.html',
})
export class BomTemplateInfoDetailComponent implements OnInit {
  bomTemplateInfo: IBomTemplateInfo | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ bomTemplateInfo }) => (this.bomTemplateInfo = bomTemplateInfo));
  }

  previousState(): void {
    window.history.back();
  }
}
