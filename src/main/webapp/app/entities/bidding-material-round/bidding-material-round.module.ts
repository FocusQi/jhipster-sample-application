import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhipsterSampleApplicationSharedModule } from 'app/shared/shared.module';
import { BiddingMaterialRoundComponent } from './bidding-material-round.component';
import { BiddingMaterialRoundDetailComponent } from './bidding-material-round-detail.component';
import { BiddingMaterialRoundUpdateComponent } from './bidding-material-round-update.component';
import { BiddingMaterialRoundDeleteDialogComponent } from './bidding-material-round-delete-dialog.component';
import { biddingMaterialRoundRoute } from './bidding-material-round.route';

@NgModule({
  imports: [JhipsterSampleApplicationSharedModule, RouterModule.forChild(biddingMaterialRoundRoute)],
  declarations: [
    BiddingMaterialRoundComponent,
    BiddingMaterialRoundDetailComponent,
    BiddingMaterialRoundUpdateComponent,
    BiddingMaterialRoundDeleteDialogComponent,
  ],
  entryComponents: [BiddingMaterialRoundDeleteDialogComponent],
})
export class JhipsterSampleApplicationBiddingMaterialRoundModule {}
