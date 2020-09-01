package com.mega.project.srm.web.rest;

import com.mega.project.srm.domain.BiddingMaterialRound;
import com.mega.project.srm.repository.BiddingMaterialRoundRepository;
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
 * REST controller for managing {@link com.mega.project.srm.domain.BiddingMaterialRound}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class BiddingMaterialRoundResource {
    private final Logger log = LoggerFactory.getLogger(BiddingMaterialRoundResource.class);

    private static final String ENTITY_NAME = "biddingMaterialRound";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final BiddingMaterialRoundRepository biddingMaterialRoundRepository;

    public BiddingMaterialRoundResource(BiddingMaterialRoundRepository biddingMaterialRoundRepository) {
        this.biddingMaterialRoundRepository = biddingMaterialRoundRepository;
    }

    /**
     * {@code POST  /bidding-material-rounds} : Create a new biddingMaterialRound.
     *
     * @param biddingMaterialRound the biddingMaterialRound to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new biddingMaterialRound, or with status {@code 400 (Bad Request)} if the biddingMaterialRound has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/bidding-material-rounds")
    public ResponseEntity<BiddingMaterialRound> createBiddingMaterialRound(@Valid @RequestBody BiddingMaterialRound biddingMaterialRound)
        throws URISyntaxException {
        log.debug("REST request to save BiddingMaterialRound : {}", biddingMaterialRound);
        if (biddingMaterialRound.getId() != null) {
            throw new BadRequestAlertException("A new biddingMaterialRound cannot already have an ID", ENTITY_NAME, "idexists");
        }
        BiddingMaterialRound result = biddingMaterialRoundRepository.save(biddingMaterialRound);
        return ResponseEntity
            .created(new URI("/api/bidding-material-rounds/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /bidding-material-rounds} : Updates an existing biddingMaterialRound.
     *
     * @param biddingMaterialRound the biddingMaterialRound to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated biddingMaterialRound,
     * or with status {@code 400 (Bad Request)} if the biddingMaterialRound is not valid,
     * or with status {@code 500 (Internal Server Error)} if the biddingMaterialRound couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/bidding-material-rounds")
    public ResponseEntity<BiddingMaterialRound> updateBiddingMaterialRound(@Valid @RequestBody BiddingMaterialRound biddingMaterialRound)
        throws URISyntaxException {
        log.debug("REST request to update BiddingMaterialRound : {}", biddingMaterialRound);
        if (biddingMaterialRound.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        BiddingMaterialRound result = biddingMaterialRoundRepository.save(biddingMaterialRound);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, biddingMaterialRound.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /bidding-material-rounds} : get all the biddingMaterialRounds.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of biddingMaterialRounds in body.
     */
    @GetMapping("/bidding-material-rounds")
    public ResponseEntity<List<BiddingMaterialRound>> getAllBiddingMaterialRounds(Pageable pageable) {
        log.debug("REST request to get a page of BiddingMaterialRounds");
        Page<BiddingMaterialRound> page = biddingMaterialRoundRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /bidding-material-rounds/:id} : get the "id" biddingMaterialRound.
     *
     * @param id the id of the biddingMaterialRound to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the biddingMaterialRound, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/bidding-material-rounds/{id}")
    public ResponseEntity<BiddingMaterialRound> getBiddingMaterialRound(@PathVariable Long id) {
        log.debug("REST request to get BiddingMaterialRound : {}", id);
        Optional<BiddingMaterialRound> biddingMaterialRound = biddingMaterialRoundRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(biddingMaterialRound);
    }

    /**
     * {@code DELETE  /bidding-material-rounds/:id} : delete the "id" biddingMaterialRound.
     *
     * @param id the id of the biddingMaterialRound to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/bidding-material-rounds/{id}")
    public ResponseEntity<Void> deleteBiddingMaterialRound(@PathVariable Long id) {
        log.debug("REST request to delete BiddingMaterialRound : {}", id);

        biddingMaterialRoundRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
