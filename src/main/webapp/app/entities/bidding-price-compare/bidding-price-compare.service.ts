import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IBiddingPriceCompare } from 'app/shared/model/bidding-price-compare.model';

type EntityResponseType = HttpResponse<IBiddingPriceCompare>;
type EntityArrayResponseType = HttpResponse<IBiddingPriceCompare[]>;

@Injectable({ providedIn: 'root' })
export class BiddingPriceCompareService {
  public resourceUrl = SERVER_API_URL + 'api/bidding-price-compares';

  constructor(protected http: HttpClient) {}

  create(biddingPriceCompare: IBiddingPriceCompare): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(biddingPriceCompare);
    return this.http
      .post<IBiddingPriceCompare>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(biddingPriceCompare: IBiddingPriceCompare): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(biddingPriceCompare);
    return this.http
      .put<IBiddingPriceCompare>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IBiddingPriceCompare>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IBiddingPriceCompare[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(biddingPriceCompare: IBiddingPriceCompare): IBiddingPriceCompare {
    const copy: IBiddingPriceCompare = Object.assign({}, biddingPriceCompare, {
      createdDate:
        biddingPriceCompare.createdDate && biddingPriceCompare.createdDate.isValid() ? biddingPriceCompare.createdDate.toJSON() : undefined,
      lastModifiedDate:
        biddingPriceCompare.lastModifiedDate && biddingPriceCompare.lastModifiedDate.isValid()
          ? biddingPriceCompare.lastModifiedDate.toJSON()
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
      res.body.forEach((biddingPriceCompare: IBiddingPriceCompare) => {
        biddingPriceCompare.createdDate = biddingPriceCompare.createdDate ? moment(biddingPriceCompare.createdDate) : undefined;
        biddingPriceCompare.lastModifiedDate = biddingPriceCompare.lastModifiedDate
          ? moment(biddingPriceCompare.lastModifiedDate)
          : undefined;
      });
    }
    return res;
  }
}
