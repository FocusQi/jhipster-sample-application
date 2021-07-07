package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.SrmMsgList;
import com.mycompany.myapp.repository.SrmMsgListRepository;
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
 * REST controller for managing {@link com.mycompany.myapp.domain.SrmMsgList}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class SrmMsgListResource {

    private final Logger log = LoggerFactory.getLogger(SrmMsgListResource.class);

    private static final String ENTITY_NAME = "srmMsgList";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SrmMsgListRepository srmMsgListRepository;

    public SrmMsgListResource(SrmMsgListRepository srmMsgListRepository) {
        this.srmMsgListRepository = srmMsgListRepository;
    }

    /**
     * {@code POST  /srm-msg-lists} : Create a new srmMsgList.
     *
     * @param srmMsgList the srmMsgList to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new srmMsgList, or with status {@code 400 (Bad Request)} if the srmMsgList has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/srm-msg-lists")
    public ResponseEntity<SrmMsgList> createSrmMsgList(@Valid @RequestBody SrmMsgList srmMsgList) throws URISyntaxException {
        log.debug("REST request to save SrmMsgList : {}", srmMsgList);
        if (srmMsgList.getId() != null) {
            throw new BadRequestAlertException("A new srmMsgList cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SrmMsgList result = srmMsgListRepository.save(srmMsgList);
        return ResponseEntity.created(new URI("/api/srm-msg-lists/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /srm-msg-lists} : Updates an existing srmMsgList.
     *
     * @param srmMsgList the srmMsgList to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated srmMsgList,
     * or with status {@code 400 (Bad Request)} if the srmMsgList is not valid,
     * or with status {@code 500 (Internal Server Error)} if the srmMsgList couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/srm-msg-lists")
    public ResponseEntity<SrmMsgList> updateSrmMsgList(@Valid @RequestBody SrmMsgList srmMsgList) throws URISyntaxException {
        log.debug("REST request to update SrmMsgList : {}", srmMsgList);
        if (srmMsgList.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        SrmMsgList result = srmMsgListRepository.save(srmMsgList);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, srmMsgList.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /srm-msg-lists} : get all the srmMsgLists.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of srmMsgLists in body.
     */
    @GetMapping("/srm-msg-lists")
    public List<SrmMsgList> getAllSrmMsgLists() {
        log.debug("REST request to get all SrmMsgLists");
        return srmMsgListRepository.findAll();
    }

    /**
     * {@code GET  /srm-msg-lists/:id} : get the "id" srmMsgList.
     *
     * @param id the id of the srmMsgList to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the srmMsgList, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/srm-msg-lists/{id}")
    public ResponseEntity<SrmMsgList> getSrmMsgList(@PathVariable Long id) {
        log.debug("REST request to get SrmMsgList : {}", id);
        Optional<SrmMsgList> srmMsgList = srmMsgListRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(srmMsgList);
    }

    /**
     * {@code DELETE  /srm-msg-lists/:id} : delete the "id" srmMsgList.
     *
     * @param id the id of the srmMsgList to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/srm-msg-lists/{id}")
    public ResponseEntity<Void> deleteSrmMsgList(@PathVariable Long id) {
        log.debug("REST request to delete SrmMsgList : {}", id);
        srmMsgListRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
