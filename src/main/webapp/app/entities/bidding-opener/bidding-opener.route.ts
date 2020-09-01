import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IBiddingOpener, BiddingOpener } from 'app/shared/model/bidding-opener.model';
import { BiddingOpenerService } from './bidding-opener.service';
import { BiddingOpenerComponent } from './bidding-opener.component';
import { BiddingOpenerDetailComponent } from './bidding-opener-detail.component';
import { BiddingOpenerUpdateComponent } from './bidding-opener-update.component';

@Injectable({ providedIn: 'root' })
export class BiddingOpenerResolve implements Resolve<IBiddingOpener> {
  constructor(private service: BiddingOpenerService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IBiddingOpener> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((biddingOpener: HttpResponse<BiddingOpener>) => {
          if (biddingOpener.body) {
            return of(biddingOpener.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new BiddingOpener());
  }
}

export const biddingOpenerRoute: Routes = [
  {
    path: '',
    component: BiddingOpenerComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationApp.biddingOpener.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: BiddingOpenerDetailComponent,
    resolve: {
      biddingOpener: BiddingOpenerResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationApp.biddingOpener.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: BiddingOpenerUpdateComponent,
    resolve: {
      biddingOpener: BiddingOpenerResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationApp.biddingOpener.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: BiddingOpenerUpdateComponent,
    resolve: {
      biddingOpener: BiddingOpenerResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationApp.biddingOpener.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
