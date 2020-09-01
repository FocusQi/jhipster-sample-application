import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhipsterSampleApplicationSharedModule } from 'app/shared/shared.module';
import { BomTemplateInfoColumnComponent } from './bom-template-info-column.component';
import { BomTemplateInfoColumnDetailComponent } from './bom-template-info-column-detail.component';
import { BomTemplateInfoColumnUpdateComponent } from './bom-template-info-column-update.component';
import { BomTemplateInfoColumnDeleteDialogComponent } from './bom-template-info-column-delete-dialog.component';
import { bomTemplateInfoColumnRoute } from './bom-template-info-column.route';

@NgModule({
  imports: [JhipsterSampleApplicationSharedModule, RouterModule.forChild(bomTemplateInfoColumnRoute)],
  declarations: [
    BomTemplateInfoColumnComponent,
    BomTemplateInfoColumnDetailComponent,
    BomTemplateInfoColumnUpdateComponent,
    BomTemplateInfoColumnDeleteDialogComponent,
  ],
  entryComponents: [BomTemplateInfoColumnDeleteDialogComponent],
})
export class JhipsterSampleApplicationBomTemplateInfoColumnModule {}
