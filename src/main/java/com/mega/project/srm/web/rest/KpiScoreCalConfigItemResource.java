package com.mega.project.srm.web.rest;

import com.mega.project.srm.domain.KpiScoreCalConfigItem;
import com.mega.project.srm.repository.KpiScoreCalConfigItemRepository;
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
 * REST controller for managing {@link com.mega.project.srm.domain.KpiScoreCalConfigItem}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class KpiScoreCalConfigItemResource {
    private final Logger log = LoggerFactory.getLogger(KpiScoreCalConfigItemResource.class);

    private static final String ENTITY_NAME = "kpiScoreCalConfigItem";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final KpiScoreCalConfigItemRepository kpiScoreCalConfigItemRepository;

    public KpiScoreCalConfigItemResource(KpiScoreCalConfigItemRepository kpiScoreCalConfigItemRepository) {
        this.kpiScoreCalConfigItemRepository = kpiScoreCalConfigItemRepository;
    }

    /**
     * {@code POST  /kpi-score-cal-config-items} : Create a new kpiScoreCalConfigItem.
     *
     * @param kpiScoreCalConfigItem the kpiScoreCalConfigItem to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new kpiScoreCalConfigItem, or with status {@code 400 (Bad Request)} if the kpiScoreCalConfigItem has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/kpi-score-cal-config-items")
    public ResponseEntity<KpiScoreCalConfigItem> createKpiScoreCalConfigItem(@RequestBody KpiScoreCalConfigItem kpiScoreCalConfigItem)
        throws URISyntaxException {
        log.debug("REST request to save KpiScoreCalConfigItem : {}", kpiScoreCalConfigItem);
        if (kpiScoreCalConfigItem.getId() != null) {
            throw new BadRequestAlertException("A new kpiScoreCalConfigItem cannot already have an ID", ENTITY_NAME, "idexists");
        }
        KpiScoreCalConfigItem result = kpiScoreCalConfigItemRepository.save(kpiScoreCalConfigItem);
        return ResponseEntity
            .created(new URI("/api/kpi-score-cal-config-items/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /kpi-score-cal-config-items} : Updates an existing kpiScoreCalConfigItem.
     *
     * @param kpiScoreCalConfigItem the kpiScoreCalConfigItem to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated kpiScoreCalConfigItem,
     * or with status {@code 400 (Bad Request)} if the kpiScoreCalConfigItem is not valid,
     * or with status {@code 500 (Internal Server Error)} if the kpiScoreCalConfigItem couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/kpi-score-cal-config-items")
    public ResponseEntity<KpiScoreCalConfigItem> updateKpiScoreCalConfigItem(@RequestBody KpiScoreCalConfigItem kpiScoreCalConfigItem)
        throws URISyntaxException {
        log.debug("REST request to update KpiScoreCalConfigItem : {}", kpiScoreCalConfigItem);
        if (kpiScoreCalConfigItem.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        KpiScoreCalConfigItem result = kpiScoreCalConfigItemRepository.save(kpiScoreCalConfigItem);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, kpiScoreCalConfigItem.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /kpi-score-cal-config-items} : get all the kpiScoreCalConfigItems.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of kpiScoreCalConfigItems in body.
     */
    @GetMapping("/kpi-score-cal-config-items")
    public ResponseEntity<List<KpiScoreCalConfigItem>> getAllKpiScoreCalConfigItems(Pageable pageable) {
        log.debug("REST request to get a page of KpiScoreCalConfigItems");
        Page<KpiScoreCalConfigItem> page = kpiScoreCalConfigItemRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /kpi-score-cal-config-items/:id} : get the "id" kpiScoreCalConfigItem.
     *
     * @param id the id of the kpiScoreCalConfigItem to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the kpiScoreCalConfigItem, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/kpi-score-cal-config-items/{id}")
    public ResponseEntity<KpiScoreCalConfigItem> getKpiScoreCalConfigItem(@PathVariable Long id) {
        log.debug("REST request to get KpiScoreCalConfigItem : {}", id);
        Optional<KpiScoreCalConfigItem> kpiScoreCalConfigItem = kpiScoreCalConfigItemRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(kpiScoreCalConfigItem);
    }

    /**
     * {@code DELETE  /kpi-score-cal-config-items/:id} : delete the "id" kpiScoreCalConfigItem.
     *
     * @param id the id of the kpiScoreCalConfigItem to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/kpi-score-cal-config-items/{id}")
    public ResponseEntity<Void> deleteKpiScoreCalConfigItem(@PathVariable Long id) {
        log.debug("REST request to delete KpiScoreCalConfigItem : {}", id);
        kpiScoreCalConfigItemRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
