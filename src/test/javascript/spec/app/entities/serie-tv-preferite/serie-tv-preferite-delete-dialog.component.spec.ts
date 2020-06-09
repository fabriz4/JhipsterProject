/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { TvBookMarksTestModule } from '../../../test.module';
import { Serie_tv_preferiteDeleteDialogComponent } from 'app/entities/serie-tv-preferite/serie-tv-preferite-delete-dialog.component';
import { Serie_tv_preferiteService } from 'app/entities/serie-tv-preferite/serie-tv-preferite.service';

describe('Component Tests', () => {
  describe('Serie_tv_preferite Management Delete Component', () => {
    let comp: Serie_tv_preferiteDeleteDialogComponent;
    let fixture: ComponentFixture<Serie_tv_preferiteDeleteDialogComponent>;
    let service: Serie_tv_preferiteService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TvBookMarksTestModule],
        declarations: [Serie_tv_preferiteDeleteDialogComponent]
      })
        .overrideTemplate(Serie_tv_preferiteDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(Serie_tv_preferiteDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(Serie_tv_preferiteService);
      mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
      mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
    });

    describe('confirmDelete', () => {
      it('Should call delete service on confirmDelete', inject(
        [],
        fakeAsync(() => {
          // GIVEN
          spyOn(service, 'delete').and.returnValue(of({}));

          // WHEN
          comp.confirmDelete(123);
          tick();

          // THEN
          expect(service.delete).toHaveBeenCalledWith(123);
          expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
          expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
        })
      ));
    });
  });
});
