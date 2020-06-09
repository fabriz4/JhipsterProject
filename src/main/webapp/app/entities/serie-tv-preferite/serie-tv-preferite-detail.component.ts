import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ISerie_tv_preferite } from 'app/shared/model/serie-tv-preferite.model';

@Component({
  selector: 'jhi-serie-tv-preferite-detail',
  templateUrl: './serie-tv-preferite-detail.component.html'
})
export class Serie_tv_preferiteDetailComponent implements OnInit {
  serie_tv_preferite: ISerie_tv_preferite;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ serie_tv_preferite }) => {
      this.serie_tv_preferite = serie_tv_preferite;
    });
  }

  previousState() {
    window.history.back();
  }
}
