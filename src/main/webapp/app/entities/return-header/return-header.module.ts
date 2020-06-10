import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhipsterSampleApplicationSharedModule } from 'app/shared/shared.module';
import { ReturnHeaderComponent } from './return-header.component';
import { ReturnHeaderDetailComponent } from './return-header-detail.component';
import { ReturnHeaderUpdateComponent } from './return-header-update.component';
import { ReturnHeaderDeleteDialogComponent } from './return-header-delete-dialog.component';
import { returnHeaderRoute } from './return-header.route';

@NgModule({
  imports: [JhipsterSampleApplicationSharedModule, RouterModule.forChild(returnHeaderRoute)],
  declarations: [ReturnHeaderComponent, ReturnHeaderDetailComponent, ReturnHeaderUpdateComponent, ReturnHeaderDeleteDialogComponent],
  entryComponents: [ReturnHeaderDeleteDialogComponent],
})
export class JhipsterSampleApplicationReturnHeaderModule {}
