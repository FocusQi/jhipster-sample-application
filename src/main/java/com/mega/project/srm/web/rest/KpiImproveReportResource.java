package com.mega.project.srm.web.rest;

import com.mega.project.srm.domain.KpiImproveReport;
import com.mega.project.srm.repository.KpiImproveReportRepository;
import com.mega.project.srm.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
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
 * REST controller for managing {@link com.mega.project.srm.domain.KpiImproveReport}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class KpiImproveReportResource {
    private final Logger log = LoggerFactory.getLogger(KpiImproveReportResource.class);

    private static final String ENTITY_NAME = "kpiImproveReport";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final KpiImproveReportRepository kpiImproveReportRepository;

    public KpiImproveReportResource(KpiImproveReportRepository kpiImproveReportRepository) {
        this.kpiImproveReportRepository = kpiImproveReportRepository;
    }

    /**
     * {@code POST  /kpi-improve-reports} : Create a new kpiImproveReport.
     *
     * @param kpiImproveReport the kpiImproveReport to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new kpiImproveReport, or with status {@code 400 (Bad Request)} if the kpiImproveReport has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/kpi-improve-reports")
    public ResponseEntity<KpiImproveReport> createKpiImproveReport(@Valid @RequestBody KpiImproveReport kpiImproveReport)
        throws URISyntaxException {
        log.debug("REST request to save KpiImproveReport : {}", kpiImproveReport);
        if (kpiImproveReport.getId() != null) {
            throw new BadRequestAlertException("A new kpiImproveReport cannot already have an ID", ENTITY_NAME, "idexists");
        }
        KpiImproveReport result = kpiImproveReportRepository.save(kpiImproveReport);
        return ResponseEntity
            .created(new URI("/api/kpi-improve-reports/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /kpi-improve-reports} : Updates an existing kpiImproveReport.
     *
     * @param kpiImproveReport the kpiImproveReport to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated kpiImproveReport,
     * or with status {@code 400 (Bad Request)} if the kpiImproveReport is not valid,
     * or with status {@code 500 (Internal Server Error)} if the kpiImproveReport couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/kpi-improve-reports")
    public ResponseEntity<KpiImproveReport> updateKpiImproveReport(@Valid @RequestBody KpiImproveReport kpiImproveReport)
        throws URISyntaxException {
        log.debug("REST request to update KpiImproveReport : {}", kpiImproveReport);
        if (kpiImproveReport.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        KpiImproveReport result = kpiImproveReportRepository.save(kpiImproveReport);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, kpiImproveReport.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /kpi-improve-reports} : get all the kpiImproveReports.
     *
     * @param pageable the pagination information.
     * @param filter the filter of the request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of kpiImproveReports in body.
     */
    @GetMapping("/kpi-improve-reports")
    public ResponseEntity<List<KpiImproveReport>> getAllKpiImproveReports(
        Pageable pageable,
        @RequestParam(required = false) String filter
    ) {
        if ("assgroupinfo-is-null".equals(filter)) {
            log.debug("REST request to get all KpiImproveReports where assGroupInfo is null");
            return new ResponseEntity<>(
                StreamSupport
                    .stream(kpiImproveReportRepository.findAll().spliterator(), false)
                    .filter(kpiImproveReport -> kpiImproveReport.getAssGroupInfo() == null)
                    .collect(Collectors.toList()),
                HttpStatus.OK
            );
        }
        log.debug("REST request to get a page of KpiImproveReports");
        Page<KpiImproveReport> page = kpiImproveReportRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /kpi-improve-reports/:id} : get the "id" kpiImproveReport.
     *
     * @param id the id of the kpiImproveReport to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the kpiImproveReport, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/kpi-improve-reports/{id}")
    public ResponseEntity<KpiImproveReport> getKpiImproveReport(@PathVariable Long id) {
        log.debug("REST request to get KpiImproveReport : {}", id);
        Optional<KpiImproveReport> kpiImproveReport = kpiImproveReportRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(kpiImproveReport);
    }

    /**
     * {@code DELETE  /kpi-improve-reports/:id} : delete the "id" kpiImproveReport.
     *
     * @param id the id of the kpiImproveReport to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/kpi-improve-reports/{id}")
    public ResponseEntity<Void> deleteKpiImproveReport(@PathVariable Long id) {
        log.debug("REST request to delete KpiImproveReport : {}", id);
        kpiImproveReportRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
