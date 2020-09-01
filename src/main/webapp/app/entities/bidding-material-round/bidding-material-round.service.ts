import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IBiddingMaterialRound } from 'app/shared/model/bidding-material-round.model';

type EntityResponseType = HttpResponse<IBiddingMaterialRound>;
type EntityArrayResponseType = HttpResponse<IBiddingMaterialRound[]>;

@Injectable({ providedIn: 'root' })
export class BiddingMaterialRoundService {
  public resourceUrl = SERVER_API_URL + 'api/bidding-material-rounds';

  constructor(protected http: HttpClient) {}

  create(biddingMaterialRound: IBiddingMaterialRound): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(biddingMaterialRound);
    return this.http
      .post<IBiddingMaterialRound>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(biddingMaterialRound: IBiddingMaterialRound): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(biddingMaterialRound);
    return this.http
      .put<IBiddingMaterialRound>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IBiddingMaterialRound>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IBiddingMaterialRound[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(biddingMaterialRound: IBiddingMaterialRound): IBiddingMaterialRound {
    const copy: IBiddingMaterialRound = Object.assign({}, biddingMaterialRound, {
      createdDate:
        biddingMaterialRound.createdDate && biddingMaterialRound.createdDate.isValid()
          ? biddingMaterialRound.createdDate.toJSON()
          : undefined,
      lastModifiedDate:
        biddingMaterialRound.lastModifiedDate && biddingMaterialRound.lastModifiedDate.isValid()
          ? biddingMaterialRound.lastModifiedDate.toJSON()
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
      res.body.forEach((biddingMaterialRound: IBiddingMaterialRound) => {
        biddingMaterialRound.createdDate = biddingMaterialRound.createdDate ? moment(biddingMaterialRound.createdDate) : undefined;
        biddingMaterialRound.lastModifiedDate = biddingMaterialRound.lastModifiedDate
          ? moment(biddingMaterialRound.lastModifiedDate)
          : undefined;
      });
    }
    return res;
  }
}
