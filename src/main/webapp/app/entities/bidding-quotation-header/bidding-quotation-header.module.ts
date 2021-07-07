import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhipsterSampleApplicationSharedModule } from 'app/shared/shared.module';
import { BiddingQuotationHeaderComponent } from './bidding-quotation-header.component';
import { BiddingQuotationHeaderDetailComponent } from './bidding-quotation-header-detail.component';
import { BiddingQuotationHeaderUpdateComponent } from './bidding-quotation-header-update.component';
import { BiddingQuotationHeaderDeleteDialogComponent } from './bidding-quotation-header-delete-dialog.component';
import { biddingQuotationHeaderRoute } from './bidding-quotation-header.route';

@NgModule({
  imports: [JhipsterSampleApplicationSharedModule, RouterModule.forChild(biddingQuotationHeaderRoute)],
  declarations: [
    BiddingQuotationHeaderComponent,
    BiddingQuotationHeaderDetailComponent,
    BiddingQuotationHeaderUpdateComponent,
    BiddingQuotationHeaderDeleteDialogComponent,
  ],
  entryComponents: [BiddingQuotationHeaderDeleteDialogComponent],
})
export class JhipsterSampleApplicationBiddingQuotationHeaderModule {}
