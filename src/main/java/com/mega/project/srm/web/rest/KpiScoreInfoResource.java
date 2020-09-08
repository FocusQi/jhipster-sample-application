package com.mega.project.srm.web.rest;

import com.mega.project.srm.domain.KpiScoreInfo;
import com.mega.project.srm.repository.KpiScoreInfoRepository;
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
 * REST controller for managing {@link com.mega.project.srm.domain.KpiScoreInfo}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class KpiScoreInfoResource {
    private final Logger log = LoggerFactory.getLogger(KpiScoreInfoResource.class);

    private static final String ENTITY_NAME = "kpiScoreInfo";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final KpiScoreInfoRepository kpiScoreInfoRepository;

    public KpiScoreInfoResource(KpiScoreInfoRepository kpiScoreInfoRepository) {
        this.kpiScoreInfoRepository = kpiScoreInfoRepository;
    }

    /**
     * {@code POST  /kpi-score-infos} : Create a new kpiScoreInfo.
     *
     * @param kpiScoreInfo the kpiScoreInfo to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new kpiScoreInfo, or with status {@code 400 (Bad Request)} if the kpiScoreInfo has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/kpi-score-infos")
    public ResponseEntity<KpiScoreInfo> createKpiScoreInfo(@Valid @RequestBody KpiScoreInfo kpiScoreInfo) throws URISyntaxException {
        log.debug("REST request to save KpiScoreInfo : {}", kpiScoreInfo);
        if (kpiScoreInfo.getId() != null) {
            throw new BadRequestAlertException("A new kpiScoreInfo cannot already have an ID", ENTITY_NAME, "idexists");
        }
        KpiScoreInfo result = kpiScoreInfoRepository.save(kpiScoreInfo);
        return ResponseEntity
            .created(new URI("/api/kpi-score-infos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /kpi-score-infos} : Updates an existing kpiScoreInfo.
     *
     * @param kpiScoreInfo the kpiScoreInfo to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated kpiScoreInfo,
     * or with status {@code 400 (Bad Request)} if the kpiScoreInfo is not valid,
     * or with status {@code 500 (Internal Server Error)} if the kpiScoreInfo couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/kpi-score-infos")
    public ResponseEntity<KpiScoreInfo> updateKpiScoreInfo(@Valid @RequestBody KpiScoreInfo kpiScoreInfo) throws URISyntaxException {
        log.debug("REST request to update KpiScoreInfo : {}", kpiScoreInfo);
        if (kpiScoreInfo.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        KpiScoreInfo result = kpiScoreInfoRepository.save(kpiScoreInfo);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, kpiScoreInfo.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /kpi-score-infos} : get all the kpiScoreInfos.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of kpiScoreInfos in body.
     */
    @GetMapping("/kpi-score-infos")
    public ResponseEntity<List<KpiScoreInfo>> getAllKpiScoreInfos(Pageable pageable) {
        log.debug("REST request to get a page of KpiScoreInfos");
        Page<KpiScoreInfo> page = kpiScoreInfoRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /kpi-score-infos/:id} : get the "id" kpiScoreInfo.
     *
     * @param id the id of the kpiScoreInfo to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the kpiScoreInfo, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/kpi-score-infos/{id}")
    public ResponseEntity<KpiScoreInfo> getKpiScoreInfo(@PathVariable Long id) {
        log.debug("REST request to get KpiScoreInfo : {}", id);
        Optional<KpiScoreInfo> kpiScoreInfo = kpiScoreInfoRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(kpiScoreInfo);
    }

    /**
     * {@code DELETE  /kpi-score-infos/:id} : delete the "id" kpiScoreInfo.
     *
     * @param id the id of the kpiScoreInfo to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/kpi-score-infos/{id}")
    public ResponseEntity<Void> deleteKpiScoreInfo(@PathVariable Long id) {
        log.debug("REST request to delete KpiScoreInfo : {}", id);
        kpiScoreInfoRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
