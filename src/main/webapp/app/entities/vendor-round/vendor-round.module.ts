import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhipsterSampleApplicationSharedModule } from 'app/shared/shared.module';
import { VendorRoundComponent } from './vendor-round.component';
import { VendorRoundDetailComponent } from './vendor-round-detail.component';
import { VendorRoundUpdateComponent } from './vendor-round-update.component';
import { VendorRoundDeleteDialogComponent } from './vendor-round-delete-dialog.component';
import { vendorRoundRoute } from './vendor-round.route';

@NgModule({
  imports: [JhipsterSampleApplicationSharedModule, RouterModule.forChild(vendorRoundRoute)],
  declarations: [VendorRoundComponent, VendorRoundDetailComponent, VendorRoundUpdateComponent, VendorRoundDeleteDialogComponent],
  entryComponents: [VendorRoundDeleteDialogComponent],
})
export class JhipsterSampleApplicationVendorRoundModule {}
