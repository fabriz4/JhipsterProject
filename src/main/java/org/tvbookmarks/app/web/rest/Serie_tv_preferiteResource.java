package org.tvbookmarks.app.web.rest;

import org.tvbookmarks.app.domain.Serie_tv_preferite;
import org.tvbookmarks.app.repository.Serie_tv_preferiteRepository;
import org.tvbookmarks.app.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link org.tvbookmarks.app.domain.Serie_tv_preferite}.
 */
@RestController
@RequestMapping("/api")
public class Serie_tv_preferiteResource {

    private final Logger log = LoggerFactory.getLogger(Serie_tv_preferiteResource.class);

    private static final String ENTITY_NAME = "serie_tv_preferite";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final Serie_tv_preferiteRepository serie_tv_preferiteRepository;

    public Serie_tv_preferiteResource(Serie_tv_preferiteRepository serie_tv_preferiteRepository) {
        this.serie_tv_preferiteRepository = serie_tv_preferiteRepository;
    }

    /**
     * {@code POST  /serie-tv-preferites} : Create a new serie_tv_preferite.
     *
     * @param serie_tv_preferite the serie_tv_preferite to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new serie_tv_preferite, or with status {@code 400 (Bad Request)} if the serie_tv_preferite has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/serie-tv-preferites")
    public ResponseEntity<Serie_tv_preferite> createSerie_tv_preferite(@RequestBody Serie_tv_preferite serie_tv_preferite) throws URISyntaxException {
        log.debug("REST request to save Serie_tv_preferite : {}", serie_tv_preferite);
        if (serie_tv_preferite.getId() != null) {
            throw new BadRequestAlertException("A new serie_tv_preferite cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Serie_tv_preferite result = serie_tv_preferiteRepository.save(serie_tv_preferite);
        return ResponseEntity.created(new URI("/api/serie-tv-preferites/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /serie-tv-preferites} : Updates an existing serie_tv_preferite.
     *
     * @param serie_tv_preferite the serie_tv_preferite to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated serie_tv_preferite,
     * or with status {@code 400 (Bad Request)} if the serie_tv_preferite is not valid,
     * or with status {@code 500 (Internal Server Error)} if the serie_tv_preferite couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/serie-tv-preferites")
    public ResponseEntity<Serie_tv_preferite> updateSerie_tv_preferite(@RequestBody Serie_tv_preferite serie_tv_preferite) throws URISyntaxException {
        log.debug("REST request to update Serie_tv_preferite : {}", serie_tv_preferite);
        if (serie_tv_preferite.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Serie_tv_preferite result = serie_tv_preferiteRepository.save(serie_tv_preferite);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, serie_tv_preferite.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /serie-tv-preferites} : get all the serie_tv_preferites.
     *
     * @param pageable the pagination information.
     * @param queryParams a {@link MultiValueMap} query parameters.
     * @param uriBuilder a {@link UriComponentsBuilder} URI builder.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of serie_tv_preferites in body.
     */
    @GetMapping("/serie-tv-preferites")
    public ResponseEntity<List<Serie_tv_preferite>> getAllSerie_tv_preferites(Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get a page of Serie_tv_preferites");
        Page<Serie_tv_preferite> page = serie_tv_preferiteRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /serie-tv-preferites/:id} : get the "id" serie_tv_preferite.
     *
     * @param id the id of the serie_tv_preferite to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the serie_tv_preferite, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/serie-tv-preferites/{id}")
    public ResponseEntity<Serie_tv_preferite> getSerie_tv_preferite(@PathVariable Long id) {
        log.debug("REST request to get Serie_tv_preferite : {}", id);
        Optional<Serie_tv_preferite> serie_tv_preferite = serie_tv_preferiteRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(serie_tv_preferite);
    }

    /**
     * {@code DELETE  /serie-tv-preferites/:id} : delete the "id" serie_tv_preferite.
     *
     * @param id the id of the serie_tv_preferite to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/serie-tv-preferites/{id}")
    public ResponseEntity<Void> deleteSerie_tv_preferite(@PathVariable Long id) {
        log.debug("REST request to delete Serie_tv_preferite : {}", id);
        serie_tv_preferiteRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
