import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { TvBookMarksSharedCommonModule, JhiLoginModalComponent, HasAnyAuthorityDirective } from './';

@NgModule({
  imports: [TvBookMarksSharedCommonModule],
  declarations: [JhiLoginModalComponent, HasAnyAuthorityDirective],
  entryComponents: [JhiLoginModalComponent],
  exports: [TvBookMarksSharedCommonModule, JhiLoginModalComponent, HasAnyAuthorityDirective],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TvBookMarksSharedModule {
  static forRoot() {
    return {
      ngModule: TvBookMarksSharedModule
    };
  }
}
