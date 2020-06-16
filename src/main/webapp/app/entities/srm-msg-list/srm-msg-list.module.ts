import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhipsterSampleApplicationSharedModule } from 'app/shared/shared.module';
import { SrmMsgListComponent } from './srm-msg-list.component';
import { SrmMsgListDetailComponent } from './srm-msg-list-detail.component';
import { SrmMsgListUpdateComponent } from './srm-msg-list-update.component';
import { SrmMsgListDeleteDialogComponent } from './srm-msg-list-delete-dialog.component';
import { srmMsgListRoute } from './srm-msg-list.route';

@NgModule({
  imports: [JhipsterSampleApplicationSharedModule, RouterModule.forChild(srmMsgListRoute)],
  declarations: [SrmMsgListComponent, SrmMsgListDetailComponent, SrmMsgListUpdateComponent, SrmMsgListDeleteDialogComponent],
  entryComponents: [SrmMsgListDeleteDialogComponent],
})
export class JhipsterSampleApplicationSrmMsgListModule {}
