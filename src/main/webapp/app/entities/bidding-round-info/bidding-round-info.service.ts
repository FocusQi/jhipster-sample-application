import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IBiddingRoundInfo } from 'app/shared/model/bidding-round-info.model';

type EntityResponseType = HttpResponse<IBiddingRoundInfo>;
type EntityArrayResponseType = HttpResponse<IBiddingRoundInfo[]>;

@Injectable({ providedIn: 'root' })
export class BiddingRoundInfoService {
  public resourceUrl = SERVER_API_URL + 'api/bidding-round-infos';

  constructor(protected http: HttpClient) {}

  create(biddingRoundInfo: IBiddingRoundInfo): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(biddingRoundInfo);
    return this.http
      .post<IBiddingRoundInfo>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(biddingRoundInfo: IBiddingRoundInfo): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(biddingRoundInfo);
    return this.http
      .put<IBiddingRoundInfo>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IBiddingRoundInfo>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IBiddingRoundInfo[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(biddingRoundInfo: IBiddingRoundInfo): IBiddingRoundInfo {
    const copy: IBiddingRoundInfo = Object.assign({}, biddingRoundInfo, {
      startTime: biddingRoundInfo.startTime && biddingRoundInfo.startTime.isValid() ? biddingRoundInfo.startTime.toJSON() : undefined,
      endTime: biddingRoundInfo.endTime && biddingRoundInfo.endTime.isValid() ? biddingRoundInfo.endTime.toJSON() : undefined,
      createdDate:
        biddingRoundInfo.createdDate && biddingRoundInfo.createdDate.isValid() ? biddingRoundInfo.createdDate.toJSON() : undefined,
      lastModifiedDate:
        biddingRoundInfo.lastModifiedDate && biddingRoundInfo.lastModifiedDate.isValid()
          ? biddingRoundInfo.lastModifiedDate.toJSON()
          : undefined,
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.startTime = res.body.startTime ? moment(res.body.startTime) : undefined;
      res.body.endTime = res.body.endTime ? moment(res.body.endTime) : undefined;
      res.body.createdDate = res.body.createdDate ? moment(res.body.createdDate) : undefined;
      res.body.lastModifiedDate = res.body.lastModifiedDate ? moment(res.body.lastModifiedDate) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((biddingRoundInfo: IBiddingRoundInfo) => {
        biddingRoundInfo.startTime = biddingRoundInfo.startTime ? moment(biddingRoundInfo.startTime) : undefined;
        biddingRoundInfo.endTime = biddingRoundInfo.endTime ? moment(biddingRoundInfo.endTime) : undefined;
        biddingRoundInfo.createdDate = biddingRoundInfo.createdDate ? moment(biddingRoundInfo.createdDate) : undefined;
        biddingRoundInfo.lastModifiedDate = biddingRoundInfo.lastModifiedDate ? moment(biddingRoundInfo.lastModifiedDate) : undefined;
      });
    }
    return res;
  }
}
