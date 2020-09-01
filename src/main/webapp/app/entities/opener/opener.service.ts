import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IOpener } from 'app/shared/model/opener.model';

type EntityResponseType = HttpResponse<IOpener>;
type EntityArrayResponseType = HttpResponse<IOpener[]>;

@Injectable({ providedIn: 'root' })
export class OpenerService {
  public resourceUrl = SERVER_API_URL + 'api/openers';

  constructor(protected http: HttpClient) {}

  create(opener: IOpener): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(opener);
    return this.http
      .post<IOpener>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(opener: IOpener): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(opener);
    return this.http
      .put<IOpener>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IOpener>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IOpener[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(opener: IOpener): IOpener {
    const copy: IOpener = Object.assign({}, opener, {
      createdDate: opener.createdDate && opener.createdDate.isValid() ? opener.createdDate.toJSON() : undefined,
      lastModifiedDate: opener.lastModifiedDate && opener.lastModifiedDate.isValid() ? opener.lastModifiedDate.toJSON() : undefined,
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
      res.body.forEach((opener: IOpener) => {
        opener.createdDate = opener.createdDate ? moment(opener.createdDate) : undefined;
        opener.lastModifiedDate = opener.lastModifiedDate ? moment(opener.lastModifiedDate) : undefined;
      });
    }
    return res;
  }
}
