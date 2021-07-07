import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IBiddingPriceCompare, BiddingPriceCompare } from 'app/shared/model/bidding-price-compare.model';
import { BiddingPriceCompareService } from './bidding-price-compare.service';
import { BiddingPriceCompareComponent } from './bidding-price-compare.component';
import { BiddingPriceCompareDetailComponent } from './bidding-price-compare-detail.component';
import { BiddingPriceCompareUpdateComponent } from './bidding-price-compare-update.component';

@Injectable({ providedIn: 'root' })
export class BiddingPriceCompareResolve implements Resolve<IBiddingPriceCompare> {
  constructor(private service: BiddingPriceCompareService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IBiddingPriceCompare> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((biddingPriceCompare: HttpResponse<BiddingPriceCompare>) => {
          if (biddingPriceCompare.body) {
            return of(biddingPriceCompare.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new BiddingPriceCompare());
  }
}

export const biddingPriceCompareRoute: Routes = [
  {
    path: '',
    component: BiddingPriceCompareComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationApp.biddingPriceCompare.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: BiddingPriceCompareDetailComponent,
    resolve: {
      biddingPriceCompare: BiddingPriceCompareResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationApp.biddingPriceCompare.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: BiddingPriceCompareUpdateComponent,
    resolve: {
      biddingPriceCompare: BiddingPriceCompareResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationApp.biddingPriceCompare.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: BiddingPriceCompareUpdateComponent,
    resolve: {
      biddingPriceCompare: BiddingPriceCompareResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationApp.biddingPriceCompare.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
