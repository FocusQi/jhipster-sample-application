package com.mega.project.srm.web.rest;

import com.mega.project.srm.domain.KpiCalItem;
import com.mega.project.srm.repository.KpiCalItemRepository;
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
 * REST controller for managing {@link com.mega.project.srm.domain.KpiCalItem}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class KpiCalItemResource {
    private final Logger log = LoggerFactory.getLogger(KpiCalItemResource.class);

    private static final String ENTITY_NAME = "kpiCalItem";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final KpiCalItemRepository kpiCalItemRepository;

    public KpiCalItemResource(KpiCalItemRepository kpiCalItemRepository) {
        this.kpiCalItemRepository = kpiCalItemRepository;
    }

    /**
     * {@code POST  /kpi-cal-items} : Create a new kpiCalItem.
     *
     * @param kpiCalItem the kpiCalItem to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new kpiCalItem, or with status {@code 400 (Bad Request)} if the kpiCalItem has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/kpi-cal-items")
    public ResponseEntity<KpiCalItem> createKpiCalItem(@Valid @RequestBody KpiCalItem kpiCalItem) throws URISyntaxException {
        log.debug("REST request to save KpiCalItem : {}", kpiCalItem);
        if (kpiCalItem.getId() != null) {
            throw new BadRequestAlertException("A new kpiCalItem cannot already have an ID", ENTITY_NAME, "idexists");
        }
        KpiCalItem result = kpiCalItemRepository.save(kpiCalItem);
        return ResponseEntity
            .created(new URI("/api/kpi-cal-items/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /kpi-cal-items} : Updates an existing kpiCalItem.
     *
     * @param kpiCalItem the kpiCalItem to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated kpiCalItem,
     * or with status {@code 400 (Bad Request)} if the kpiCalItem is not valid,
     * or with status {@code 500 (Internal Server Error)} if the kpiCalItem couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/kpi-cal-items")
    public ResponseEntity<KpiCalItem> updateKpiCalItem(@Valid @RequestBody KpiCalItem kpiCalItem) throws URISyntaxException {
        log.debug("REST request to update KpiCalItem : {}", kpiCalItem);
        if (kpiCalItem.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        KpiCalItem result = kpiCalItemRepository.save(kpiCalItem);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, kpiCalItem.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /kpi-cal-items} : get all the kpiCalItems.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of kpiCalItems in body.
     */
    @GetMapping("/kpi-cal-items")
    public ResponseEntity<List<KpiCalItem>> getAllKpiCalItems(Pageable pageable) {
        log.debug("REST request to get a page of KpiCalItems");
        Page<KpiCalItem> page = kpiCalItemRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /kpi-cal-items/:id} : get the "id" kpiCalItem.
     *
     * @param id the id of the kpiCalItem to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the kpiCalItem, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/kpi-cal-items/{id}")
    public ResponseEntity<KpiCalItem> getKpiCalItem(@PathVariable Long id) {
        log.debug("REST request to get KpiCalItem : {}", id);
        Optional<KpiCalItem> kpiCalItem = kpiCalItemRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(kpiCalItem);
    }

    /**
     * {@code DELETE  /kpi-cal-items/:id} : delete the "id" kpiCalItem.
     *
     * @param id the id of the kpiCalItem to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/kpi-cal-items/{id}")
    public ResponseEntity<Void> deleteKpiCalItem(@PathVariable Long id) {
        log.debug("REST request to delete KpiCalItem : {}", id);
        kpiCalItemRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
