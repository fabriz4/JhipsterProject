/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { TvBookMarksTestModule } from '../../../test.module';
import { Serie_tv_preferiteUpdateComponent } from 'app/entities/serie-tv-preferite/serie-tv-preferite-update.component';
import { Serie_tv_preferiteService } from 'app/entities/serie-tv-preferite/serie-tv-preferite.service';
import { Serie_tv_preferite } from 'app/shared/model/serie-tv-preferite.model';

describe('Component Tests', () => {
  describe('Serie_tv_preferite Management Update Component', () => {
    let comp: Serie_tv_preferiteUpdateComponent;
    let fixture: ComponentFixture<Serie_tv_preferiteUpdateComponent>;
    let service: Serie_tv_preferiteService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TvBookMarksTestModule],
        declarations: [Serie_tv_preferiteUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(Serie_tv_preferiteUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(Serie_tv_preferiteUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(Serie_tv_preferiteService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Serie_tv_preferite(123);
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new Serie_tv_preferite();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
