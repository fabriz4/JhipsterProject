import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TvBookMarksSharedModule } from 'app/shared';
import {
  Serie_tv_preferiteComponent,
  Serie_tv_preferiteDetailComponent,
  Serie_tv_preferiteUpdateComponent,
  Serie_tv_preferiteDeletePopupComponent,
  Serie_tv_preferiteDeleteDialogComponent,
  serie_tv_preferiteRoute,
  serie_tv_preferitePopupRoute
} from './';

const ENTITY_STATES = [...serie_tv_preferiteRoute, ...serie_tv_preferitePopupRoute];

@NgModule({
  imports: [TvBookMarksSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    Serie_tv_preferiteComponent,
    Serie_tv_preferiteDetailComponent,
    Serie_tv_preferiteUpdateComponent,
    Serie_tv_preferiteDeleteDialogComponent,
    Serie_tv_preferiteDeletePopupComponent
  ],
  entryComponents: [
    Serie_tv_preferiteComponent,
    Serie_tv_preferiteUpdateComponent,
    Serie_tv_preferiteDeleteDialogComponent,
    Serie_tv_preferiteDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TvBookMarksSerie_tv_preferiteModule {}
