package com.mega.project.srm.web.rest;

import com.mega.project.srm.domain.BomTemplateInfoColumn;
import com.mega.project.srm.repository.BomTemplateInfoColumnRepository;
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
 * REST controller for managing {@link com.mega.project.srm.domain.BomTemplateInfoColumn}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class BomTemplateInfoColumnResource {
    private final Logger log = LoggerFactory.getLogger(BomTemplateInfoColumnResource.class);

    private static final String ENTITY_NAME = "bomTemplateInfoColumn";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final BomTemplateInfoColumnRepository bomTemplateInfoColumnRepository;

    public BomTemplateInfoColumnResource(BomTemplateInfoColumnRepository bomTemplateInfoColumnRepository) {
        this.bomTemplateInfoColumnRepository = bomTemplateInfoColumnRepository;
    }

    /**
     * {@code POST  /bom-template-info-columns} : Create a new bomTemplateInfoColumn.
     *
     * @param bomTemplateInfoColumn the bomTemplateInfoColumn to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new bomTemplateInfoColumn, or with status {@code 400 (Bad Request)} if the bomTemplateInfoColumn has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/bom-template-info-columns")
    public ResponseEntity<BomTemplateInfoColumn> createBomTemplateInfoColumn(@RequestBody BomTemplateInfoColumn bomTemplateInfoColumn)
        throws URISyntaxException {
        log.debug("REST request to save BomTemplateInfoColumn : {}", bomTemplateInfoColumn);
        if (bomTemplateInfoColumn.getId() != null) {
            throw new BadRequestAlertException("A new bomTemplateInfoColumn cannot already have an ID", ENTITY_NAME, "idexists");
        }
        BomTemplateInfoColumn result = bomTemplateInfoColumnRepository.save(bomTemplateInfoColumn);
        return ResponseEntity
            .created(new URI("/api/bom-template-info-columns/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /bom-template-info-columns} : Updates an existing bomTemplateInfoColumn.
     *
     * @param bomTemplateInfoColumn the bomTemplateInfoColumn to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated bomTemplateInfoColumn,
     * or with status {@code 400 (Bad Request)} if the bomTemplateInfoColumn is not valid,
     * or with status {@code 500 (Internal Server Error)} if the bomTemplateInfoColumn couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/bom-template-info-columns")
    public ResponseEntity<BomTemplateInfoColumn> updateBomTemplateInfoColumn(@RequestBody BomTemplateInfoColumn bomTemplateInfoColumn)
        throws URISyntaxException {
        log.debug("REST request to update BomTemplateInfoColumn : {}", bomTemplateInfoColumn);
        if (bomTemplateInfoColumn.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        BomTemplateInfoColumn result = bomTemplateInfoColumnRepository.save(bomTemplateInfoColumn);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, bomTemplateInfoColumn.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /bom-template-info-columns} : get all the bomTemplateInfoColumns.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of bomTemplateInfoColumns in body.
     */
    @GetMapping("/bom-template-info-columns")
    public ResponseEntity<List<BomTemplateInfoColumn>> getAllBomTemplateInfoColumns(Pageable pageable) {
        log.debug("REST request to get a page of BomTemplateInfoColumns");
        Page<BomTemplateInfoColumn> page = bomTemplateInfoColumnRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /bom-template-info-columns/:id} : get the "id" bomTemplateInfoColumn.
     *
     * @param id the id of the bomTemplateInfoColumn to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the bomTemplateInfoColumn, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/bom-template-info-columns/{id}")
    public ResponseEntity<BomTemplateInfoColumn> getBomTemplateInfoColumn(@PathVariable Long id) {
        log.debug("REST request to get BomTemplateInfoColumn : {}", id);
        Optional<BomTemplateInfoColumn> bomTemplateInfoColumn = bomTemplateInfoColumnRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(bomTemplateInfoColumn);
    }

    /**
     * {@code DELETE  /bom-template-info-columns/:id} : delete the "id" bomTemplateInfoColumn.
     *
     * @param id the id of the bomTemplateInfoColumn to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/bom-template-info-columns/{id}")
    public ResponseEntity<Void> deleteBomTemplateInfoColumn(@PathVariable Long id) {
        log.debug("REST request to delete BomTemplateInfoColumn : {}", id);

        bomTemplateInfoColumnRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
