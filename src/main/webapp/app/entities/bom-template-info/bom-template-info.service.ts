import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IBomTemplateInfo } from 'app/shared/model/bom-template-info.model';

type EntityResponseType = HttpResponse<IBomTemplateInfo>;
type EntityArrayResponseType = HttpResponse<IBomTemplateInfo[]>;

@Injectable({ providedIn: 'root' })
export class BomTemplateInfoService {
  public resourceUrl = SERVER_API_URL + 'api/bom-template-infos';

  constructor(protected http: HttpClient) {}

  create(bomTemplateInfo: IBomTemplateInfo): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(bomTemplateInfo);
    return this.http
      .post<IBomTemplateInfo>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(bomTemplateInfo: IBomTemplateInfo): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(bomTemplateInfo);
    return this.http
      .put<IBomTemplateInfo>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IBomTemplateInfo>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IBomTemplateInfo[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(bomTemplateInfo: IBomTemplateInfo): IBomTemplateInfo {
    const copy: IBomTemplateInfo = Object.assign({}, bomTemplateInfo, {
      createdDate: bomTemplateInfo.createdDate && bomTemplateInfo.createdDate.isValid() ? bomTemplateInfo.createdDate.toJSON() : undefined,
      lastModifiedDate:
        bomTemplateInfo.lastModifiedDate && bomTemplateInfo.lastModifiedDate.isValid()
          ? bomTemplateInfo.lastModifiedDate.toJSON()
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
      res.body.forEach((bomTemplateInfo: IBomTemplateInfo) => {
        bomTemplateInfo.createdDate = bomTemplateInfo.createdDate ? moment(bomTemplateInfo.createdDate) : undefined;
        bomTemplateInfo.lastModifiedDate = bomTemplateInfo.lastModifiedDate ? moment(bomTemplateInfo.lastModifiedDate) : undefined;
      });
    }
    return res;
  }
}
