package com.mega.project.srm.web.rest;

import com.mega.project.srm.domain.KpiTemplateGroup;
import com.mega.project.srm.repository.KpiTemplateGroupRepository;
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
 * REST controller for managing {@link com.mega.project.srm.domain.KpiTemplateGroup}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class KpiTemplateGroupResource {
    private final Logger log = LoggerFactory.getLogger(KpiTemplateGroupResource.class);

    private static final String ENTITY_NAME = "kpiTemplateGroup";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final KpiTemplateGroupRepository kpiTemplateGroupRepository;

    public KpiTemplateGroupResource(KpiTemplateGroupRepository kpiTemplateGroupRepository) {
        this.kpiTemplateGroupRepository = kpiTemplateGroupRepository;
    }

    /**
     * {@code POST  /kpi-template-groups} : Create a new kpiTemplateGroup.
     *
     * @param kpiTemplateGroup the kpiTemplateGroup to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new kpiTemplateGroup, or with status {@code 400 (Bad Request)} if the kpiTemplateGroup has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/kpi-template-groups")
    public ResponseEntity<KpiTemplateGroup> createKpiTemplateGroup(@Valid @RequestBody KpiTemplateGroup kpiTemplateGroup)
        throws URISyntaxException {
        log.debug("REST request to save KpiTemplateGroup : {}", kpiTemplateGroup);
        if (kpiTemplateGroup.getId() != null) {
            throw new BadRequestAlertException("A new kpiTemplateGroup cannot already have an ID", ENTITY_NAME, "idexists");
        }
        KpiTemplateGroup result = kpiTemplateGroupRepository.save(kpiTemplateGroup);
        return ResponseEntity
            .created(new URI("/api/kpi-template-groups/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /kpi-template-groups} : Updates an existing kpiTemplateGroup.
     *
     * @param kpiTemplateGroup the kpiTemplateGroup to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated kpiTemplateGroup,
     * or with status {@code 400 (Bad Request)} if the kpiTemplateGroup is not valid,
     * or with status {@code 500 (Internal Server Error)} if the kpiTemplateGroup couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/kpi-template-groups")
    public ResponseEntity<KpiTemplateGroup> updateKpiTemplateGroup(@Valid @RequestBody KpiTemplateGroup kpiTemplateGroup)
        throws URISyntaxException {
        log.debug("REST request to update KpiTemplateGroup : {}", kpiTemplateGroup);
        if (kpiTemplateGroup.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        KpiTemplateGroup result = kpiTemplateGroupRepository.save(kpiTemplateGroup);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, kpiTemplateGroup.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /kpi-template-groups} : get all the kpiTemplateGroups.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of kpiTemplateGroups in body.
     */
    @GetMapping("/kpi-template-groups")
    public ResponseEntity<List<KpiTemplateGroup>> getAllKpiTemplateGroups(Pageable pageable) {
        log.debug("REST request to get a page of KpiTemplateGroups");
        Page<KpiTemplateGroup> page = kpiTemplateGroupRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /kpi-template-groups/:id} : get the "id" kpiTemplateGroup.
     *
     * @param id the id of the kpiTemplateGroup to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the kpiTemplateGroup, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/kpi-template-groups/{id}")
    public ResponseEntity<KpiTemplateGroup> getKpiTemplateGroup(@PathVariable Long id) {
        log.debug("REST request to get KpiTemplateGroup : {}", id);
        Optional<KpiTemplateGroup> kpiTemplateGroup = kpiTemplateGroupRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(kpiTemplateGroup);
    }

    /**
     * {@code DELETE  /kpi-template-groups/:id} : delete the "id" kpiTemplateGroup.
     *
     * @param id the id of the kpiTemplateGroup to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/kpi-template-groups/{id}")
    public ResponseEntity<Void> deleteKpiTemplateGroup(@PathVariable Long id) {
        log.debug("REST request to delete KpiTemplateGroup : {}", id);
        kpiTemplateGroupRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
