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
        path: 'opener',
        loadChildren: () => import('./opener/opener.module').then(m => m.JhipsterSampleApplicationOpenerModule),
      },
      {
        path: 'bidding-round-info',
        loadChildren: () =>
          import('./bidding-round-info/bidding-round-info.module').then(m => m.JhipsterSampleApplicationBiddingRoundInfoModule),
      },
      {
        path: 'vendor-round',
        loadChildren: () => import('./vendor-round/vendor-round.module').then(m => m.JhipsterSampleApplicationVendorRoundModule),
      },
      {
        path: 'material-round',
        loadChildren: () => import('./material-round/material-round.module').then(m => m.JhipsterSampleApplicationMaterialRoundModule),
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
        path: 'quotation-info',
        loadChildren: () => import('./quotation-info/quotation-info.module').then(m => m.JhipsterSampleApplicationQuotationInfoModule),
      },
      {
        path: 'price-compare',
        loadChildren: () => import('./price-compare/price-compare.module').then(m => m.JhipsterSampleApplicationPriceCompareModule),
      },
      {
        path: 'vendor',
        loadChildren: () => import('./vendor/vendor.module').then(m => m.JhipsterSampleApplicationVendorModule),
      },
      {
        path: 'material',
        loadChildren: () => import('./material/material.module').then(m => m.JhipsterSampleApplicationMaterialModule),
      },
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ]),
  ],
})
export class JhipsterSampleApplicationEntityModule {}
