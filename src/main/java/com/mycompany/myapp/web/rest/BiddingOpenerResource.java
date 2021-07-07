package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.BiddingOpener;
import com.mycompany.myapp.repository.BiddingOpenerRepository;
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
 * REST controller for managing {@link com.mycompany.myapp.domain.BiddingOpener}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class BiddingOpenerResource {

    private final Logger log = LoggerFactory.getLogger(BiddingOpenerResource.class);

    private static final String ENTITY_NAME = "biddingOpener";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final BiddingOpenerRepository biddingOpenerRepository;

    public BiddingOpenerResource(BiddingOpenerRepository biddingOpenerRepository) {
        this.biddingOpenerRepository = biddingOpenerRepository;
    }

    /**
     * {@code POST  /bidding-openers} : Create a new biddingOpener.
     *
     * @param biddingOpener the biddingOpener to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new biddingOpener, or with status {@code 400 (Bad Request)} if the biddingOpener has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/bidding-openers")
    public ResponseEntity<BiddingOpener> createBiddingOpener(@Valid @RequestBody BiddingOpener biddingOpener) throws URISyntaxException {
        log.debug("REST request to save BiddingOpener : {}", biddingOpener);
        if (biddingOpener.getId() != null) {
            throw new BadRequestAlertException("A new biddingOpener cannot already have an ID", ENTITY_NAME, "idexists");
        }
        BiddingOpener result = biddingOpenerRepository.save(biddingOpener);
        return ResponseEntity.created(new URI("/api/bidding-openers/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /bidding-openers} : Updates an existing biddingOpener.
     *
     * @param biddingOpener the biddingOpener to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated biddingOpener,
     * or with status {@code 400 (Bad Request)} if the biddingOpener is not valid,
     * or with status {@code 500 (Internal Server Error)} if the biddingOpener couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/bidding-openers")
    public ResponseEntity<BiddingOpener> updateBiddingOpener(@Valid @RequestBody BiddingOpener biddingOpener) throws URISyntaxException {
        log.debug("REST request to update BiddingOpener : {}", biddingOpener);
        if (biddingOpener.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        BiddingOpener result = biddingOpenerRepository.save(biddingOpener);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, biddingOpener.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /bidding-openers} : get all the biddingOpeners.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of biddingOpeners in body.
     */
    @GetMapping("/bidding-openers")
    public List<BiddingOpener> getAllBiddingOpeners() {
        log.debug("REST request to get all BiddingOpeners");
        return biddingOpenerRepository.findAll();
    }

    /**
     * {@code GET  /bidding-openers/:id} : get the "id" biddingOpener.
     *
     * @param id the id of the biddingOpener to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the biddingOpener, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/bidding-openers/{id}")
    public ResponseEntity<BiddingOpener> getBiddingOpener(@PathVariable Long id) {
        log.debug("REST request to get BiddingOpener : {}", id);
        Optional<BiddingOpener> biddingOpener = biddingOpenerRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(biddingOpener);
    }

    /**
     * {@code DELETE  /bidding-openers/:id} : delete the "id" biddingOpener.
     *
     * @param id the id of the biddingOpener to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/bidding-openers/{id}")
    public ResponseEntity<Void> deleteBiddingOpener(@PathVariable Long id) {
        log.debug("REST request to delete BiddingOpener : {}", id);

        biddingOpenerRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
