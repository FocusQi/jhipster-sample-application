import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'bidding-quotation-header',
        loadChildren: () =>
          import('./bidding-quotation-header/bidding-quotation-header.module').then(
            m => m.JhipsterSampleApplicationBiddingQuotationHeaderModule
          ),
      },
      {
        path: 'bidding-opener',
        loadChildren: () => import('./bidding-opener/bidding-opener.module').then(m => m.JhipsterSampleApplicationBiddingOpenerModule),
      },
      {
        path: 'bidding-round-info',
        loadChildren: () =>
          import('./bidding-round-info/bidding-round-info.module').then(m => m.JhipsterSampleApplicationBiddingRoundInfoModule),
      },
      {
        path: 'bidding-vendor-round',
        loadChildren: () =>
          import('./bidding-vendor-round/bidding-vendor-round.module').then(m => m.JhipsterSampleApplicationBiddingVendorRoundModule),
      },
      {
        path: 'bidding-material-round',
        loadChildren: () =>
          import('./bidding-material-round/bidding-material-round.module').then(m => m.JhipsterSampleApplicationBiddingMaterialRoundModule),
      },
      {
        path: 'bom-template-header',
        loadChildren: () =>
          import('./bom-template-header/bom-template-header.module').then(m => m.JhipsterSampleApplicationBomTemplateHeaderModule),
      },
      {
        path: 'bom-template-info',
        loadChildren: () =>
          import('./bom-template-info/bom-template-info.module').then(m => m.JhipsterSampleApplicationBomTemplateInfoModule),
      },
      {
        path: 'bom-template-info-column',
        loadChildren: () =>
          import('./bom-template-info-column/bom-template-info-column.module').then(
            m => m.JhipsterSampleApplicationBomTemplateInfoColumnModule
          ),
      },
      {
        path: 'bidding-quotation-info',
        loadChildren: () =>
          import('./bidding-quotation-info/bidding-quotation-info.module').then(m => m.JhipsterSampleApplicationBiddingQuotationInfoModule),
      },
      {
        path: 'bidding-price-compare',
        loadChildren: () =>
          import('./bidding-price-compare/bidding-price-compare.module').then(m => m.JhipsterSampleApplicationBiddingPriceCompareModule),
      },
      {
        path: 'vendor',
        loadChildren: () => import('./vendor/vendor.module').then(m => m.JhipsterSampleApplicationVendorModule),
      },
      {
        path: 'material',
        loadChildren: () => import('./material/material.module').then(m => m.JhipsterSampleApplicationMaterialModule),
      },
      {
        path: 'po-header',
        loadChildren: () => import('./po-header/po-header.module').then(m => m.JhipsterSampleApplicationPoHeaderModule),
      },
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ]),
  ],
})
export class JhipsterSampleApplicationEntityModule {}
