package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.BomTemplateInfo;
import com.mycompany.myapp.repository.BomTemplateInfoRepository;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.mycompany.myapp.domain.BomTemplateInfo}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class BomTemplateInfoResource {

    private final Logger log = LoggerFactory.getLogger(BomTemplateInfoResource.class);

    private static final String ENTITY_NAME = "bomTemplateInfo";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final BomTemplateInfoRepository bomTemplateInfoRepository;

    public BomTemplateInfoResource(BomTemplateInfoRepository bomTemplateInfoRepository) {
        this.bomTemplateInfoRepository = bomTemplateInfoRepository;
    }

    /**
     * {@code POST  /bom-template-infos} : Create a new bomTemplateInfo.
     *
     * @param bomTemplateInfo the bomTemplateInfo to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new bomTemplateInfo, or with status {@code 400 (Bad Request)} if the bomTemplateInfo has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/bom-template-infos")
    public ResponseEntity<BomTemplateInfo> createBomTemplateInfo(@RequestBody BomTemplateInfo bomTemplateInfo) throws URISyntaxException {
        log.debug("REST request to save BomTemplateInfo : {}", bomTemplateInfo);
        if (bomTemplateInfo.getId() != null) {
            throw new BadRequestAlertException("A new bomTemplateInfo cannot already have an ID", ENTITY_NAME, "idexists");
        }
        BomTemplateInfo result = bomTemplateInfoRepository.save(bomTemplateInfo);
        return ResponseEntity.created(new URI("/api/bom-template-infos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /bom-template-infos} : Updates an existing bomTemplateInfo.
     *
     * @param bomTemplateInfo the bomTemplateInfo to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated bomTemplateInfo,
     * or with status {@code 400 (Bad Request)} if the bomTemplateInfo is not valid,
     * or with status {@code 500 (Internal Server Error)} if the bomTemplateInfo couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/bom-template-infos")
    public ResponseEntity<BomTemplateInfo> updateBomTemplateInfo(@RequestBody BomTemplateInfo bomTemplateInfo) throws URISyntaxException {
        log.debug("REST request to update BomTemplateInfo : {}", bomTemplateInfo);
        if (bomTemplateInfo.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        BomTemplateInfo result = bomTemplateInfoRepository.save(bomTemplateInfo);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, bomTemplateInfo.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /bom-template-infos} : get all the bomTemplateInfos.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of bomTemplateInfos in body.
     */
    @GetMapping("/bom-template-infos")
    public List<BomTemplateInfo> getAllBomTemplateInfos() {
        log.debug("REST request to get all BomTemplateInfos");
        return bomTemplateInfoRepository.findAll();
    }

    /**
     * {@code GET  /bom-template-infos/:id} : get the "id" bomTemplateInfo.
     *
     * @param id the id of the bomTemplateInfo to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the bomTemplateInfo, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/bom-template-infos/{id}")
    public ResponseEntity<BomTemplateInfo> getBomTemplateInfo(@PathVariable Long id) {
        log.debug("REST request to get BomTemplateInfo : {}", id);
        Optional<BomTemplateInfo> bomTemplateInfo = bomTemplateInfoRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(bomTemplateInfo);
    }

    /**
     * {@code DELETE  /bom-template-infos/:id} : delete the "id" bomTemplateInfo.
     *
     * @param id the id of the bomTemplateInfo to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/bom-template-infos/{id}")
    public ResponseEntity<Void> deleteBomTemplateInfo(@PathVariable Long id) {
        log.debug("REST request to delete BomTemplateInfo : {}", id);

        bomTemplateInfoRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
