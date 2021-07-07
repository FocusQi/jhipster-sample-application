import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhipsterSampleApplicationSharedModule } from 'app/shared/shared.module';
import { BomTemplateInfoComponent } from './bom-template-info.component';
import { BomTemplateInfoDetailComponent } from './bom-template-info-detail.component';
import { BomTemplateInfoUpdateComponent } from './bom-template-info-update.component';
import { BomTemplateInfoDeleteDialogComponent } from './bom-template-info-delete-dialog.component';
import { bomTemplateInfoRoute } from './bom-template-info.route';

@NgModule({
  imports: [JhipsterSampleApplicationSharedModule, RouterModule.forChild(bomTemplateInfoRoute)],
  declarations: [
    BomTemplateInfoComponent,
    BomTemplateInfoDetailComponent,
    BomTemplateInfoUpdateComponent,
    BomTemplateInfoDeleteDialogComponent,
  ],
  entryComponents: [BomTemplateInfoDeleteDialogComponent],
})
export class JhipsterSampleApplicationBomTemplateInfoModule {}
