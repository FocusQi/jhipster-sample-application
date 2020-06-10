import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IReturnHeader } from 'app/shared/model/return-header.model';

type EntityResponseType = HttpResponse<IReturnHeader>;
type EntityArrayResponseType = HttpResponse<IReturnHeader[]>;

@Injectable({ providedIn: 'root' })
export class ReturnHeaderService {
  public resourceUrl = SERVER_API_URL + 'api/return-headers';

  constructor(protected http: HttpClient) {}

  create(returnHeader: IReturnHeader): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(returnHeader);
    return this.http
      .post<IReturnHeader>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(returnHeader: IReturnHeader): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(returnHeader);
    return this.http
      .put<IReturnHeader>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IReturnHeader>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IReturnHeader[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(returnHeader: IReturnHeader): IReturnHeader {
    const copy: IReturnHeader = Object.assign({}, returnHeader, {
      createdDate: returnHeader.createdDate && returnHeader.createdDate.isValid() ? returnHeader.createdDate.toJSON() : undefined,
      lastModifiedDate:
        returnHeader.lastModifiedDate && returnHeader.lastModifiedDate.isValid() ? returnHeader.lastModifiedDate.toJSON() : undefined,
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
      res.body.forEach((returnHeader: IReturnHeader) => {
        returnHeader.createdDate = returnHeader.createdDate ? moment(returnHeader.createdDate) : undefined;
        returnHeader.lastModifiedDate = returnHeader.lastModifiedDate ? moment(returnHeader.lastModifiedDate) : undefined;
      });
    }
    return res;
  }
}
