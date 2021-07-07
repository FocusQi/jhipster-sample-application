import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IBomTemplateInfoColumn } from 'app/shared/model/bom-template-info-column.model';

type EntityResponseType = HttpResponse<IBomTemplateInfoColumn>;
type EntityArrayResponseType = HttpResponse<IBomTemplateInfoColumn[]>;

@Injectable({ providedIn: 'root' })
export class BomTemplateInfoColumnService {
  public resourceUrl = SERVER_API_URL + 'api/bom-template-info-columns';

  constructor(protected http: HttpClient) {}

  create(bomTemplateInfoColumn: IBomTemplateInfoColumn): Observable<EntityResponseType> {
    return this.http.post<IBomTemplateInfoColumn>(this.resourceUrl, bomTemplateInfoColumn, { observe: 'response' });
  }

  update(bomTemplateInfoColumn: IBomTemplateInfoColumn): Observable<EntityResponseType> {
    return this.http.put<IBomTemplateInfoColumn>(this.resourceUrl, bomTemplateInfoColumn, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IBomTemplateInfoColumn>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IBomTemplateInfoColumn[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
