import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IBiddingQuotationInfo } from 'app/shared/model/bidding-quotation-info.model';

type EntityResponseType = HttpResponse<IBiddingQuotationInfo>;
type EntityArrayResponseType = HttpResponse<IBiddingQuotationInfo[]>;

@Injectable({ providedIn: 'root' })
export class BiddingQuotationInfoService {
  public resourceUrl = SERVER_API_URL + 'api/bidding-quotation-infos';

  constructor(protected http: HttpClient) {}

  create(biddingQuotationInfo: IBiddingQuotationInfo): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(biddingQuotationInfo);
    return this.http
      .post<IBiddingQuotationInfo>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(biddingQuotationInfo: IBiddingQuotationInfo): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(biddingQuotationInfo);
    return this.http
      .put<IBiddingQuotationInfo>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IBiddingQuotationInfo>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IBiddingQuotationInfo[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(biddingQuotationInfo: IBiddingQuotationInfo): IBiddingQuotationInfo {
    const copy: IBiddingQuotationInfo = Object.assign({}, biddingQuotationInfo, {
      validDate:
        biddingQuotationInfo.validDate && biddingQuotationInfo.validDate.isValid()
          ? biddingQuotationInfo.validDate.format(DATE_FORMAT)
          : undefined,
      currentPriceValidDate:
        biddingQuotationInfo.currentPriceValidDate && biddingQuotationInfo.currentPriceValidDate.isValid()
          ? biddingQuotationInfo.currentPriceValidDate.format(DATE_FORMAT)
          : undefined,
      createdDate:
        biddingQuotationInfo.createdDate && biddingQuotationInfo.createdDate.isValid()
          ? biddingQuotationInfo.createdDate.toJSON()
          : undefined,
      lastModifiedDate:
        biddingQuotationInfo.lastModifiedDate && biddingQuotationInfo.lastModifiedDate.isValid()
          ? biddingQuotationInfo.lastModifiedDate.toJSON()
          : undefined,
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.validDate = res.body.validDate ? moment(res.body.validDate) : undefined;
      res.body.currentPriceValidDate = res.body.currentPriceValidDate ? moment(res.body.currentPriceValidDate) : undefined;
      res.body.createdDate = res.body.createdDate ? moment(res.body.createdDate) : undefined;
      res.body.lastModifiedDate = res.body.lastModifiedDate ? moment(res.body.lastModifiedDate) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((biddingQuotationInfo: IBiddingQuotationInfo) => {
        biddingQuotationInfo.validDate = biddingQuotationInfo.validDate ? moment(biddingQuotationInfo.validDate) : undefined;
        biddingQuotationInfo.currentPriceValidDate = biddingQuotationInfo.currentPriceValidDate
          ? moment(biddingQuotationInfo.currentPriceValidDate)
          : undefined;
        biddingQuotationInfo.createdDate = biddingQuotationInfo.createdDate ? moment(biddingQuotationInfo.createdDate) : undefined;
        biddingQuotationInfo.lastModifiedDate = biddingQuotationInfo.lastModifiedDate
          ? moment(biddingQuotationInfo.lastModifiedDate)
          : undefined;
      });
    }
    return res;
  }
}
