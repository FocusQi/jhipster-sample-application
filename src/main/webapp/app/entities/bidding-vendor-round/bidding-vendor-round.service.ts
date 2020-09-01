import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IBiddingVendorRound } from 'app/shared/model/bidding-vendor-round.model';

type EntityResponseType = HttpResponse<IBiddingVendorRound>;
type EntityArrayResponseType = HttpResponse<IBiddingVendorRound[]>;

@Injectable({ providedIn: 'root' })
export class BiddingVendorRoundService {
  public resourceUrl = SERVER_API_URL + 'api/bidding-vendor-rounds';

  constructor(protected http: HttpClient) {}

  create(biddingVendorRound: IBiddingVendorRound): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(biddingVendorRound);
    return this.http
      .post<IBiddingVendorRound>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(biddingVendorRound: IBiddingVendorRound): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(biddingVendorRound);
    return this.http
      .put<IBiddingVendorRound>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IBiddingVendorRound>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IBiddingVendorRound[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(biddingVendorRound: IBiddingVendorRound): IBiddingVendorRound {
    const copy: IBiddingVendorRound = Object.assign({}, biddingVendorRound, {
      createdDate:
        biddingVendorRound.createdDate && biddingVendorRound.createdDate.isValid() ? biddingVendorRound.createdDate.toJSON() : undefined,
      lastModifiedDate:
        biddingVendorRound.lastModifiedDate && biddingVendorRound.lastModifiedDate.isValid()
          ? biddingVendorRound.lastModifiedDate.toJSON()
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
      res.body.forEach((biddingVendorRound: IBiddingVendorRound) => {
        biddingVendorRound.createdDate = biddingVendorRound.createdDate ? moment(biddingVendorRound.createdDate) : undefined;
        biddingVendorRound.lastModifiedDate = biddingVendorRound.lastModifiedDate ? moment(biddingVendorRound.lastModifiedDate) : undefined;
      });
    }
    return res;
  }
}
