package com.mega.project.srm.web.rest;

import com.mega.project.srm.domain.KpiAssGroup;
import com.mega.project.srm.repository.KpiAssGroupRepository;
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
 * REST controller for managing {@link com.mega.project.srm.domain.KpiAssGroup}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class KpiAssGroupResource {
    private final Logger log = LoggerFactory.getLogger(KpiAssGroupResource.class);

    private static final String ENTITY_NAME = "kpiAssGroup";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final KpiAssGroupRepository kpiAssGroupRepository;

    public KpiAssGroupResource(KpiAssGroupRepository kpiAssGroupRepository) {
        this.kpiAssGroupRepository = kpiAssGroupRepository;
    }

    /**
     * {@code POST  /kpi-ass-groups} : Create a new kpiAssGroup.
     *
     * @param kpiAssGroup the kpiAssGroup to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new kpiAssGroup, or with status {@code 400 (Bad Request)} if the kpiAssGroup has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/kpi-ass-groups")
    public ResponseEntity<KpiAssGroup> createKpiAssGroup(@Valid @RequestBody KpiAssGroup kpiAssGroup) throws URISyntaxException {
        log.debug("REST request to save KpiAssGroup : {}", kpiAssGroup);
        if (kpiAssGroup.getId() != null) {
            throw new BadRequestAlertException("A new kpiAssGroup cannot already have an ID", ENTITY_NAME, "idexists");
        }
        KpiAssGroup result = kpiAssGroupRepository.save(kpiAssGroup);
        return ResponseEntity
            .created(new URI("/api/kpi-ass-groups/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /kpi-ass-groups} : Updates an existing kpiAssGroup.
     *
     * @param kpiAssGroup the kpiAssGroup to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated kpiAssGroup,
     * or with status {@code 400 (Bad Request)} if the kpiAssGroup is not valid,
     * or with status {@code 500 (Internal Server Error)} if the kpiAssGroup couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/kpi-ass-groups")
    public ResponseEntity<KpiAssGroup> updateKpiAssGroup(@Valid @RequestBody KpiAssGroup kpiAssGroup) throws URISyntaxException {
        log.debug("REST request to update KpiAssGroup : {}", kpiAssGroup);
        if (kpiAssGroup.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        KpiAssGroup result = kpiAssGroupRepository.save(kpiAssGroup);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, kpiAssGroup.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /kpi-ass-groups} : get all the kpiAssGroups.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of kpiAssGroups in body.
     */
    @GetMapping("/kpi-ass-groups")
    public ResponseEntity<List<KpiAssGroup>> getAllKpiAssGroups(Pageable pageable) {
        log.debug("REST request to get a page of KpiAssGroups");
        Page<KpiAssGroup> page = kpiAssGroupRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /kpi-ass-groups/:id} : get the "id" kpiAssGroup.
     *
     * @param id the id of the kpiAssGroup to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the kpiAssGroup, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/kpi-ass-groups/{id}")
    public ResponseEntity<KpiAssGroup> getKpiAssGroup(@PathVariable Long id) {
        log.debug("REST request to get KpiAssGroup : {}", id);
        Optional<KpiAssGroup> kpiAssGroup = kpiAssGroupRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(kpiAssGroup);
    }

    /**
     * {@code DELETE  /kpi-ass-groups/:id} : delete the "id" kpiAssGroup.
     *
     * @param id the id of the kpiAssGroup to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/kpi-ass-groups/{id}")
    public ResponseEntity<Void> deleteKpiAssGroup(@PathVariable Long id) {
        log.debug("REST request to delete KpiAssGroup : {}", id);
        kpiAssGroupRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
