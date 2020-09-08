package com.mega.project.srm.web.rest;

import com.mega.project.srm.domain.KpiTemplateGroupInfo;
import com.mega.project.srm.repository.KpiTemplateGroupInfoRepository;
import com.mega.project.srm.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
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
 * REST controller for managing {@link com.mega.project.srm.domain.KpiTemplateGroupInfo}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class KpiTemplateGroupInfoResource {
    private final Logger log = LoggerFactory.getLogger(KpiTemplateGroupInfoResource.class);

    private static final String ENTITY_NAME = "kpiTemplateGroupInfo";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final KpiTemplateGroupInfoRepository kpiTemplateGroupInfoRepository;

    public KpiTemplateGroupInfoResource(KpiTemplateGroupInfoRepository kpiTemplateGroupInfoRepository) {
        this.kpiTemplateGroupInfoRepository = kpiTemplateGroupInfoRepository;
    }

    /**
     * {@code POST  /kpi-template-group-infos} : Create a new kpiTemplateGroupInfo.
     *
     * @param kpiTemplateGroupInfo the kpiTemplateGroupInfo to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new kpiTemplateGroupInfo, or with status {@code 400 (Bad Request)} if the kpiTemplateGroupInfo has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/kpi-template-group-infos")
    public ResponseEntity<KpiTemplateGroupInfo> createKpiTemplateGroupInfo(@RequestBody KpiTemplateGroupInfo kpiTemplateGroupInfo)
        throws URISyntaxException {
        log.debug("REST request to save KpiTemplateGroupInfo : {}", kpiTemplateGroupInfo);
        if (kpiTemplateGroupInfo.getId() != null) {
            throw new BadRequestAlertException("A new kpiTemplateGroupInfo cannot already have an ID", ENTITY_NAME, "idexists");
        }
        KpiTemplateGroupInfo result = kpiTemplateGroupInfoRepository.save(kpiTemplateGroupInfo);
        return ResponseEntity
            .created(new URI("/api/kpi-template-group-infos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /kpi-template-group-infos} : Updates an existing kpiTemplateGroupInfo.
     *
     * @param kpiTemplateGroupInfo the kpiTemplateGroupInfo to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated kpiTemplateGroupInfo,
     * or with status {@code 400 (Bad Request)} if the kpiTemplateGroupInfo is not valid,
     * or with status {@code 500 (Internal Server Error)} if the kpiTemplateGroupInfo couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/kpi-template-group-infos")
    public ResponseEntity<KpiTemplateGroupInfo> updateKpiTemplateGroupInfo(@RequestBody KpiTemplateGroupInfo kpiTemplateGroupInfo)
        throws URISyntaxException {
        log.debug("REST request to update KpiTemplateGroupInfo : {}", kpiTemplateGroupInfo);
        if (kpiTemplateGroupInfo.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        KpiTemplateGroupInfo result = kpiTemplateGroupInfoRepository.save(kpiTemplateGroupInfo);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, kpiTemplateGroupInfo.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /kpi-template-group-infos} : get all the kpiTemplateGroupInfos.
     *
     * @param pageable the pagination information.
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many).
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of kpiTemplateGroupInfos in body.
     */
    @GetMapping("/kpi-template-group-infos")
    public ResponseEntity<List<KpiTemplateGroupInfo>> getAllKpiTemplateGroupInfos(
        Pageable pageable,
        @RequestParam(required = false, defaultValue = "false") boolean eagerload
    ) {
        log.debug("REST request to get a page of KpiTemplateGroupInfos");
        Page<KpiTemplateGroupInfo> page;
        if (eagerload) {
            page = kpiTemplateGroupInfoRepository.findAllWithEagerRelationships(pageable);
        } else {
            page = kpiTemplateGroupInfoRepository.findAll(pageable);
        }
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /kpi-template-group-infos/:id} : get the "id" kpiTemplateGroupInfo.
     *
     * @param id the id of the kpiTemplateGroupInfo to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the kpiTemplateGroupInfo, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/kpi-template-group-infos/{id}")
    public ResponseEntity<KpiTemplateGroupInfo> getKpiTemplateGroupInfo(@PathVariable Long id) {
        log.debug("REST request to get KpiTemplateGroupInfo : {}", id);
        Optional<KpiTemplateGroupInfo> kpiTemplateGroupInfo = kpiTemplateGroupInfoRepository.findOneWithEagerRelationships(id);
        return ResponseUtil.wrapOrNotFound(kpiTemplateGroupInfo);
    }

    /**
     * {@code DELETE  /kpi-template-group-infos/:id} : delete the "id" kpiTemplateGroupInfo.
     *
     * @param id the id of the kpiTemplateGroupInfo to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/kpi-template-group-infos/{id}")
    public ResponseEntity<Void> deleteKpiTemplateGroupInfo(@PathVariable Long id) {
        log.debug("REST request to delete KpiTemplateGroupInfo : {}", id);
        kpiTemplateGroupInfoRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
