import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IPriceCompare, PriceCompare } from 'app/shared/model/price-compare.model';
import { PriceCompareService } from './price-compare.service';
import { PriceCompareComponent } from './price-compare.component';
import { PriceCompareDetailComponent } from './price-compare-detail.component';
import { PriceCompareUpdateComponent } from './price-compare-update.component';

@Injectable({ providedIn: 'root' })
export class PriceCompareResolve implements Resolve<IPriceCompare> {
  constructor(private service: PriceCompareService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IPriceCompare> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((priceCompare: HttpResponse<PriceCompare>) => {
          if (priceCompare.body) {
            return of(priceCompare.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new PriceCompare());
  }
}

export const priceCompareRoute: Routes = [
  {
    path: '',
    component: PriceCompareComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationApp.priceCompare.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: PriceCompareDetailComponent,
    resolve: {
      priceCompare: PriceCompareResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationApp.priceCompare.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: PriceCompareUpdateComponent,
    resolve: {
      priceCompare: PriceCompareResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationApp.priceCompare.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: PriceCompareUpdateComponent,
    resolve: {
      priceCompare: PriceCompareResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationApp.priceCompare.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
