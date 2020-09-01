import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IBomTemplateInfoColumn } from 'app/shared/model/bom-template-info-column.model';

@Component({
  selector: 'jhi-bom-template-info-column-detail',
  templateUrl: './bom-template-info-column-detail.component.html',
})
export class BomTemplateInfoColumnDetailComponent implements OnInit {
  bomTemplateInfoColumn: IBomTemplateInfoColumn | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ bomTemplateInfoColumn }) => (this.bomTemplateInfoColumn = bomTemplateInfoColumn));
  }

  previousState(): void {
    window.history.back();
  }
}
