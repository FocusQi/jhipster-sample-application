import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IBiddingOpener } from 'app/shared/model/bidding-opener.model';

type EntityResponseType = HttpResponse<IBiddingOpener>;
type EntityArrayResponseType = HttpResponse<IBiddingOpener[]>;

@Injectable({ providedIn: 'root' })
export class BiddingOpenerService {
  public resourceUrl = SERVER_API_URL + 'api/bidding-openers';

  constructor(protected http: HttpClient) {}

  create(biddingOpener: IBiddingOpener): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(biddingOpener);
    return this.http
      .post<IBiddingOpener>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(biddingOpener: IBiddingOpener): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(biddingOpener);
    return this.http
      .put<IBiddingOpener>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IBiddingOpener>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IBiddingOpener[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(biddingOpener: IBiddingOpener): IBiddingOpener {
    const copy: IBiddingOpener = Object.assign({}, biddingOpener, {
      createdDate: biddingOpener.createdDate && biddingOpener.createdDate.isValid() ? biddingOpener.createdDate.toJSON() : undefined,
      lastModifiedDate:
        biddingOpener.lastModifiedDate && biddingOpener.lastModifiedDate.isValid() ? biddingOpener.lastModifiedDate.toJSON() : undefined,
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
      res.body.forEach((biddingOpener: IBiddingOpener) => {
        biddingOpener.createdDate = biddingOpener.createdDate ? moment(biddingOpener.createdDate) : undefined;
        biddingOpener.lastModifiedDate = biddingOpener.lastModifiedDate ? moment(biddingOpener.lastModifiedDate) : undefined;
      });
    }
    return res;
  }
}
