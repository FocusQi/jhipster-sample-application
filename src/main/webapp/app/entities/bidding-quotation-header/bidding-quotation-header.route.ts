import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IBiddingQuotationHeader, BiddingQuotationHeader } from 'app/shared/model/bidding-quotation-header.model';
import { BiddingQuotationHeaderService } from './bidding-quotation-header.service';
import { BiddingQuotationHeaderComponent } from './bidding-quotation-header.component';
import { BiddingQuotationHeaderDetailComponent } from './bidding-quotation-header-detail.component';
import { BiddingQuotationHeaderUpdateComponent } from './bidding-quotation-header-update.component';

@Injectable({ providedIn: 'root' })
export class BiddingQuotationHeaderResolve implements Resolve<IBiddingQuotationHeader> {
  constructor(private service: BiddingQuotationHeaderService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IBiddingQuotationHeader> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((biddingQuotationHeader: HttpResponse<BiddingQuotationHeader>) => {
          if (biddingQuotationHeader.body) {
            return of(biddingQuotationHeader.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new BiddingQuotationHeader());
  }
}

export const biddingQuotationHeaderRoute: Routes = [
  {
    path: '',
    component: BiddingQuotationHeaderComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationApp.biddingQuotationHeader.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: BiddingQuotationHeaderDetailComponent,
    resolve: {
      biddingQuotationHeader: BiddingQuotationHeaderResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationApp.biddingQuotationHeader.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: BiddingQuotationHeaderUpdateComponent,
    resolve: {
      biddingQuotationHeader: BiddingQuotationHeaderResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationApp.biddingQuotationHeader.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: BiddingQuotationHeaderUpdateComponent,
    resolve: {
      biddingQuotationHeader: BiddingQuotationHeaderResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationApp.biddingQuotationHeader.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
