package com.mega.project.srm.web.rest;

import com.mega.project.srm.domain.KpiAssGroupInfo;
import com.mega.project.srm.repository.KpiAssGroupInfoRepository;
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
 * REST controller for managing {@link com.mega.project.srm.domain.KpiAssGroupInfo}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class KpiAssGroupInfoResource {
    private final Logger log = LoggerFactory.getLogger(KpiAssGroupInfoResource.class);

    private static final String ENTITY_NAME = "kpiAssGroupInfo";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final KpiAssGroupInfoRepository kpiAssGroupInfoRepository;

    public KpiAssGroupInfoResource(KpiAssGroupInfoRepository kpiAssGroupInfoRepository) {
        this.kpiAssGroupInfoRepository = kpiAssGroupInfoRepository;
    }

    /**
     * {@code POST  /kpi-ass-group-infos} : Create a new kpiAssGroupInfo.
     *
     * @param kpiAssGroupInfo the kpiAssGroupInfo to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new kpiAssGroupInfo, or with status {@code 400 (Bad Request)} if the kpiAssGroupInfo has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/kpi-ass-group-infos")
    public ResponseEntity<KpiAssGroupInfo> createKpiAssGroupInfo(@Valid @RequestBody KpiAssGroupInfo kpiAssGroupInfo)
        throws URISyntaxException {
        log.debug("REST request to save KpiAssGroupInfo : {}", kpiAssGroupInfo);
        if (kpiAssGroupInfo.getId() != null) {
            throw new BadRequestAlertException("A new kpiAssGroupInfo cannot already have an ID", ENTITY_NAME, "idexists");
        }
        KpiAssGroupInfo result = kpiAssGroupInfoRepository.save(kpiAssGroupInfo);
        return ResponseEntity
            .created(new URI("/api/kpi-ass-group-infos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /kpi-ass-group-infos} : Updates an existing kpiAssGroupInfo.
     *
     * @param kpiAssGroupInfo the kpiAssGroupInfo to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated kpiAssGroupInfo,
     * or with status {@code 400 (Bad Request)} if the kpiAssGroupInfo is not valid,
     * or with status {@code 500 (Internal Server Error)} if the kpiAssGroupInfo couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/kpi-ass-group-infos")
    public ResponseEntity<KpiAssGroupInfo> updateKpiAssGroupInfo(@Valid @RequestBody KpiAssGroupInfo kpiAssGroupInfo)
        throws URISyntaxException {
        log.debug("REST request to update KpiAssGroupInfo : {}", kpiAssGroupInfo);
        if (kpiAssGroupInfo.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        KpiAssGroupInfo result = kpiAssGroupInfoRepository.save(kpiAssGroupInfo);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, kpiAssGroupInfo.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /kpi-ass-group-infos} : get all the kpiAssGroupInfos.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of kpiAssGroupInfos in body.
     */
    @GetMapping("/kpi-ass-group-infos")
    public ResponseEntity<List<KpiAssGroupInfo>> getAllKpiAssGroupInfos(Pageable pageable) {
        log.debug("REST request to get a page of KpiAssGroupInfos");
        Page<KpiAssGroupInfo> page = kpiAssGroupInfoRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /kpi-ass-group-infos/:id} : get the "id" kpiAssGroupInfo.
     *
     * @param id the id of the kpiAssGroupInfo to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the kpiAssGroupInfo, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/kpi-ass-group-infos/{id}")
    public ResponseEntity<KpiAssGroupInfo> getKpiAssGroupInfo(@PathVariable Long id) {
        log.debug("REST request to get KpiAssGroupInfo : {}", id);
        Optional<KpiAssGroupInfo> kpiAssGroupInfo = kpiAssGroupInfoRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(kpiAssGroupInfo);
    }

    /**
     * {@code DELETE  /kpi-ass-group-infos/:id} : delete the "id" kpiAssGroupInfo.
     *
     * @param id the id of the kpiAssGroupInfo to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/kpi-ass-group-infos/{id}")
    public ResponseEntity<Void> deleteKpiAssGroupInfo(@PathVariable Long id) {
        log.debug("REST request to delete KpiAssGroupInfo : {}", id);
        kpiAssGroupInfoRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
