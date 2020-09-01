import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IQuotationInfo, QuotationInfo } from 'app/shared/model/quotation-info.model';
import { QuotationInfoService } from './quotation-info.service';
import { QuotationInfoComponent } from './quotation-info.component';
import { QuotationInfoDetailComponent } from './quotation-info-detail.component';
import { QuotationInfoUpdateComponent } from './quotation-info-update.component';

@Injectable({ providedIn: 'root' })
export class QuotationInfoResolve implements Resolve<IQuotationInfo> {
  constructor(private service: QuotationInfoService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IQuotationInfo> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((quotationInfo: HttpResponse<QuotationInfo>) => {
          if (quotationInfo.body) {
            return of(quotationInfo.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new QuotationInfo());
  }
}

export const quotationInfoRoute: Routes = [
  {
    path: '',
    component: QuotationInfoComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationApp.quotationInfo.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: QuotationInfoDetailComponent,
    resolve: {
      quotationInfo: QuotationInfoResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationApp.quotationInfo.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: QuotationInfoUpdateComponent,
    resolve: {
      quotationInfo: QuotationInfoResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationApp.quotationInfo.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: QuotationInfoUpdateComponent,
    resolve: {
      quotationInfo: QuotationInfoResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationApp.quotationInfo.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
