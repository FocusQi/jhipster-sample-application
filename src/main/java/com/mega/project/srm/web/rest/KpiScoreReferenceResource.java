package com.mega.project.srm.web.rest;

import com.mega.project.srm.domain.KpiScoreReference;
import com.mega.project.srm.repository.KpiScoreReferenceRepository;
import com.mega.project.srm.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 * REST controller for managing {@link com.mega.project.srm.domain.KpiScoreReference}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class KpiScoreReferenceResource {
    private final Logger log = LoggerFactory.getLogger(KpiScoreReferenceResource.class);

    private static final String ENTITY_NAME = "kpiScoreReference";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final KpiScoreReferenceRepository kpiScoreReferenceRepository;

    public KpiScoreReferenceResource(KpiScoreReferenceRepository kpiScoreReferenceRepository) {
        this.kpiScoreReferenceRepository = kpiScoreReferenceRepository;
    }

    /**
     * {@code POST  /kpi-score-references} : Create a new kpiScoreReference.
     *
     * @param kpiScoreReference the kpiScoreReference to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new kpiScoreReference, or with status {@code 400 (Bad Request)} if the kpiScoreReference has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/kpi-score-references")
    public ResponseEntity<KpiScoreReference> createKpiScoreReference(@Valid @RequestBody KpiScoreReference kpiScoreReference)
        throws URISyntaxException {
        log.debug("REST request to save KpiScoreReference : {}", kpiScoreReference);
        if (kpiScoreReference.getId() != null) {
            throw new BadRequestAlertException("A new kpiScoreReference cannot already have an ID", ENTITY_NAME, "idexists");
        }
        KpiScoreReference result = kpiScoreReferenceRepository.save(kpiScoreReference);
        return ResponseEntity
            .created(new URI("/api/kpi-score-references/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /kpi-score-references} : Updates an existing kpiScoreReference.
     *
     * @param kpiScoreReference the kpiScoreReference to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated kpiScoreReference,
     * or with status {@code 400 (Bad Request)} if the kpiScoreReference is not valid,
     * or with status {@code 500 (Internal Server Error)} if the kpiScoreReference couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/kpi-score-references")
    public ResponseEntity<KpiScoreReference> updateKpiScoreReference(@Valid @RequestBody KpiScoreReference kpiScoreReference)
        throws URISyntaxException {
        log.debug("REST request to update KpiScoreReference : {}", kpiScoreReference);
        if (kpiScoreReference.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        KpiScoreReference result = kpiScoreReferenceRepository.save(kpiScoreReference);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, kpiScoreReference.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /kpi-score-references} : get all the kpiScoreReferences.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of kpiScoreReferences in body.
     */
    @GetMapping("/kpi-score-references")
    public ResponseEntity<List<KpiScoreReference>> getAllKpiScoreReferences(Pageable pageable) {
        log.debug("REST request to get a page of KpiScoreReferences");
        Page<KpiScoreReference> page = kpiScoreReferenceRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /kpi-score-references/:id} : get the "id" kpiScoreReference.
     *
     * @param id the id of the kpiScoreReference to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the kpiScoreReference, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/kpi-score-references/{id}")
    public ResponseEntity<KpiScoreReference> getKpiScoreReference(@PathVariable Long id) {
        log.debug("REST request to get KpiScoreReference : {}", id);
        Optional<KpiScoreReference> kpiScoreReference = kpiScoreReferenceRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(kpiScoreReference);
    }

    /**
     * {@code DELETE  /kpi-score-references/:id} : delete the "id" kpiScoreReference.
     *
     * @param id the id of the kpiScoreReference to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/kpi-score-references/{id}")
    public ResponseEntity<Void> deleteKpiScoreReference(@PathVariable Long id) {
        log.debug("REST request to delete KpiScoreReference : {}", id);
        kpiScoreReferenceRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
