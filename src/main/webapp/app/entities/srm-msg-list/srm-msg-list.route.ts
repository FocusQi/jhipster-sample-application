import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ISrmMsgList, SrmMsgList } from 'app/shared/model/srm-msg-list.model';
import { SrmMsgListService } from './srm-msg-list.service';
import { SrmMsgListComponent } from './srm-msg-list.component';
import { SrmMsgListDetailComponent } from './srm-msg-list-detail.component';
import { SrmMsgListUpdateComponent } from './srm-msg-list-update.component';

@Injectable({ providedIn: 'root' })
export class SrmMsgListResolve implements Resolve<ISrmMsgList> {
  constructor(private service: SrmMsgListService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ISrmMsgList> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((srmMsgList: HttpResponse<SrmMsgList>) => {
          if (srmMsgList.body) {
            return of(srmMsgList.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new SrmMsgList());
  }
}

export const srmMsgListRoute: Routes = [
  {
    path: '',
    component: SrmMsgListComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationApp.srmMsgList.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: SrmMsgListDetailComponent,
    resolve: {
      srmMsgList: SrmMsgListResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationApp.srmMsgList.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: SrmMsgListUpdateComponent,
    resolve: {
      srmMsgList: SrmMsgListResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationApp.srmMsgList.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: SrmMsgListUpdateComponent,
    resolve: {
      srmMsgList: SrmMsgListResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationApp.srmMsgList.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
