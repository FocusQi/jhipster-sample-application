import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IBiddingQuotationInfo, BiddingQuotationInfo } from 'app/shared/model/bidding-quotation-info.model';
import { BiddingQuotationInfoService } from './bidding-quotation-info.service';
import { BiddingQuotationInfoComponent } from './bidding-quotation-info.component';
import { BiddingQuotationInfoDetailComponent } from './bidding-quotation-info-detail.component';
import { BiddingQuotationInfoUpdateComponent } from './bidding-quotation-info-update.component';

@Injectable({ providedIn: 'root' })
export class BiddingQuotationInfoResolve implements Resolve<IBiddingQuotationInfo> {
  constructor(private service: BiddingQuotationInfoService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IBiddingQuotationInfo> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((biddingQuotationInfo: HttpResponse<BiddingQuotationInfo>) => {
          if (biddingQuotationInfo.body) {
            return of(biddingQuotationInfo.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new BiddingQuotationInfo());
  }
}

export const biddingQuotationInfoRoute: Routes = [
  {
    path: '',
    component: BiddingQuotationInfoComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationApp.biddingQuotationInfo.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: BiddingQuotationInfoDetailComponent,
    resolve: {
      biddingQuotationInfo: BiddingQuotationInfoResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationApp.biddingQuotationInfo.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: BiddingQuotationInfoUpdateComponent,
    resolve: {
      biddingQuotationInfo: BiddingQuotationInfoResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationApp.biddingQuotationInfo.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: BiddingQuotationInfoUpdateComponent,
    resolve: {
      biddingQuotationInfo: BiddingQuotationInfoResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationApp.biddingQuotationInfo.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
