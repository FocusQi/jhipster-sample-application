import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IBomTemplateHeader } from 'app/shared/model/bom-template-header.model';

@Component({
  selector: 'jhi-bom-template-header-detail',
  templateUrl: './bom-template-header-detail.component.html',
})
export class BomTemplateHeaderDetailComponent implements OnInit {
  bomTemplateHeader: IBomTemplateHeader | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ bomTemplateHeader }) => (this.bomTemplateHeader = bomTemplateHeader));
  }

  previousState(): void {
    window.history.back();
  }
}
