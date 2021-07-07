package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.BiddingVendorRound;
import com.mycompany.myapp.repository.BiddingVendorRoundRepository;
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
 * REST controller for managing {@link com.mycompany.myapp.domain.BiddingVendorRound}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class BiddingVendorRoundResource {

    private final Logger log = LoggerFactory.getLogger(BiddingVendorRoundResource.class);

    private static final String ENTITY_NAME = "biddingVendorRound";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final BiddingVendorRoundRepository biddingVendorRoundRepository;

    public BiddingVendorRoundResource(BiddingVendorRoundRepository biddingVendorRoundRepository) {
        this.biddingVendorRoundRepository = biddingVendorRoundRepository;
    }

    /**
     * {@code POST  /bidding-vendor-rounds} : Create a new biddingVendorRound.
     *
     * @param biddingVendorRound the biddingVendorRound to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new biddingVendorRound, or with status {@code 400 (Bad Request)} if the biddingVendorRound has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/bidding-vendor-rounds")
    public ResponseEntity<BiddingVendorRound> createBiddingVendorRound(@RequestBody BiddingVendorRound biddingVendorRound) throws URISyntaxException {
        log.debug("REST request to save BiddingVendorRound : {}", biddingVendorRound);
        if (biddingVendorRound.getId() != null) {
            throw new BadRequestAlertException("A new biddingVendorRound cannot already have an ID", ENTITY_NAME, "idexists");
        }
        BiddingVendorRound result = biddingVendorRoundRepository.save(biddingVendorRound);
        return ResponseEntity.created(new URI("/api/bidding-vendor-rounds/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /bidding-vendor-rounds} : Updates an existing biddingVendorRound.
     *
     * @param biddingVendorRound the biddingVendorRound to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated biddingVendorRound,
     * or with status {@code 400 (Bad Request)} if the biddingVendorRound is not valid,
     * or with status {@code 500 (Internal Server Error)} if the biddingVendorRound couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/bidding-vendor-rounds")
    public ResponseEntity<BiddingVendorRound> updateBiddingVendorRound(@RequestBody BiddingVendorRound biddingVendorRound) throws URISyntaxException {
        log.debug("REST request to update BiddingVendorRound : {}", biddingVendorRound);
        if (biddingVendorRound.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        BiddingVendorRound result = biddingVendorRoundRepository.save(biddingVendorRound);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, biddingVendorRound.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /bidding-vendor-rounds} : get all the biddingVendorRounds.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of biddingVendorRounds in body.
     */
    @GetMapping("/bidding-vendor-rounds")
    public List<BiddingVendorRound> getAllBiddingVendorRounds() {
        log.debug("REST request to get all BiddingVendorRounds");
        return biddingVendorRoundRepository.findAll();
    }

    /**
     * {@code GET  /bidding-vendor-rounds/:id} : get the "id" biddingVendorRound.
     *
     * @param id the id of the biddingVendorRound to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the biddingVendorRound, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/bidding-vendor-rounds/{id}")
    public ResponseEntity<BiddingVendorRound> getBiddingVendorRound(@PathVariable Long id) {
        log.debug("REST request to get BiddingVendorRound : {}", id);
        Optional<BiddingVendorRound> biddingVendorRound = biddingVendorRoundRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(biddingVendorRound);
    }

    /**
     * {@code DELETE  /bidding-vendor-rounds/:id} : delete the "id" biddingVendorRound.
     *
     * @param id the id of the biddingVendorRound to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/bidding-vendor-rounds/{id}")
    public ResponseEntity<Void> deleteBiddingVendorRound(@PathVariable Long id) {
        log.debug("REST request to delete BiddingVendorRound : {}", id);

        biddingVendorRoundRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
