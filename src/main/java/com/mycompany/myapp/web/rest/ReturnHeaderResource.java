package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.ReturnHeader;
import com.mycompany.myapp.repository.ReturnHeaderRepository;
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
 * REST controller for managing {@link com.mycompany.myapp.domain.ReturnHeader}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class ReturnHeaderResource {

    private final Logger log = LoggerFactory.getLogger(ReturnHeaderResource.class);

    private static final String ENTITY_NAME = "returnHeader";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ReturnHeaderRepository returnHeaderRepository;

    public ReturnHeaderResource(ReturnHeaderRepository returnHeaderRepository) {
        this.returnHeaderRepository = returnHeaderRepository;
    }

    /**
     * {@code POST  /return-headers} : Create a new returnHeader.
     *
     * @param returnHeader the returnHeader to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new returnHeader, or with status {@code 400 (Bad Request)} if the returnHeader has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/return-headers")
    public ResponseEntity<ReturnHeader> createReturnHeader(@Valid @RequestBody ReturnHeader returnHeader) throws URISyntaxException {
        log.debug("REST request to save ReturnHeader : {}", returnHeader);
        if (returnHeader.getId() != null) {
            throw new BadRequestAlertException("A new returnHeader cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ReturnHeader result = returnHeaderRepository.save(returnHeader);
        return ResponseEntity.created(new URI("/api/return-headers/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /return-headers} : Updates an existing returnHeader.
     *
     * @param returnHeader the returnHeader to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated returnHeader,
     * or with status {@code 400 (Bad Request)} if the returnHeader is not valid,
     * or with status {@code 500 (Internal Server Error)} if the returnHeader couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/return-headers")
    public ResponseEntity<ReturnHeader> updateReturnHeader(@Valid @RequestBody ReturnHeader returnHeader) throws URISyntaxException {
        log.debug("REST request to update ReturnHeader : {}", returnHeader);
        if (returnHeader.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ReturnHeader result = returnHeaderRepository.save(returnHeader);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, returnHeader.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /return-headers} : get all the returnHeaders.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of returnHeaders in body.
     */
    @GetMapping("/return-headers")
    public List<ReturnHeader> getAllReturnHeaders() {
        log.debug("REST request to get all ReturnHeaders");
        return returnHeaderRepository.findAll();
    }

    /**
     * {@code GET  /return-headers/:id} : get the "id" returnHeader.
     *
     * @param id the id of the returnHeader to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the returnHeader, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/return-headers/{id}")
    public ResponseEntity<ReturnHeader> getReturnHeader(@PathVariable Long id) {
        log.debug("REST request to get ReturnHeader : {}", id);
        Optional<ReturnHeader> returnHeader = returnHeaderRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(returnHeader);
    }

    /**
     * {@code DELETE  /return-headers/:id} : delete the "id" returnHeader.
     *
     * @param id the id of the returnHeader to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/return-headers/{id}")
    public ResponseEntity<Void> deleteReturnHeader(@PathVariable Long id) {
        log.debug("REST request to delete ReturnHeader : {}", id);
        returnHeaderRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
