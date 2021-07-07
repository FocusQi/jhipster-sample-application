import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhipsterSampleApplicationSharedModule } from 'app/shared/shared.module';
import { BiddingOpenerComponent } from './bidding-opener.component';
import { BiddingOpenerDetailComponent } from './bidding-opener-detail.component';
import { BiddingOpenerUpdateComponent } from './bidding-opener-update.component';
import { BiddingOpenerDeleteDialogComponent } from './bidding-opener-delete-dialog.component';
import { biddingOpenerRoute } from './bidding-opener.route';

@NgModule({
  imports: [JhipsterSampleApplicationSharedModule, RouterModule.forChild(biddingOpenerRoute)],
  declarations: [BiddingOpenerComponent, BiddingOpenerDetailComponent, BiddingOpenerUpdateComponent, BiddingOpenerDeleteDialogComponent],
  entryComponents: [BiddingOpenerDeleteDialogComponent],
})
export class JhipsterSampleApplicationBiddingOpenerModule {}
