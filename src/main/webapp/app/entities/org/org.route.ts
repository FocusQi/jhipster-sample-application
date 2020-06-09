import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IOrg, Org } from 'app/shared/model/org.model';
import { OrgService } from './org.service';
import { OrgComponent } from './org.component';
import { OrgDetailComponent } from './org-detail.component';
import { OrgUpdateComponent } from './org-update.component';

@Injectable({ providedIn: 'root' })
export class OrgResolve implements Resolve<IOrg> {
  constructor(private service: OrgService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IOrg> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((org: HttpResponse<Org>) => {
          if (org.body) {
            return of(org.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Org());
  }
}

export const orgRoute: Routes = [
  {
    path: '',
    component: OrgComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationApp.org.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: OrgDetailComponent,
    resolve: {
      org: OrgResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationApp.org.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: OrgUpdateComponent,
    resolve: {
      org: OrgResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationApp.org.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: OrgUpdateComponent,
    resolve: {
      org: OrgResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationApp.org.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
