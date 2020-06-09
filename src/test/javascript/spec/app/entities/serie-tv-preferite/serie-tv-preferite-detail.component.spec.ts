/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TvBookMarksTestModule } from '../../../test.module';
import { Serie_tv_preferiteDetailComponent } from 'app/entities/serie-tv-preferite/serie-tv-preferite-detail.component';
import { Serie_tv_preferite } from 'app/shared/model/serie-tv-preferite.model';

describe('Component Tests', () => {
  describe('Serie_tv_preferite Management Detail Component', () => {
    let comp: Serie_tv_preferiteDetailComponent;
    let fixture: ComponentFixture<Serie_tv_preferiteDetailComponent>;
    const route = ({ data: of({ serie_tv_preferite: new Serie_tv_preferite(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TvBookMarksTestModule],
        declarations: [Serie_tv_preferiteDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(Serie_tv_preferiteDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(Serie_tv_preferiteDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.serie_tv_preferite).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
