import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IMaterialRound, MaterialRound } from 'app/shared/model/material-round.model';
import { MaterialRoundService } from './material-round.service';
import { MaterialRoundComponent } from './material-round.component';
import { MaterialRoundDetailComponent } from './material-round-detail.component';
import { MaterialRoundUpdateComponent } from './material-round-update.component';

@Injectable({ providedIn: 'root' })
export class MaterialRoundResolve implements Resolve<IMaterialRound> {
  constructor(private service: MaterialRoundService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IMaterialRound> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((materialRound: HttpResponse<MaterialRound>) => {
          if (materialRound.body) {
            return of(materialRound.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new MaterialRound());
  }
}

export const materialRoundRoute: Routes = [
  {
    path: '',
    component: MaterialRoundComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationApp.materialRound.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: MaterialRoundDetailComponent,
    resolve: {
      materialRound: MaterialRoundResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationApp.materialRound.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: MaterialRoundUpdateComponent,
    resolve: {
      materialRound: MaterialRoundResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationApp.materialRound.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: MaterialRoundUpdateComponent,
    resolve: {
      materialRound: MaterialRoundResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationApp.materialRound.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
