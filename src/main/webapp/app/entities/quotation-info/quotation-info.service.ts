import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IQuotationInfo } from 'app/shared/model/quotation-info.model';

type EntityResponseType = HttpResponse<IQuotationInfo>;
type EntityArrayResponseType = HttpResponse<IQuotationInfo[]>;

@Injectable({ providedIn: 'root' })
export class QuotationInfoService {
  public resourceUrl = SERVER_API_URL + 'api/quotation-infos';

  constructor(protected http: HttpClient) {}

  create(quotationInfo: IQuotationInfo): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(quotationInfo);
    return this.http
      .post<IQuotationInfo>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(quotationInfo: IQuotationInfo): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(quotationInfo);
    return this.http
      .put<IQuotationInfo>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IQuotationInfo>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IQuotationInfo[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(quotationInfo: IQuotationInfo): IQuotationInfo {
    const copy: IQuotationInfo = Object.assign({}, quotationInfo, {
      validDate: quotationInfo.validDate && quotationInfo.validDate.isValid() ? quotationInfo.validDate.format(DATE_FORMAT) : undefined,
      currentPriceValidDate:
        quotationInfo.currentPriceValidDate && quotationInfo.currentPriceValidDate.isValid()
          ? quotationInfo.currentPriceValidDate.format(DATE_FORMAT)
          : undefined,
      createdDate: quotationInfo.createdDate && quotationInfo.createdDate.isValid() ? quotationInfo.createdDate.toJSON() : undefined,
      lastModifiedDate:
        quotationInfo.lastModifiedDate && quotationInfo.lastModifiedDate.isValid() ? quotationInfo.lastModifiedDate.toJSON() : undefined,
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
      res.body.forEach((quotationInfo: IQuotationInfo) => {
        quotationInfo.validDate = quotationInfo.validDate ? moment(quotationInfo.validDate) : undefined;
        quotationInfo.currentPriceValidDate = quotationInfo.currentPriceValidDate ? moment(quotationInfo.currentPriceValidDate) : undefined;
        quotationInfo.createdDate = quotationInfo.createdDate ? moment(quotationInfo.createdDate) : undefined;
        quotationInfo.lastModifiedDate = quotationInfo.lastModifiedDate ? moment(quotationInfo.lastModifiedDate) : undefined;
      });
    }
    return res;
  }
}
