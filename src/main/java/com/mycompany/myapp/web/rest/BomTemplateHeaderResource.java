package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.BomTemplateHeader;
import com.mycompany.myapp.repository.BomTemplateHeaderRepository;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.mycompany.myapp.domain.BomTemplateHeader}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class BomTemplateHeaderResource {

    private final Logger log = LoggerFactory.getLogger(BomTemplateHeaderResource.class);

    private static final String ENTITY_NAME = "bomTemplateHeader";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final BomTemplateHeaderRepository bomTemplateHeaderRepository;

    public BomTemplateHeaderResource(BomTemplateHeaderRepository bomTemplateHeaderRepository) {
        this.bomTemplateHeaderRepository = bomTemplateHeaderRepository;
    }

    /**
     * {@code POST  /bom-template-headers} : Create a new bomTemplateHeader.
     *
     * @param bomTemplateHeader the bomTemplateHeader to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new bomTemplateHeader, or with status {@code 400 (Bad Request)} if the bomTemplateHeader has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/bom-template-headers")
    public ResponseEntity<BomTemplateHeader> createBomTemplateHeader(@Valid @RequestBody BomTemplateHeader bomTemplateHeader) throws URISyntaxException {
        log.debug("REST request to save BomTemplateHeader : {}", bomTemplateHeader);
        if (bomTemplateHeader.getId() != null) {
            throw new BadRequestAlertException("A new bomTemplateHeader cannot already have an ID", ENTITY_NAME, "idexists");
        }
        BomTemplateHeader result = bomTemplateHeaderRepository.save(bomTemplateHeader);
        return ResponseEntity.created(new URI("/api/bom-template-headers/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /bom-template-headers} : Updates an existing bomTemplateHeader.
     *
     * @param bomTemplateHeader the bomTemplateHeader to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated bomTemplateHeader,
     * or with status {@code 400 (Bad Request)} if the bomTemplateHeader is not valid,
     * or with status {@code 500 (Internal Server Error)} if the bomTemplateHeader couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/bom-template-headers")
    public ResponseEntity<BomTemplateHeader> updateBomTemplateHeader(@Valid @RequestBody BomTemplateHeader bomTemplateHeader) throws URISyntaxException {
        log.debug("REST request to update BomTemplateHeader : {}", bomTemplateHeader);
        if (bomTemplateHeader.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        BomTemplateHeader result = bomTemplateHeaderRepository.save(bomTemplateHeader);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, bomTemplateHeader.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /bom-template-headers} : get all the bomTemplateHeaders.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of bomTemplateHeaders in body.
     */
    @GetMapping("/bom-template-headers")
    public List<BomTemplateHeader> getAllBomTemplateHeaders() {
        log.debug("REST request to get all BomTemplateHeaders");
        return bomTemplateHeaderRepository.findAll();
    }

    /**
     * {@code GET  /bom-template-headers/:id} : get the "id" bomTemplateHeader.
     *
     * @param id the id of the bomTemplateHeader to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the bomTemplateHeader, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/bom-template-headers/{id}")
    public ResponseEntity<BomTemplateHeader> getBomTemplateHeader(@PathVariable Long id) {
        log.debug("REST request to get BomTemplateHeader : {}", id);
        Optional<BomTemplateHeader> bomTemplateHeader = bomTemplateHeaderRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(bomTemplateHeader);
    }

    /**
     * {@code DELETE  /bom-template-headers/:id} : delete the "id" bomTemplateHeader.
     *
     * @param id the id of the bomTemplateHeader to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/bom-template-headers/{id}")
    public ResponseEntity<Void> deleteBomTemplateHeader(@PathVariable Long id) {
        log.debug("REST request to delete BomTemplateHeader : {}", id);

        bomTemplateHeaderRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
