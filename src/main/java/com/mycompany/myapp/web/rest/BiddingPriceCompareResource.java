package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.BiddingPriceCompare;
import com.mycompany.myapp.repository.BiddingPriceCompareRepository;
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
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * REST controller for managing {@link com.mycompany.myapp.domain.BiddingPriceCompare}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class BiddingPriceCompareResource {

    private final Logger log = LoggerFactory.getLogger(BiddingPriceCompareResource.class);

    private static final String ENTITY_NAME = "biddingPriceCompare";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final BiddingPriceCompareRepository biddingPriceCompareRepository;

    public BiddingPriceCompareResource(BiddingPriceCompareRepository biddingPriceCompareRepository) {
        this.biddingPriceCompareRepository = biddingPriceCompareRepository;
    }

    /**
     * {@code POST  /bidding-price-compares} : Create a new biddingPriceCompare.
     *
     * @param biddingPriceCompare the biddingPriceCompare to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new biddingPriceCompare, or with status {@code 400 (Bad Request)} if the biddingPriceCompare has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/bidding-price-compares")
    public ResponseEntity<BiddingPriceCompare> createBiddingPriceCompare(@Valid @RequestBody BiddingPriceCompare biddingPriceCompare) throws URISyntaxException {
        log.debug("REST request to save BiddingPriceCompare : {}", biddingPriceCompare);
        if (biddingPriceCompare.getId() != null) {
            throw new BadRequestAlertException("A new biddingPriceCompare cannot already have an ID", ENTITY_NAME, "idexists");
        }
        BiddingPriceCompare result = biddingPriceCompareRepository.save(biddingPriceCompare);
        return ResponseEntity.created(new URI("/api/bidding-price-compares/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /bidding-price-compares} : Updates an existing biddingPriceCompare.
     *
     * @param biddingPriceCompare the biddingPriceCompare to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated biddingPriceCompare,
     * or with status {@code 400 (Bad Request)} if the biddingPriceCompare is not valid,
     * or with status {@code 500 (Internal Server Error)} if the biddingPriceCompare couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/bidding-price-compares")
    public ResponseEntity<BiddingPriceCompare> updateBiddingPriceCompare(@Valid @RequestBody BiddingPriceCompare biddingPriceCompare) throws URISyntaxException {
        log.debug("REST request to update BiddingPriceCompare : {}", biddingPriceCompare);
        if (biddingPriceCompare.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        BiddingPriceCompare result = biddingPriceCompareRepository.save(biddingPriceCompare);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, biddingPriceCompare.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /bidding-price-compares} : get all the biddingPriceCompares.
     *
     * @param filter the filter of the request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of biddingPriceCompares in body.
     */
    @GetMapping("/bidding-price-compares")
    public List<BiddingPriceCompare> getAllBiddingPriceCompares(@RequestParam(required = false) String filter) {
        if ("bqheader-is-null".equals(filter)) {
            log.debug("REST request to get all BiddingPriceCompares where bqHeader is null");
            return StreamSupport
                .stream(biddingPriceCompareRepository.findAll().spliterator(), false)
                .filter(biddingPriceCompare -> biddingPriceCompare.getBqHeader() == null)
                .collect(Collectors.toList());
        }
        log.debug("REST request to get all BiddingPriceCompares");
        return biddingPriceCompareRepository.findAll();
    }

    /**
     * {@code GET  /bidding-price-compares/:id} : get the "id" biddingPriceCompare.
     *
     * @param id the id of the biddingPriceCompare to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the biddingPriceCompare, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/bidding-price-compares/{id}")
    public ResponseEntity<BiddingPriceCompare> getBiddingPriceCompare(@PathVariable Long id) {
        log.debug("REST request to get BiddingPriceCompare : {}", id);
        Optional<BiddingPriceCompare> biddingPriceCompare = biddingPriceCompareRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(biddingPriceCompare);
    }

    /**
     * {@code DELETE  /bidding-price-compares/:id} : delete the "id" biddingPriceCompare.
     *
     * @param id the id of the biddingPriceCompare to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/bidding-price-compares/{id}")
    public ResponseEntity<Void> deleteBiddingPriceCompare(@PathVariable Long id) {
        log.debug("REST request to delete BiddingPriceCompare : {}", id);

        biddingPriceCompareRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
