import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IPriceCompare } from 'app/shared/model/price-compare.model';

type EntityResponseType = HttpResponse<IPriceCompare>;
type EntityArrayResponseType = HttpResponse<IPriceCompare[]>;

@Injectable({ providedIn: 'root' })
export class PriceCompareService {
  public resourceUrl = SERVER_API_URL + 'api/price-compares';

  constructor(protected http: HttpClient) {}

  create(priceCompare: IPriceCompare): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(priceCompare);
    return this.http
      .post<IPriceCompare>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(priceCompare: IPriceCompare): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(priceCompare);
    return this.http
      .put<IPriceCompare>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IPriceCompare>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IPriceCompare[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(priceCompare: IPriceCompare): IPriceCompare {
    const copy: IPriceCompare = Object.assign({}, priceCompare, {
      createdDate: priceCompare.createdDate && priceCompare.createdDate.isValid() ? priceCompare.createdDate.toJSON() : undefined,
      lastModifiedDate:
        priceCompare.lastModifiedDate && priceCompare.lastModifiedDate.isValid() ? priceCompare.lastModifiedDate.toJSON() : undefined,
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
      res.body.forEach((priceCompare: IPriceCompare) => {
        priceCompare.createdDate = priceCompare.createdDate ? moment(priceCompare.createdDate) : undefined;
        priceCompare.lastModifiedDate = priceCompare.lastModifiedDate ? moment(priceCompare.lastModifiedDate) : undefined;
      });
    }
    return res;
  }
}
