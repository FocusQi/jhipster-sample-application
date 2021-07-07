import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhipsterSampleApplicationSharedModule } from 'app/shared/shared.module';
import { MaterialRoundComponent } from './material-round.component';
import { MaterialRoundDetailComponent } from './material-round-detail.component';
import { MaterialRoundUpdateComponent } from './material-round-update.component';
import { MaterialRoundDeleteDialogComponent } from './material-round-delete-dialog.component';
import { materialRoundRoute } from './material-round.route';

@NgModule({
  imports: [JhipsterSampleApplicationSharedModule, RouterModule.forChild(materialRoundRoute)],
  declarations: [MaterialRoundComponent, MaterialRoundDetailComponent, MaterialRoundUpdateComponent, MaterialRoundDeleteDialogComponent],
  entryComponents: [MaterialRoundDeleteDialogComponent],
})
export class JhipsterSampleApplicationMaterialRoundModule {}
