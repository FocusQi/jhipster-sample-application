import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhipsterSampleApplicationSharedModule } from 'app/shared/shared.module';
import { BiddingVendorRoundComponent } from './bidding-vendor-round.component';
import { BiddingVendorRoundDetailComponent } from './bidding-vendor-round-detail.component';
import { BiddingVendorRoundUpdateComponent } from './bidding-vendor-round-update.component';
import { BiddingVendorRoundDeleteDialogComponent } from './bidding-vendor-round-delete-dialog.component';
import { biddingVendorRoundRoute } from './bidding-vendor-round.route';

@NgModule({
  imports: [JhipsterSampleApplicationSharedModule, RouterModule.forChild(biddingVendorRoundRoute)],
  declarations: [
    BiddingVendorRoundComponent,
    BiddingVendorRoundDetailComponent,
    BiddingVendorRoundUpdateComponent,
    BiddingVendorRoundDeleteDialogComponent,
  ],
  entryComponents: [BiddingVendorRoundDeleteDialogComponent],
})
export class JhipsterSampleApplicationBiddingVendorRoundModule {}
