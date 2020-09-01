import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhipsterSampleApplicationSharedModule } from 'app/shared/shared.module';
import { BiddingRoundInfoComponent } from './bidding-round-info.component';
import { BiddingRoundInfoDetailComponent } from './bidding-round-info-detail.component';
import { BiddingRoundInfoUpdateComponent } from './bidding-round-info-update.component';
import { BiddingRoundInfoDeleteDialogComponent } from './bidding-round-info-delete-dialog.component';
import { biddingRoundInfoRoute } from './bidding-round-info.route';

@NgModule({
  imports: [JhipsterSampleApplicationSharedModule, RouterModule.forChild(biddingRoundInfoRoute)],
  declarations: [
    BiddingRoundInfoComponent,
    BiddingRoundInfoDetailComponent,
    BiddingRoundInfoUpdateComponent,
    BiddingRoundInfoDeleteDialogComponent,
  ],
  entryComponents: [BiddingRoundInfoDeleteDialogComponent],
})
export class JhipsterSampleApplicationBiddingRoundInfoModule {}
