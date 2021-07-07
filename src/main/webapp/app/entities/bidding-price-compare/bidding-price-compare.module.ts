import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhipsterSampleApplicationSharedModule } from 'app/shared/shared.module';
import { BiddingPriceCompareComponent } from './bidding-price-compare.component';
import { BiddingPriceCompareDetailComponent } from './bidding-price-compare-detail.component';
import { BiddingPriceCompareUpdateComponent } from './bidding-price-compare-update.component';
import { BiddingPriceCompareDeleteDialogComponent } from './bidding-price-compare-delete-dialog.component';
import { biddingPriceCompareRoute } from './bidding-price-compare.route';

@NgModule({
  imports: [JhipsterSampleApplicationSharedModule, RouterModule.forChild(biddingPriceCompareRoute)],
  declarations: [
    BiddingPriceCompareComponent,
    BiddingPriceCompareDetailComponent,
    BiddingPriceCompareUpdateComponent,
    BiddingPriceCompareDeleteDialogComponent,
  ],
  entryComponents: [BiddingPriceCompareDeleteDialogComponent],
})
export class JhipsterSampleApplicationBiddingPriceCompareModule {}
