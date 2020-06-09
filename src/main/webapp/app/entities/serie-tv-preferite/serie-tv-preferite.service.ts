import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ISerie_tv_preferite } from 'app/shared/model/serie-tv-preferite.model';

type EntityResponseType = HttpResponse<ISerie_tv_preferite>;
type EntityArrayResponseType = HttpResponse<ISerie_tv_preferite[]>;

@Injectable({ providedIn: 'root' })
export class Serie_tv_preferiteService {
  public resourceUrl = SERVER_API_URL + 'api/serie-tv-preferites';

  constructor(protected http: HttpClient) {}

  create(serie_tv_preferite: ISerie_tv_preferite): Observable<EntityResponseType> {
    return this.http.post<ISerie_tv_preferite>(this.resourceUrl, serie_tv_preferite, { observe: 'response' });
  }

  update(serie_tv_preferite: ISerie_tv_preferite): Observable<EntityResponseType> {
    return this.http.put<ISerie_tv_preferite>(this.resourceUrl, serie_tv_preferite, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ISerie_tv_preferite>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ISerie_tv_preferite[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
