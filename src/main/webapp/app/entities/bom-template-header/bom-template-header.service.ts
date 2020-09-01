import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IBomTemplateHeader } from 'app/shared/model/bom-template-header.model';

type EntityResponseType = HttpResponse<IBomTemplateHeader>;
type EntityArrayResponseType = HttpResponse<IBomTemplateHeader[]>;

@Injectable({ providedIn: 'root' })
export class BomTemplateHeaderService {
  public resourceUrl = SERVER_API_URL + 'api/bom-template-headers';

  constructor(protected http: HttpClient) {}

  create(bomTemplateHeader: IBomTemplateHeader): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(bomTemplateHeader);
    return this.http
      .post<IBomTemplateHeader>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(bomTemplateHeader: IBomTemplateHeader): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(bomTemplateHeader);
    return this.http
      .put<IBomTemplateHeader>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IBomTemplateHeader>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IBomTemplateHeader[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(bomTemplateHeader: IBomTemplateHeader): IBomTemplateHeader {
    const copy: IBomTemplateHeader = Object.assign({}, bomTemplateHeader, {
      createdDate:
        bomTemplateHeader.createdDate && bomTemplateHeader.createdDate.isValid() ? bomTemplateHeader.createdDate.toJSON() : undefined,
      lastModifiedDate:
        bomTemplateHeader.lastModifiedDate && bomTemplateHeader.lastModifiedDate.isValid()
          ? bomTemplateHeader.lastModifiedDate.toJSON()
          : undefined,
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
      res.body.forEach((bomTemplateHeader: IBomTemplateHeader) => {
        bomTemplateHeader.createdDate = bomTemplateHeader.createdDate ? moment(bomTemplateHeader.createdDate) : undefined;
        bomTemplateHeader.lastModifiedDate = bomTemplateHeader.lastModifiedDate ? moment(bomTemplateHeader.lastModifiedDate) : undefined;
      });
    }
    return res;
  }
}
