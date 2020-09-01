import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IPoHeader, PoHeader } from 'app/shared/model/po-header.model';
import { PoHeaderService } from './po-header.service';
import { PoHeaderComponent } from './po-header.component';
import { PoHeaderDetailComponent } from './po-header-detail.component';
import { PoHeaderUpdateComponent } from './po-header-update.component';

@Injectable({ providedIn: 'root' })
export class PoHeaderResolve implements Resolve<IPoHeader> {
  constructor(private service: PoHeaderService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IPoHeader> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((poHeader: HttpResponse<PoHeader>) => {
          if (poHeader.body) {
            return of(poHeader.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new PoHeader());
  }
}

export const poHeaderRoute: Routes = [
  {
    path: '',
    component: PoHeaderComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationApp.poHeader.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: PoHeaderDetailComponent,
    resolve: {
      poHeader: PoHeaderResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationApp.poHeader.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: PoHeaderUpdateComponent,
    resolve: {
      poHeader: PoHeaderResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationApp.poHeader.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: PoHeaderUpdateComponent,
    resolve: {
      poHeader: PoHeaderResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationApp.poHeader.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
