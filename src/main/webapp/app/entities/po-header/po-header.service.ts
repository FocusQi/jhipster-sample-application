import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IPoHeader } from 'app/shared/model/po-header.model';

type EntityResponseType = HttpResponse<IPoHeader>;
type EntityArrayResponseType = HttpResponse<IPoHeader[]>;

@Injectable({ providedIn: 'root' })
export class PoHeaderService {
  public resourceUrl = SERVER_API_URL + 'api/po-headers';

  constructor(protected http: HttpClient) {}

  create(poHeader: IPoHeader): Observable<EntityResponseType> {
    return this.http.post<IPoHeader>(this.resourceUrl, poHeader, { observe: 'response' });
  }

  update(poHeader: IPoHeader): Observable<EntityResponseType> {
    return this.http.put<IPoHeader>(this.resourceUrl, poHeader, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IPoHeader>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IPoHeader[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
