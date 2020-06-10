import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IReturnHeader, ReturnHeader } from 'app/shared/model/return-header.model';
import { ReturnHeaderService } from './return-header.service';
import { ReturnHeaderComponent } from './return-header.component';
import { ReturnHeaderDetailComponent } from './return-header-detail.component';
import { ReturnHeaderUpdateComponent } from './return-header-update.component';

@Injectable({ providedIn: 'root' })
export class ReturnHeaderResolve implements Resolve<IReturnHeader> {
  constructor(private service: ReturnHeaderService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IReturnHeader> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((returnHeader: HttpResponse<ReturnHeader>) => {
          if (returnHeader.body) {
            return of(returnHeader.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new ReturnHeader());
  }
}

export const returnHeaderRoute: Routes = [
  {
    path: '',
    component: ReturnHeaderComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationApp.returnHeader.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: ReturnHeaderDetailComponent,
    resolve: {
      returnHeader: ReturnHeaderResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationApp.returnHeader.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: ReturnHeaderUpdateComponent,
    resolve: {
      returnHeader: ReturnHeaderResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationApp.returnHeader.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: ReturnHeaderUpdateComponent,
    resolve: {
      returnHeader: ReturnHeaderResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationApp.returnHeader.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
