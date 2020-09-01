import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IBomTemplateHeader, BomTemplateHeader } from 'app/shared/model/bom-template-header.model';
import { BomTemplateHeaderService } from './bom-template-header.service';
import { BomTemplateHeaderComponent } from './bom-template-header.component';
import { BomTemplateHeaderDetailComponent } from './bom-template-header-detail.component';
import { BomTemplateHeaderUpdateComponent } from './bom-template-header-update.component';

@Injectable({ providedIn: 'root' })
export class BomTemplateHeaderResolve implements Resolve<IBomTemplateHeader> {
  constructor(private service: BomTemplateHeaderService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IBomTemplateHeader> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((bomTemplateHeader: HttpResponse<BomTemplateHeader>) => {
          if (bomTemplateHeader.body) {
            return of(bomTemplateHeader.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new BomTemplateHeader());
  }
}

export const bomTemplateHeaderRoute: Routes = [
  {
    path: '',
    component: BomTemplateHeaderComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationApp.bomTemplateHeader.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: BomTemplateHeaderDetailComponent,
    resolve: {
      bomTemplateHeader: BomTemplateHeaderResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationApp.bomTemplateHeader.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: BomTemplateHeaderUpdateComponent,
    resolve: {
      bomTemplateHeader: BomTemplateHeaderResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationApp.bomTemplateHeader.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: BomTemplateHeaderUpdateComponent,
    resolve: {
      bomTemplateHeader: BomTemplateHeaderResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationApp.bomTemplateHeader.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
