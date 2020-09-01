import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhipsterSampleApplicationSharedModule } from 'app/shared/shared.module';
import { OpenerComponent } from './opener.component';
import { OpenerDetailComponent } from './opener-detail.component';
import { OpenerUpdateComponent } from './opener-update.component';
import { OpenerDeleteDialogComponent } from './opener-delete-dialog.component';
import { openerRoute } from './opener.route';

@NgModule({
  imports: [JhipsterSampleApplicationSharedModule, RouterModule.forChild(openerRoute)],
  declarations: [OpenerComponent, OpenerDetailComponent, OpenerUpdateComponent, OpenerDeleteDialogComponent],
  entryComponents: [OpenerDeleteDialogComponent],
})
export class JhipsterSampleApplicationOpenerModule {}
