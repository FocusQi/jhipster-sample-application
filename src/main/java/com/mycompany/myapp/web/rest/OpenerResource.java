package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.Opener;
import com.mycompany.myapp.repository.OpenerRepository;
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
 * REST controller for managing {@link com.mycompany.myapp.domain.Opener}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class OpenerResource {

    private final Logger log = LoggerFactory.getLogger(OpenerResource.class);

    private static final String ENTITY_NAME = "opener";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final OpenerRepository openerRepository;

    public OpenerResource(OpenerRepository openerRepository) {
        this.openerRepository = openerRepository;
    }

    /**
     * {@code POST  /openers} : Create a new opener.
     *
     * @param opener the opener to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new opener, or with status {@code 400 (Bad Request)} if the opener has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/openers")
    public ResponseEntity<Opener> createOpener(@Valid @RequestBody Opener opener) throws URISyntaxException {
        log.debug("REST request to save Opener : {}", opener);
        if (opener.getId() != null) {
            throw new BadRequestAlertException("A new opener cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Opener result = openerRepository.save(opener);
        return ResponseEntity.created(new URI("/api/openers/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /openers} : Updates an existing opener.
     *
     * @param opener the opener to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated opener,
     * or with status {@code 400 (Bad Request)} if the opener is not valid,
     * or with status {@code 500 (Internal Server Error)} if the opener couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/openers")
    public ResponseEntity<Opener> updateOpener(@Valid @RequestBody Opener opener) throws URISyntaxException {
        log.debug("REST request to update Opener : {}", opener);
        if (opener.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Opener result = openerRepository.save(opener);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, opener.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /openers} : get all the openers.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of openers in body.
     */
    @GetMapping("/openers")
    public List<Opener> getAllOpeners() {
        log.debug("REST request to get all Openers");
        return openerRepository.findAll();
    }

    /**
     * {@code GET  /openers/:id} : get the "id" opener.
     *
     * @param id the id of the opener to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the opener, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/openers/{id}")
    public ResponseEntity<Opener> getOpener(@PathVariable Long id) {
        log.debug("REST request to get Opener : {}", id);
        Optional<Opener> opener = openerRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(opener);
    }

    /**
     * {@code DELETE  /openers/:id} : delete the "id" opener.
     *
     * @param id the id of the opener to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/openers/{id}")
    public ResponseEntity<Void> deleteOpener(@PathVariable Long id) {
        log.debug("REST request to delete Opener : {}", id);

        openerRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
