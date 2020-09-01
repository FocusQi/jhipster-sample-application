import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhipsterSampleApplicationSharedModule } from 'app/shared/shared.module';
import { BomTemplateHeaderComponent } from './bom-template-header.component';
import { BomTemplateHeaderDetailComponent } from './bom-template-header-detail.component';
import { BomTemplateHeaderUpdateComponent } from './bom-template-header-update.component';
import { BomTemplateHeaderDeleteDialogComponent } from './bom-template-header-delete-dialog.component';
import { bomTemplateHeaderRoute } from './bom-template-header.route';

@NgModule({
  imports: [JhipsterSampleApplicationSharedModule, RouterModule.forChild(bomTemplateHeaderRoute)],
  declarations: [
    BomTemplateHeaderComponent,
    BomTemplateHeaderDetailComponent,
    BomTemplateHeaderUpdateComponent,
    BomTemplateHeaderDeleteDialogComponent,
  ],
  entryComponents: [BomTemplateHeaderDeleteDialogComponent],
})
export class JhipsterSampleApplicationBomTemplateHeaderModule {}
