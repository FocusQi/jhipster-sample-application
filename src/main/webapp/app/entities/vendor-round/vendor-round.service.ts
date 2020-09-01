import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IVendorRound } from 'app/shared/model/vendor-round.model';

type EntityResponseType = HttpResponse<IVendorRound>;
type EntityArrayResponseType = HttpResponse<IVendorRound[]>;

@Injectable({ providedIn: 'root' })
export class VendorRoundService {
  public resourceUrl = SERVER_API_URL + 'api/vendor-rounds';

  constructor(protected http: HttpClient) {}

  create(vendorRound: IVendorRound): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(vendorRound);
    return this.http
      .post<IVendorRound>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(vendorRound: IVendorRound): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(vendorRound);
    return this.http
      .put<IVendorRound>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IVendorRound>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IVendorRound[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(vendorRound: IVendorRound): IVendorRound {
    const copy: IVendorRound = Object.assign({}, vendorRound, {
      createdDate: vendorRound.createdDate && vendorRound.createdDate.isValid() ? vendorRound.createdDate.toJSON() : undefined,
      lastModifiedDate:
        vendorRound.lastModifiedDate && vendorRound.lastModifiedDate.isValid() ? vendorRound.lastModifiedDate.toJSON() : undefined,
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
      res.body.forEach((vendorRound: IVendorRound) => {
        vendorRound.createdDate = vendorRound.createdDate ? moment(vendorRound.createdDate) : undefined;
        vendorRound.lastModifiedDate = vendorRound.lastModifiedDate ? moment(vendorRound.lastModifiedDate) : undefined;
      });
    }
    return res;
  }
}
