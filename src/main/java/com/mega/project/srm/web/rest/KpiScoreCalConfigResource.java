package com.mega.project.srm.web.rest;

import com.mega.project.srm.domain.KpiScoreCalConfig;
import com.mega.project.srm.repository.KpiScoreCalConfigRepository;
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
 * REST controller for managing {@link com.mega.project.srm.domain.KpiScoreCalConfig}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class KpiScoreCalConfigResource {
    private final Logger log = LoggerFactory.getLogger(KpiScoreCalConfigResource.class);

    private static final String ENTITY_NAME = "kpiScoreCalConfig";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final KpiScoreCalConfigRepository kpiScoreCalConfigRepository;

    public KpiScoreCalConfigResource(KpiScoreCalConfigRepository kpiScoreCalConfigRepository) {
        this.kpiScoreCalConfigRepository = kpiScoreCalConfigRepository;
    }

    /**
     * {@code POST  /kpi-score-cal-configs} : Create a new kpiScoreCalConfig.
     *
     * @param kpiScoreCalConfig the kpiScoreCalConfig to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new kpiScoreCalConfig, or with status {@code 400 (Bad Request)} if the kpiScoreCalConfig has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/kpi-score-cal-configs")
    public ResponseEntity<KpiScoreCalConfig> createKpiScoreCalConfig(@Valid @RequestBody KpiScoreCalConfig kpiScoreCalConfig)
        throws URISyntaxException {
        log.debug("REST request to save KpiScoreCalConfig : {}", kpiScoreCalConfig);
        if (kpiScoreCalConfig.getId() != null) {
            throw new BadRequestAlertException("A new kpiScoreCalConfig cannot already have an ID", ENTITY_NAME, "idexists");
        }
        KpiScoreCalConfig result = kpiScoreCalConfigRepository.save(kpiScoreCalConfig);
        return ResponseEntity
            .created(new URI("/api/kpi-score-cal-configs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /kpi-score-cal-configs} : Updates an existing kpiScoreCalConfig.
     *
     * @param kpiScoreCalConfig the kpiScoreCalConfig to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated kpiScoreCalConfig,
     * or with status {@code 400 (Bad Request)} if the kpiScoreCalConfig is not valid,
     * or with status {@code 500 (Internal Server Error)} if the kpiScoreCalConfig couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/kpi-score-cal-configs")
    public ResponseEntity<KpiScoreCalConfig> updateKpiScoreCalConfig(@Valid @RequestBody KpiScoreCalConfig kpiScoreCalConfig)
        throws URISyntaxException {
        log.debug("REST request to update KpiScoreCalConfig : {}", kpiScoreCalConfig);
        if (kpiScoreCalConfig.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        KpiScoreCalConfig result = kpiScoreCalConfigRepository.save(kpiScoreCalConfig);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, kpiScoreCalConfig.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /kpi-score-cal-configs} : get all the kpiScoreCalConfigs.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of kpiScoreCalConfigs in body.
     */
    @GetMapping("/kpi-score-cal-configs")
    public ResponseEntity<List<KpiScoreCalConfig>> getAllKpiScoreCalConfigs(Pageable pageable) {
        log.debug("REST request to get a page of KpiScoreCalConfigs");
        Page<KpiScoreCalConfig> page = kpiScoreCalConfigRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /kpi-score-cal-configs/:id} : get the "id" kpiScoreCalConfig.
     *
     * @param id the id of the kpiScoreCalConfig to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the kpiScoreCalConfig, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/kpi-score-cal-configs/{id}")
    public ResponseEntity<KpiScoreCalConfig> getKpiScoreCalConfig(@PathVariable Long id) {
        log.debug("REST request to get KpiScoreCalConfig : {}", id);
        Optional<KpiScoreCalConfig> kpiScoreCalConfig = kpiScoreCalConfigRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(kpiScoreCalConfig);
    }

    /**
     * {@code DELETE  /kpi-score-cal-configs/:id} : delete the "id" kpiScoreCalConfig.
     *
     * @param id the id of the kpiScoreCalConfig to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/kpi-score-cal-configs/{id}")
    public ResponseEntity<Void> deleteKpiScoreCalConfig(@PathVariable Long id) {
        log.debug("REST request to delete KpiScoreCalConfig : {}", id);
        kpiScoreCalConfigRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
