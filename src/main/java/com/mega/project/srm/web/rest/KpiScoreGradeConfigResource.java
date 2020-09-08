package com.mega.project.srm.web.rest;

import com.mega.project.srm.domain.KpiScoreGradeConfig;
import com.mega.project.srm.repository.KpiScoreGradeConfigRepository;
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
 * REST controller for managing {@link com.mega.project.srm.domain.KpiScoreGradeConfig}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class KpiScoreGradeConfigResource {
    private final Logger log = LoggerFactory.getLogger(KpiScoreGradeConfigResource.class);

    private static final String ENTITY_NAME = "kpiScoreGradeConfig";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final KpiScoreGradeConfigRepository kpiScoreGradeConfigRepository;

    public KpiScoreGradeConfigResource(KpiScoreGradeConfigRepository kpiScoreGradeConfigRepository) {
        this.kpiScoreGradeConfigRepository = kpiScoreGradeConfigRepository;
    }

    /**
     * {@code POST  /kpi-score-grade-configs} : Create a new kpiScoreGradeConfig.
     *
     * @param kpiScoreGradeConfig the kpiScoreGradeConfig to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new kpiScoreGradeConfig, or with status {@code 400 (Bad Request)} if the kpiScoreGradeConfig has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/kpi-score-grade-configs")
    public ResponseEntity<KpiScoreGradeConfig> createKpiScoreGradeConfig(@Valid @RequestBody KpiScoreGradeConfig kpiScoreGradeConfig)
        throws URISyntaxException {
        log.debug("REST request to save KpiScoreGradeConfig : {}", kpiScoreGradeConfig);
        if (kpiScoreGradeConfig.getId() != null) {
            throw new BadRequestAlertException("A new kpiScoreGradeConfig cannot already have an ID", ENTITY_NAME, "idexists");
        }
        KpiScoreGradeConfig result = kpiScoreGradeConfigRepository.save(kpiScoreGradeConfig);
        return ResponseEntity
            .created(new URI("/api/kpi-score-grade-configs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /kpi-score-grade-configs} : Updates an existing kpiScoreGradeConfig.
     *
     * @param kpiScoreGradeConfig the kpiScoreGradeConfig to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated kpiScoreGradeConfig,
     * or with status {@code 400 (Bad Request)} if the kpiScoreGradeConfig is not valid,
     * or with status {@code 500 (Internal Server Error)} if the kpiScoreGradeConfig couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/kpi-score-grade-configs")
    public ResponseEntity<KpiScoreGradeConfig> updateKpiScoreGradeConfig(@Valid @RequestBody KpiScoreGradeConfig kpiScoreGradeConfig)
        throws URISyntaxException {
        log.debug("REST request to update KpiScoreGradeConfig : {}", kpiScoreGradeConfig);
        if (kpiScoreGradeConfig.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        KpiScoreGradeConfig result = kpiScoreGradeConfigRepository.save(kpiScoreGradeConfig);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, kpiScoreGradeConfig.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /kpi-score-grade-configs} : get all the kpiScoreGradeConfigs.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of kpiScoreGradeConfigs in body.
     */
    @GetMapping("/kpi-score-grade-configs")
    public ResponseEntity<List<KpiScoreGradeConfig>> getAllKpiScoreGradeConfigs(Pageable pageable) {
        log.debug("REST request to get a page of KpiScoreGradeConfigs");
        Page<KpiScoreGradeConfig> page = kpiScoreGradeConfigRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /kpi-score-grade-configs/:id} : get the "id" kpiScoreGradeConfig.
     *
     * @param id the id of the kpiScoreGradeConfig to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the kpiScoreGradeConfig, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/kpi-score-grade-configs/{id}")
    public ResponseEntity<KpiScoreGradeConfig> getKpiScoreGradeConfig(@PathVariable Long id) {
        log.debug("REST request to get KpiScoreGradeConfig : {}", id);
        Optional<KpiScoreGradeConfig> kpiScoreGradeConfig = kpiScoreGradeConfigRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(kpiScoreGradeConfig);
    }

    /**
     * {@code DELETE  /kpi-score-grade-configs/:id} : delete the "id" kpiScoreGradeConfig.
     *
     * @param id the id of the kpiScoreGradeConfig to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/kpi-score-grade-configs/{id}")
    public ResponseEntity<Void> deleteKpiScoreGradeConfig(@PathVariable Long id) {
        log.debug("REST request to delete KpiScoreGradeConfig : {}", id);
        kpiScoreGradeConfigRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
