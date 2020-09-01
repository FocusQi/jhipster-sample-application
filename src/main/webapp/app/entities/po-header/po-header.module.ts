import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhipsterSampleApplicationSharedModule } from 'app/shared/shared.module';
import { PoHeaderComponent } from './po-header.component';
import { PoHeaderDetailComponent } from './po-header-detail.component';
import { PoHeaderUpdateComponent } from './po-header-update.component';
import { PoHeaderDeleteDialogComponent } from './po-header-delete-dialog.component';
import { poHeaderRoute } from './po-header.route';

@NgModule({
  imports: [JhipsterSampleApplicationSharedModule, RouterModule.forChild(poHeaderRoute)],
  declarations: [PoHeaderComponent, PoHeaderDetailComponent, PoHeaderUpdateComponent, PoHeaderDeleteDialogComponent],
  entryComponents: [PoHeaderDeleteDialogComponent],
})
export class JhipsterSampleApplicationPoHeaderModule {}
