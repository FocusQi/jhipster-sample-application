import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ISrmMsgList } from 'app/shared/model/srm-msg-list.model';

type EntityResponseType = HttpResponse<ISrmMsgList>;
type EntityArrayResponseType = HttpResponse<ISrmMsgList[]>;

@Injectable({ providedIn: 'root' })
export class SrmMsgListService {
  public resourceUrl = SERVER_API_URL + 'api/srm-msg-lists';

  constructor(protected http: HttpClient) {}

  create(srmMsgList: ISrmMsgList): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(srmMsgList);
    return this.http
      .post<ISrmMsgList>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(srmMsgList: ISrmMsgList): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(srmMsgList);
    return this.http
      .put<ISrmMsgList>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<ISrmMsgList>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<ISrmMsgList[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(srmMsgList: ISrmMsgList): ISrmMsgList {
    const copy: ISrmMsgList = Object.assign({}, srmMsgList, {
      msgCreateTime: srmMsgList.msgCreateTime && srmMsgList.msgCreateTime.isValid() ? srmMsgList.msgCreateTime.toJSON() : undefined,
      msgSendTime: srmMsgList.msgSendTime && srmMsgList.msgSendTime.isValid() ? srmMsgList.msgSendTime.toJSON() : undefined,
      createdDate: srmMsgList.createdDate && srmMsgList.createdDate.isValid() ? srmMsgList.createdDate.toJSON() : undefined,
      lastModifiedDate:
        srmMsgList.lastModifiedDate && srmMsgList.lastModifiedDate.isValid() ? srmMsgList.lastModifiedDate.toJSON() : undefined,
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.msgCreateTime = res.body.msgCreateTime ? moment(res.body.msgCreateTime) : undefined;
      res.body.msgSendTime = res.body.msgSendTime ? moment(res.body.msgSendTime) : undefined;
      res.body.createdDate = res.body.createdDate ? moment(res.body.createdDate) : undefined;
      res.body.lastModifiedDate = res.body.lastModifiedDate ? moment(res.body.lastModifiedDate) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((srmMsgList: ISrmMsgList) => {
        srmMsgList.msgCreateTime = srmMsgList.msgCreateTime ? moment(srmMsgList.msgCreateTime) : undefined;
        srmMsgList.msgSendTime = srmMsgList.msgSendTime ? moment(srmMsgList.msgSendTime) : undefined;
        srmMsgList.createdDate = srmMsgList.createdDate ? moment(srmMsgList.createdDate) : undefined;
        srmMsgList.lastModifiedDate = srmMsgList.lastModifiedDate ? moment(srmMsgList.lastModifiedDate) : undefined;
      });
    }
    return res;
  }
}
