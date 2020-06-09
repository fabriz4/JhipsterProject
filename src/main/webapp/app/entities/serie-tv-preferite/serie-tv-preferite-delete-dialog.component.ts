import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ISerie_tv_preferite } from 'app/shared/model/serie-tv-preferite.model';
import { Serie_tv_preferiteService } from './serie-tv-preferite.service';

@Component({
  selector: 'jhi-serie-tv-preferite-delete-dialog',
  templateUrl: './serie-tv-preferite-delete-dialog.component.html'
})
export class Serie_tv_preferiteDeleteDialogComponent {
  serie_tv_preferite: ISerie_tv_preferite;

  constructor(
    protected serie_tv_preferiteService: Serie_tv_preferiteService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.serie_tv_preferiteService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'serie_tv_preferiteListModification',
        content: 'Deleted an serie_tv_preferite'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-serie-tv-preferite-delete-popup',
  template: ''
})
export class Serie_tv_preferiteDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ serie_tv_preferite }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(Serie_tv_preferiteDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.serie_tv_preferite = serie_tv_preferite;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/serie-tv-preferite', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/serie-tv-preferite', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          }
        );
      }, 0);
    });
  }

  ngOnDestroy() {
    this.ngbModalRef = null;
  }
}
