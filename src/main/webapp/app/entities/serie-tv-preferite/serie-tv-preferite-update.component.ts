import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { ISerie_tv_preferite, Serie_tv_preferite } from 'app/shared/model/serie-tv-preferite.model';
import { Serie_tv_preferiteService } from './serie-tv-preferite.service';

@Component({
  selector: 'jhi-serie-tv-preferite-update',
  templateUrl: './serie-tv-preferite-update.component.html'
})
export class Serie_tv_preferiteUpdateComponent implements OnInit {
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    user: [],
    name: [],
    overview: [],
    popularity: [],
    avg_vote: []
  });

  constructor(
    protected serie_tv_preferiteService: Serie_tv_preferiteService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ serie_tv_preferite }) => {
      this.updateForm(serie_tv_preferite);
    });
  }

  updateForm(serie_tv_preferite: ISerie_tv_preferite) {
    this.editForm.patchValue({
      id: serie_tv_preferite.id,
      user: serie_tv_preferite.user,
      name: serie_tv_preferite.name,
      overview: serie_tv_preferite.overview,
      popularity: serie_tv_preferite.popularity,
      avg_vote: serie_tv_preferite.avg_vote
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const serie_tv_preferite = this.createFromForm();
    if (serie_tv_preferite.id !== undefined) {
      this.subscribeToSaveResponse(this.serie_tv_preferiteService.update(serie_tv_preferite));
    } else {
      this.subscribeToSaveResponse(this.serie_tv_preferiteService.create(serie_tv_preferite));
    }
  }

  private createFromForm(): ISerie_tv_preferite {
    return {
      ...new Serie_tv_preferite(),
      id: this.editForm.get(['id']).value,
      user: this.editForm.get(['user']).value,
      name: this.editForm.get(['name']).value,
      overview: this.editForm.get(['overview']).value,
      popularity: this.editForm.get(['popularity']).value,
      avg_vote: this.editForm.get(['avg_vote']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ISerie_tv_preferite>>) {
    result.subscribe(() => this.onSaveSuccess(), () => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }
}
