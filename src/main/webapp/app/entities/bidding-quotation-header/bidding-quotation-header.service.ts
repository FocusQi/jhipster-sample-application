import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IBiddingQuotationHeader } from 'app/shared/model/bidding-quotation-header.model';

type EntityResponseType = HttpResponse<IBiddingQuotationHeader>;
type EntityArrayResponseType = HttpResponse<IBiddingQuotationHeader[]>;

@Injectable({ providedIn: 'root' })
export class BiddingQuotationHeaderService {
  public resourceUrl = SERVER_API_URL + 'api/bidding-quotation-headers';

  constructor(protected http: HttpClient) {}

  create(biddingQuotationHeader: IBiddingQuotationHeader): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(biddingQuotationHeader);
    return this.http
      .post<IBiddingQuotationHeader>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(biddingQuotationHeader: IBiddingQuotationHeader): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(biddingQuotationHeader);
    return this.http
      .put<IBiddingQuotationHeader>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IBiddingQuotationHeader>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IBiddingQuotationHeader[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(biddingQuotationHeader: IBiddingQuotationHeader): IBiddingQuotationHeader {
    const copy: IBiddingQuotationHeader = Object.assign({}, biddingQuotationHeader, {
      issuanceDate:
        biddingQuotationHeader.issuanceDate && biddingQuotationHeader.issuanceDate.isValid()
          ? biddingQuotationHeader.issuanceDate.format(DATE_FORMAT)
          : undefined,
      createdDate:
        biddingQuotationHeader.createdDate && biddingQuotationHeader.createdDate.isValid()
          ? biddingQuotationHeader.createdDate.toJSON()
          : undefined,
      lastModifiedDate:
        biddingQuotationHeader.lastModifiedDate && biddingQuotationHeader.lastModifiedDate.isValid()
          ? biddingQuotationHeader.lastModifiedDate.toJSON()
          : undefined,
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.issuanceDate = res.body.issuanceDate ? moment(res.body.issuanceDate) : undefined;
      res.body.createdDate = res.body.createdDate ? moment(res.body.createdDate) : undefined;
      res.body.lastModifiedDate = res.body.lastModifiedDate ? moment(res.body.lastModifiedDate) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((biddingQuotationHeader: IBiddingQuotationHeader) => {
        biddingQuotationHeader.issuanceDate = biddingQuotationHeader.issuanceDate ? moment(biddingQuotationHeader.issuanceDate) : undefined;
        biddingQuotationHeader.createdDate = biddingQuotationHeader.createdDate ? moment(biddingQuotationHeader.createdDate) : undefined;
        biddingQuotationHeader.lastModifiedDate = biddingQuotationHeader.lastModifiedDate
          ? moment(biddingQuotationHeader.lastModifiedDate)
          : undefined;
      });
    }
    return res;
  }
}
