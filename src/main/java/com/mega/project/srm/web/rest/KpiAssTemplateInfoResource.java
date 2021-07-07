package com.mega.project.srm.web.rest;

import com.mega.project.srm.domain.KpiAssTemplateInfo;
import com.mega.project.srm.repository.KpiAssTemplateInfoRepository;
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
 * REST controller for managing {@link com.mega.project.srm.domain.KpiAssTemplateInfo}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class KpiAssTemplateInfoResource {
    private final Logger log = LoggerFactory.getLogger(KpiAssTemplateInfoResource.class);

    private static final String ENTITY_NAME = "kpiAssTemplateInfo";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final KpiAssTemplateInfoRepository kpiAssTemplateInfoRepository;

    public KpiAssTemplateInfoResource(KpiAssTemplateInfoRepository kpiAssTemplateInfoRepository) {
        this.kpiAssTemplateInfoRepository = kpiAssTemplateInfoRepository;
    }

    /**
     * {@code POST  /kpi-ass-template-infos} : Create a new kpiAssTemplateInfo.
     *
     * @param kpiAssTemplateInfo the kpiAssTemplateInfo to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new kpiAssTemplateInfo, or with status {@code 400 (Bad Request)} if the kpiAssTemplateInfo has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/kpi-ass-template-infos")
    public ResponseEntity<KpiAssTemplateInfo> createKpiAssTemplateInfo(@Valid @RequestBody KpiAssTemplateInfo kpiAssTemplateInfo)
        throws URISyntaxException {
        log.debug("REST request to save KpiAssTemplateInfo : {}", kpiAssTemplateInfo);
        if (kpiAssTemplateInfo.getId() != null) {
            throw new BadRequestAlertException("A new kpiAssTemplateInfo cannot already have an ID", ENTITY_NAME, "idexists");
        }
        KpiAssTemplateInfo result = kpiAssTemplateInfoRepository.save(kpiAssTemplateInfo);
        return ResponseEntity
            .created(new URI("/api/kpi-ass-template-infos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /kpi-ass-template-infos} : Updates an existing kpiAssTemplateInfo.
     *
     * @param kpiAssTemplateInfo the kpiAssTemplateInfo to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated kpiAssTemplateInfo,
     * or with status {@code 400 (Bad Request)} if the kpiAssTemplateInfo is not valid,
     * or with status {@code 500 (Internal Server Error)} if the kpiAssTemplateInfo couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/kpi-ass-template-infos")
    public ResponseEntity<KpiAssTemplateInfo> updateKpiAssTemplateInfo(@Valid @RequestBody KpiAssTemplateInfo kpiAssTemplateInfo)
        throws URISyntaxException {
        log.debug("REST request to update KpiAssTemplateInfo : {}", kpiAssTemplateInfo);
        if (kpiAssTemplateInfo.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        KpiAssTemplateInfo result = kpiAssTemplateInfoRepository.save(kpiAssTemplateInfo);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, kpiAssTemplateInfo.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /kpi-ass-template-infos} : get all the kpiAssTemplateInfos.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of kpiAssTemplateInfos in body.
     */
    @GetMapping("/kpi-ass-template-infos")
    public ResponseEntity<List<KpiAssTemplateInfo>> getAllKpiAssTemplateInfos(Pageable pageable) {
        log.debug("REST request to get a page of KpiAssTemplateInfos");
        Page<KpiAssTemplateInfo> page = kpiAssTemplateInfoRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /kpi-ass-template-infos/:id} : get the "id" kpiAssTemplateInfo.
     *
     * @param id the id of the kpiAssTemplateInfo to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the kpiAssTemplateInfo, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/kpi-ass-template-infos/{id}")
    public ResponseEntity<KpiAssTemplateInfo> getKpiAssTemplateInfo(@PathVariable Long id) {
        log.debug("REST request to get KpiAssTemplateInfo : {}", id);
        Optional<KpiAssTemplateInfo> kpiAssTemplateInfo = kpiAssTemplateInfoRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(kpiAssTemplateInfo);
    }

    /**
     * {@code DELETE  /kpi-ass-template-infos/:id} : delete the "id" kpiAssTemplateInfo.
     *
     * @param id the id of the kpiAssTemplateInfo to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/kpi-ass-template-infos/{id}")
    public ResponseEntity<Void> deleteKpiAssTemplateInfo(@PathVariable Long id) {
        log.debug("REST request to delete KpiAssTemplateInfo : {}", id);
        kpiAssTemplateInfoRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
