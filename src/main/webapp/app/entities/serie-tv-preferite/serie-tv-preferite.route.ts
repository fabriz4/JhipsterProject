import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { Serie_tv_preferite } from 'app/shared/model/serie-tv-preferite.model';
import { Serie_tv_preferiteService } from './serie-tv-preferite.service';
import { Serie_tv_preferiteComponent } from './serie-tv-preferite.component';
import { Serie_tv_preferiteDetailComponent } from './serie-tv-preferite-detail.component';
import { Serie_tv_preferiteUpdateComponent } from './serie-tv-preferite-update.component';
import { Serie_tv_preferiteDeletePopupComponent } from './serie-tv-preferite-delete-dialog.component';
import { ISerie_tv_preferite } from 'app/shared/model/serie-tv-preferite.model';

@Injectable({ providedIn: 'root' })
export class Serie_tv_preferiteResolve implements Resolve<ISerie_tv_preferite> {
  constructor(private service: Serie_tv_preferiteService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<ISerie_tv_preferite> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<Serie_tv_preferite>) => response.ok),
        map((serie_tv_preferite: HttpResponse<Serie_tv_preferite>) => serie_tv_preferite.body)
      );
    }
    return of(new Serie_tv_preferite());
  }
}

export const serie_tv_preferiteRoute: Routes = [
  {
    path: '',
    component: Serie_tv_preferiteComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'Serie_tv_preferites'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: Serie_tv_preferiteDetailComponent,
    resolve: {
      serie_tv_preferite: Serie_tv_preferiteResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'Serie_tv_preferites'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: Serie_tv_preferiteUpdateComponent,
    resolve: {
      serie_tv_preferite: Serie_tv_preferiteResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'Serie_tv_preferites'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: Serie_tv_preferiteUpdateComponent,
    resolve: {
      serie_tv_preferite: Serie_tv_preferiteResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'Serie_tv_preferites'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const serie_tv_preferitePopupRoute: Routes = [
  {
    path: ':id/delete',
    component: Serie_tv_preferiteDeletePopupComponent,
    resolve: {
      serie_tv_preferite: Serie_tv_preferiteResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'Serie_tv_preferites'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
