import { NgModule } from '@angular/core';

import { TvBookMarksSharedLibsModule, JhiAlertComponent, JhiAlertErrorComponent } from './';

@NgModule({
  imports: [TvBookMarksSharedLibsModule],
  declarations: [JhiAlertComponent, JhiAlertErrorComponent],
  exports: [TvBookMarksSharedLibsModule, JhiAlertComponent, JhiAlertErrorComponent]
})
export class TvBookMarksSharedCommonModule {}
