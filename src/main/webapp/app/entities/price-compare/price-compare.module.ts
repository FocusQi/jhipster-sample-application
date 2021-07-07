import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhipsterSampleApplicationSharedModule } from 'app/shared/shared.module';
import { PriceCompareComponent } from './price-compare.component';
import { PriceCompareDetailComponent } from './price-compare-detail.component';
import { PriceCompareUpdateComponent } from './price-compare-update.component';
import { PriceCompareDeleteDialogComponent } from './price-compare-delete-dialog.component';
import { priceCompareRoute } from './price-compare.route';

@NgModule({
  imports: [JhipsterSampleApplicationSharedModule, RouterModule.forChild(priceCompareRoute)],
  declarations: [PriceCompareComponent, PriceCompareDetailComponent, PriceCompareUpdateComponent, PriceCompareDeleteDialogComponent],
  entryComponents: [PriceCompareDeleteDialogComponent],
})
export class JhipsterSampleApplicationPriceCompareModule {}
