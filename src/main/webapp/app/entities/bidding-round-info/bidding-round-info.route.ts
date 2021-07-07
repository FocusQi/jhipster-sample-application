import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IBiddingRoundInfo, BiddingRoundInfo } from 'app/shared/model/bidding-round-info.model';
import { BiddingRoundInfoService } from './bidding-round-info.service';
import { BiddingRoundInfoComponent } from './bidding-round-info.component';
import { BiddingRoundInfoDetailComponent } from './bidding-round-info-detail.component';
import { BiddingRoundInfoUpdateComponent } from './bidding-round-info-update.component';

@Injectable({ providedIn: 'root' })
export class BiddingRoundInfoResolve implements Resolve<IBiddingRoundInfo> {
  constructor(private service: BiddingRoundInfoService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IBiddingRoundInfo> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((biddingRoundInfo: HttpResponse<BiddingRoundInfo>) => {
          if (biddingRoundInfo.body) {
            return of(biddingRoundInfo.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new BiddingRoundInfo());
  }
}

export const biddingRoundInfoRoute: Routes = [
  {
    path: '',
    component: BiddingRoundInfoComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationApp.biddingRoundInfo.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: BiddingRoundInfoDetailComponent,
    resolve: {
      biddingRoundInfo: BiddingRoundInfoResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationApp.biddingRoundInfo.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: BiddingRoundInfoUpdateComponent,
    resolve: {
      biddingRoundInfo: BiddingRoundInfoResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationApp.biddingRoundInfo.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: BiddingRoundInfoUpdateComponent,
    resolve: {
      biddingRoundInfo: BiddingRoundInfoResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationApp.biddingRoundInfo.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
