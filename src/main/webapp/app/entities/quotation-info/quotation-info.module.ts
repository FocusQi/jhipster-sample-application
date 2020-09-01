import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhipsterSampleApplicationSharedModule } from 'app/shared/shared.module';
import { QuotationInfoComponent } from './quotation-info.component';
import { QuotationInfoDetailComponent } from './quotation-info-detail.component';
import { QuotationInfoUpdateComponent } from './quotation-info-update.component';
import { QuotationInfoDeleteDialogComponent } from './quotation-info-delete-dialog.component';
import { quotationInfoRoute } from './quotation-info.route';

@NgModule({
  imports: [JhipsterSampleApplicationSharedModule, RouterModule.forChild(quotationInfoRoute)],
  declarations: [QuotationInfoComponent, QuotationInfoDetailComponent, QuotationInfoUpdateComponent, QuotationInfoDeleteDialogComponent],
  entryComponents: [QuotationInfoDeleteDialogComponent],
})
export class JhipsterSampleApplicationQuotationInfoModule {}
