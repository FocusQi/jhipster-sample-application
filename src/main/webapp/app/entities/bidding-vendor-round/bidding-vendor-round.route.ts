import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IBiddingVendorRound, BiddingVendorRound } from 'app/shared/model/bidding-vendor-round.model';
import { BiddingVendorRoundService } from './bidding-vendor-round.service';
import { BiddingVendorRoundComponent } from './bidding-vendor-round.component';
import { BiddingVendorRoundDetailComponent } from './bidding-vendor-round-detail.component';
import { BiddingVendorRoundUpdateComponent } from './bidding-vendor-round-update.component';

@Injectable({ providedIn: 'root' })
export class BiddingVendorRoundResolve implements Resolve<IBiddingVendorRound> {
  constructor(private service: BiddingVendorRoundService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IBiddingVendorRound> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((biddingVendorRound: HttpResponse<BiddingVendorRound>) => {
          if (biddingVendorRound.body) {
            return of(biddingVendorRound.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new BiddingVendorRound());
  }
}

export const biddingVendorRoundRoute: Routes = [
  {
    path: '',
    component: BiddingVendorRoundComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationApp.biddingVendorRound.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: BiddingVendorRoundDetailComponent,
    resolve: {
      biddingVendorRound: BiddingVendorRoundResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationApp.biddingVendorRound.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: BiddingVendorRoundUpdateComponent,
    resolve: {
      biddingVendorRound: BiddingVendorRoundResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationApp.biddingVendorRound.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: BiddingVendorRoundUpdateComponent,
    resolve: {
      biddingVendorRound: BiddingVendorRoundResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationApp.biddingVendorRound.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
