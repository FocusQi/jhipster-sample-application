import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IBomTemplateInfoColumn, BomTemplateInfoColumn } from 'app/shared/model/bom-template-info-column.model';
import { BomTemplateInfoColumnService } from './bom-template-info-column.service';
import { BomTemplateInfoColumnComponent } from './bom-template-info-column.component';
import { BomTemplateInfoColumnDetailComponent } from './bom-template-info-column-detail.component';
import { BomTemplateInfoColumnUpdateComponent } from './bom-template-info-column-update.component';

@Injectable({ providedIn: 'root' })
export class BomTemplateInfoColumnResolve implements Resolve<IBomTemplateInfoColumn> {
  constructor(private service: BomTemplateInfoColumnService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IBomTemplateInfoColumn> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((bomTemplateInfoColumn: HttpResponse<BomTemplateInfoColumn>) => {
          if (bomTemplateInfoColumn.body) {
            return of(bomTemplateInfoColumn.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new BomTemplateInfoColumn());
  }
}

export const bomTemplateInfoColumnRoute: Routes = [
  {
    path: '',
    component: BomTemplateInfoColumnComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationApp.bomTemplateInfoColumn.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: BomTemplateInfoColumnDetailComponent,
    resolve: {
      bomTemplateInfoColumn: BomTemplateInfoColumnResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationApp.bomTemplateInfoColumn.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: BomTemplateInfoColumnUpdateComponent,
    resolve: {
      bomTemplateInfoColumn: BomTemplateInfoColumnResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationApp.bomTemplateInfoColumn.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: BomTemplateInfoColumnUpdateComponent,
    resolve: {
      bomTemplateInfoColumn: BomTemplateInfoColumnResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationApp.bomTemplateInfoColumn.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
