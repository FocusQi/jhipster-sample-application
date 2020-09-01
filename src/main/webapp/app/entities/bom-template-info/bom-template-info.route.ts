import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IBomTemplateInfo, BomTemplateInfo } from 'app/shared/model/bom-template-info.model';
import { BomTemplateInfoService } from './bom-template-info.service';
import { BomTemplateInfoComponent } from './bom-template-info.component';
import { BomTemplateInfoDetailComponent } from './bom-template-info-detail.component';
import { BomTemplateInfoUpdateComponent } from './bom-template-info-update.component';

@Injectable({ providedIn: 'root' })
export class BomTemplateInfoResolve implements Resolve<IBomTemplateInfo> {
  constructor(private service: BomTemplateInfoService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IBomTemplateInfo> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((bomTemplateInfo: HttpResponse<BomTemplateInfo>) => {
          if (bomTemplateInfo.body) {
            return of(bomTemplateInfo.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new BomTemplateInfo());
  }
}

export const bomTemplateInfoRoute: Routes = [
  {
    path: '',
    component: BomTemplateInfoComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationApp.bomTemplateInfo.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: BomTemplateInfoDetailComponent,
    resolve: {
      bomTemplateInfo: BomTemplateInfoResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationApp.bomTemplateInfo.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: BomTemplateInfoUpdateComponent,
    resolve: {
      bomTemplateInfo: BomTemplateInfoResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationApp.bomTemplateInfo.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: BomTemplateInfoUpdateComponent,
    resolve: {
      bomTemplateInfo: BomTemplateInfoResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationApp.bomTemplateInfo.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
