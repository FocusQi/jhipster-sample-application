package com.mega.project.srm.web.rest;

import com.mega.project.srm.domain.KpiImproveReportInfo;
import com.mega.project.srm.repository.KpiImproveReportInfoRepository;
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
 * REST controller for managing {@link com.mega.project.srm.domain.KpiImproveReportInfo}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class KpiImproveReportInfoResource {
    private final Logger log = LoggerFactory.getLogger(KpiImproveReportInfoResource.class);

    private static final String ENTITY_NAME = "kpiImproveReportInfo";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final KpiImproveReportInfoRepository kpiImproveReportInfoRepository;

    public KpiImproveReportInfoResource(KpiImproveReportInfoRepository kpiImproveReportInfoRepository) {
        this.kpiImproveReportInfoRepository = kpiImproveReportInfoRepository;
    }

    /**
     * {@code POST  /kpi-improve-report-infos} : Create a new kpiImproveReportInfo.
     *
     * @param kpiImproveReportInfo the kpiImproveReportInfo to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new kpiImproveReportInfo, or with status {@code 400 (Bad Request)} if the kpiImproveReportInfo has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/kpi-improve-report-infos")
    public ResponseEntity<KpiImproveReportInfo> createKpiImproveReportInfo(@RequestBody KpiImproveReportInfo kpiImproveReportInfo)
        throws URISyntaxException {
        log.debug("REST request to save KpiImproveReportInfo : {}", kpiImproveReportInfo);
        if (kpiImproveReportInfo.getId() != null) {
            throw new BadRequestAlertException("A new kpiImproveReportInfo cannot already have an ID", ENTITY_NAME, "idexists");
        }
        KpiImproveReportInfo result = kpiImproveReportInfoRepository.save(kpiImproveReportInfo);
        return ResponseEntity
            .created(new URI("/api/kpi-improve-report-infos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /kpi-improve-report-infos} : Updates an existing kpiImproveReportInfo.
     *
     * @param kpiImproveReportInfo the kpiImproveReportInfo to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated kpiImproveReportInfo,
     * or with status {@code 400 (Bad Request)} if the kpiImproveReportInfo is not valid,
     * or with status {@code 500 (Internal Server Error)} if the kpiImproveReportInfo couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/kpi-improve-report-infos")
    public ResponseEntity<KpiImproveReportInfo> updateKpiImproveReportInfo(@RequestBody KpiImproveReportInfo kpiImproveReportInfo)
        throws URISyntaxException {
        log.debug("REST request to update KpiImproveReportInfo : {}", kpiImproveReportInfo);
        if (kpiImproveReportInfo.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        KpiImproveReportInfo result = kpiImproveReportInfoRepository.save(kpiImproveReportInfo);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, kpiImproveReportInfo.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /kpi-improve-report-infos} : get all the kpiImproveReportInfos.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of kpiImproveReportInfos in body.
     */
    @GetMapping("/kpi-improve-report-infos")
    public ResponseEntity<List<KpiImproveReportInfo>> getAllKpiImproveReportInfos(Pageable pageable) {
        log.debug("REST request to get a page of KpiImproveReportInfos");
        Page<KpiImproveReportInfo> page = kpiImproveReportInfoRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /kpi-improve-report-infos/:id} : get the "id" kpiImproveReportInfo.
     *
     * @param id the id of the kpiImproveReportInfo to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the kpiImproveReportInfo, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/kpi-improve-report-infos/{id}")
    public ResponseEntity<KpiImproveReportInfo> getKpiImproveReportInfo(@PathVariable Long id) {
        log.debug("REST request to get KpiImproveReportInfo : {}", id);
        Optional<KpiImproveReportInfo> kpiImproveReportInfo = kpiImproveReportInfoRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(kpiImproveReportInfo);
    }

    /**
     * {@code DELETE  /kpi-improve-report-infos/:id} : delete the "id" kpiImproveReportInfo.
     *
     * @param id the id of the kpiImproveReportInfo to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/kpi-improve-report-infos/{id}")
    public ResponseEntity<Void> deleteKpiImproveReportInfo(@PathVariable Long id) {
        log.debug("REST request to delete KpiImproveReportInfo : {}", id);
        kpiImproveReportInfoRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
