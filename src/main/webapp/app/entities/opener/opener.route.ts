import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IOpener, Opener } from 'app/shared/model/opener.model';
import { OpenerService } from './opener.service';
import { OpenerComponent } from './opener.component';
import { OpenerDetailComponent } from './opener-detail.component';
import { OpenerUpdateComponent } from './opener-update.component';

@Injectable({ providedIn: 'root' })
export class OpenerResolve implements Resolve<IOpener> {
  constructor(private service: OpenerService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IOpener> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((opener: HttpResponse<Opener>) => {
          if (opener.body) {
            return of(opener.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Opener());
  }
}

export const openerRoute: Routes = [
  {
    path: '',
    component: OpenerComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationApp.opener.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: OpenerDetailComponent,
    resolve: {
      opener: OpenerResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationApp.opener.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: OpenerUpdateComponent,
    resolve: {
      opener: OpenerResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationApp.opener.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: OpenerUpdateComponent,
    resolve: {
      opener: OpenerResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationApp.opener.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
