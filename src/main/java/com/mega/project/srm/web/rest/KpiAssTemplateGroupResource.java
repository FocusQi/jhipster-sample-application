package com.mega.project.srm.web.rest;

import com.mega.project.srm.domain.KpiAssTemplateGroup;
import com.mega.project.srm.repository.KpiAssTemplateGroupRepository;
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
 * REST controller for managing {@link com.mega.project.srm.domain.KpiAssTemplateGroup}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class KpiAssTemplateGroupResource {
    private final Logger log = LoggerFactory.getLogger(KpiAssTemplateGroupResource.class);

    private static final String ENTITY_NAME = "kpiAssTemplateGroup";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final KpiAssTemplateGroupRepository kpiAssTemplateGroupRepository;

    public KpiAssTemplateGroupResource(KpiAssTemplateGroupRepository kpiAssTemplateGroupRepository) {
        this.kpiAssTemplateGroupRepository = kpiAssTemplateGroupRepository;
    }

    /**
     * {@code POST  /kpi-ass-template-groups} : Create a new kpiAssTemplateGroup.
     *
     * @param kpiAssTemplateGroup the kpiAssTemplateGroup to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new kpiAssTemplateGroup, or with status {@code 400 (Bad Request)} if the kpiAssTemplateGroup has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/kpi-ass-template-groups")
    public ResponseEntity<KpiAssTemplateGroup> createKpiAssTemplateGroup(@Valid @RequestBody KpiAssTemplateGroup kpiAssTemplateGroup)
        throws URISyntaxException {
        log.debug("REST request to save KpiAssTemplateGroup : {}", kpiAssTemplateGroup);
        if (kpiAssTemplateGroup.getId() != null) {
            throw new BadRequestAlertException("A new kpiAssTemplateGroup cannot already have an ID", ENTITY_NAME, "idexists");
        }
        KpiAssTemplateGroup result = kpiAssTemplateGroupRepository.save(kpiAssTemplateGroup);
        return ResponseEntity
            .created(new URI("/api/kpi-ass-template-groups/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /kpi-ass-template-groups} : Updates an existing kpiAssTemplateGroup.
     *
     * @param kpiAssTemplateGroup the kpiAssTemplateGroup to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated kpiAssTemplateGroup,
     * or with status {@code 400 (Bad Request)} if the kpiAssTemplateGroup is not valid,
     * or with status {@code 500 (Internal Server Error)} if the kpiAssTemplateGroup couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/kpi-ass-template-groups")
    public ResponseEntity<KpiAssTemplateGroup> updateKpiAssTemplateGroup(@Valid @RequestBody KpiAssTemplateGroup kpiAssTemplateGroup)
        throws URISyntaxException {
        log.debug("REST request to update KpiAssTemplateGroup : {}", kpiAssTemplateGroup);
        if (kpiAssTemplateGroup.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        KpiAssTemplateGroup result = kpiAssTemplateGroupRepository.save(kpiAssTemplateGroup);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, kpiAssTemplateGroup.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /kpi-ass-template-groups} : get all the kpiAssTemplateGroups.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of kpiAssTemplateGroups in body.
     */
    @GetMapping("/kpi-ass-template-groups")
    public ResponseEntity<List<KpiAssTemplateGroup>> getAllKpiAssTemplateGroups(Pageable pageable) {
        log.debug("REST request to get a page of KpiAssTemplateGroups");
        Page<KpiAssTemplateGroup> page = kpiAssTemplateGroupRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /kpi-ass-template-groups/:id} : get the "id" kpiAssTemplateGroup.
     *
     * @param id the id of the kpiAssTemplateGroup to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the kpiAssTemplateGroup, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/kpi-ass-template-groups/{id}")
    public ResponseEntity<KpiAssTemplateGroup> getKpiAssTemplateGroup(@PathVariable Long id) {
        log.debug("REST request to get KpiAssTemplateGroup : {}", id);
        Optional<KpiAssTemplateGroup> kpiAssTemplateGroup = kpiAssTemplateGroupRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(kpiAssTemplateGroup);
    }

    /**
     * {@code DELETE  /kpi-ass-template-groups/:id} : delete the "id" kpiAssTemplateGroup.
     *
     * @param id the id of the kpiAssTemplateGroup to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/kpi-ass-template-groups/{id}")
    public ResponseEntity<Void> deleteKpiAssTemplateGroup(@PathVariable Long id) {
        log.debug("REST request to delete KpiAssTemplateGroup : {}", id);
        kpiAssTemplateGroupRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
