import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IBiddingMaterialRound, BiddingMaterialRound } from 'app/shared/model/bidding-material-round.model';
import { BiddingMaterialRoundService } from './bidding-material-round.service';
import { BiddingMaterialRoundComponent } from './bidding-material-round.component';
import { BiddingMaterialRoundDetailComponent } from './bidding-material-round-detail.component';
import { BiddingMaterialRoundUpdateComponent } from './bidding-material-round-update.component';

@Injectable({ providedIn: 'root' })
export class BiddingMaterialRoundResolve implements Resolve<IBiddingMaterialRound> {
  constructor(private service: BiddingMaterialRoundService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IBiddingMaterialRound> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((biddingMaterialRound: HttpResponse<BiddingMaterialRound>) => {
          if (biddingMaterialRound.body) {
            return of(biddingMaterialRound.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new BiddingMaterialRound());
  }
}

export const biddingMaterialRoundRoute: Routes = [
  {
    path: '',
    component: BiddingMaterialRoundComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationApp.biddingMaterialRound.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: BiddingMaterialRoundDetailComponent,
    resolve: {
      biddingMaterialRound: BiddingMaterialRoundResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationApp.biddingMaterialRound.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: BiddingMaterialRoundUpdateComponent,
    resolve: {
      biddingMaterialRound: BiddingMaterialRoundResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationApp.biddingMaterialRound.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: BiddingMaterialRoundUpdateComponent,
    resolve: {
      biddingMaterialRound: BiddingMaterialRoundResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationApp.biddingMaterialRound.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
