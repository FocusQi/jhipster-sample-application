import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IMaterialRound } from 'app/shared/model/material-round.model';

type EntityResponseType = HttpResponse<IMaterialRound>;
type EntityArrayResponseType = HttpResponse<IMaterialRound[]>;

@Injectable({ providedIn: 'root' })
export class MaterialRoundService {
  public resourceUrl = SERVER_API_URL + 'api/material-rounds';

  constructor(protected http: HttpClient) {}

  create(materialRound: IMaterialRound): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(materialRound);
    return this.http
      .post<IMaterialRound>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(materialRound: IMaterialRound): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(materialRound);
    return this.http
      .put<IMaterialRound>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IMaterialRound>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IMaterialRound[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(materialRound: IMaterialRound): IMaterialRound {
    const copy: IMaterialRound = Object.assign({}, materialRound, {
      createdDate: materialRound.createdDate && materialRound.createdDate.isValid() ? materialRound.createdDate.toJSON() : undefined,
      lastModifiedDate:
        materialRound.lastModifiedDate && materialRound.lastModifiedDate.isValid() ? materialRound.lastModifiedDate.toJSON() : undefined,
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.createdDate = res.body.createdDate ? moment(res.body.createdDate) : undefined;
      res.body.lastModifiedDate = res.body.lastModifiedDate ? moment(res.body.lastModifiedDate) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((materialRound: IMaterialRound) => {
        materialRound.createdDate = materialRound.createdDate ? moment(materialRound.createdDate) : undefined;
        materialRound.lastModifiedDate = materialRound.lastModifiedDate ? moment(materialRound.lastModifiedDate) : undefined;
      });
    }
    return res;
  }
}
