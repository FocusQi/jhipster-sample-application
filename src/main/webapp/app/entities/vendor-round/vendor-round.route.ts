import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IVendorRound, VendorRound } from 'app/shared/model/vendor-round.model';
import { VendorRoundService } from './vendor-round.service';
import { VendorRoundComponent } from './vendor-round.component';
import { VendorRoundDetailComponent } from './vendor-round-detail.component';
import { VendorRoundUpdateComponent } from './vendor-round-update.component';

@Injectable({ providedIn: 'root' })
export class VendorRoundResolve implements Resolve<IVendorRound> {
  constructor(private service: VendorRoundService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IVendorRound> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((vendorRound: HttpResponse<VendorRound>) => {
          if (vendorRound.body) {
            return of(vendorRound.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new VendorRound());
  }
}

export const vendorRoundRoute: Routes = [
  {
    path: '',
    component: VendorRoundComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationApp.vendorRound.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: VendorRoundDetailComponent,
    resolve: {
      vendorRound: VendorRoundResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationApp.vendorRound.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: VendorRoundUpdateComponent,
    resolve: {
      vendorRound: VendorRoundResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationApp.vendorRound.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: VendorRoundUpdateComponent,
    resolve: {
      vendorRound: VendorRoundResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationApp.vendorRound.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
