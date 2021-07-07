import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhipsterSampleApplicationSharedModule } from 'app/shared/shared.module';
import { BiddingQuotationInfoComponent } from './bidding-quotation-info.component';
import { BiddingQuotationInfoDetailComponent } from './bidding-quotation-info-detail.component';
import { BiddingQuotationInfoUpdateComponent } from './bidding-quotation-info-update.component';
import { BiddingQuotationInfoDeleteDialogComponent } from './bidding-quotation-info-delete-dialog.component';
import { biddingQuotationInfoRoute } from './bidding-quotation-info.route';

@NgModule({
  imports: [JhipsterSampleApplicationSharedModule, RouterModule.forChild(biddingQuotationInfoRoute)],
  declarations: [
    BiddingQuotationInfoComponent,
    BiddingQuotationInfoDetailComponent,
    BiddingQuotationInfoUpdateComponent,
    BiddingQuotationInfoDeleteDialogComponent,
  ],
  entryComponents: [BiddingQuotationInfoDeleteDialogComponent],
})
export class JhipsterSampleApplicationBiddingQuotationInfoModule {}
