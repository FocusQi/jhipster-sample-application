package com.mega.project.srm.web.rest;

import com.mega.project.srm.domain.BiddingQuotationHeader;
import com.mega.project.srm.repository.BiddingQuotationHeaderRepository;
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
 * REST controller for managing {@link com.mega.project.srm.domain.BiddingQuotationHeader}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class BiddingQuotationHeaderResource {
    private final Logger log = LoggerFactory.getLogger(BiddingQuotationHeaderResource.class);

    private static final String ENTITY_NAME = "biddingQuotationHeader";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final BiddingQuotationHeaderRepository biddingQuotationHeaderRepository;

    public BiddingQuotationHeaderResource(BiddingQuotationHeaderRepository biddingQuotationHeaderRepository) {
        this.biddingQuotationHeaderRepository = biddingQuotationHeaderRepository;
    }

    /**
     * {@code POST  /bidding-quotation-headers} : Create a new biddingQuotationHeader.
     *
     * @param biddingQuotationHeader the biddingQuotationHeader to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new biddingQuotationHeader, or with status {@code 400 (Bad Request)} if the biddingQuotationHeader has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/bidding-quotation-headers")
    public ResponseEntity<BiddingQuotationHeader> createBiddingQuotationHeader(
        @Valid @RequestBody BiddingQuotationHeader biddingQuotationHeader
    )
        throws URISyntaxException {
        log.debug("REST request to save BiddingQuotationHeader : {}", biddingQuotationHeader);
        if (biddingQuotationHeader.getId() != null) {
            throw new BadRequestAlertException("A new biddingQuotationHeader cannot already have an ID", ENTITY_NAME, "idexists");
        }
        BiddingQuotationHeader result = biddingQuotationHeaderRepository.save(biddingQuotationHeader);
        return ResponseEntity
            .created(new URI("/api/bidding-quotation-headers/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /bidding-quotation-headers} : Updates an existing biddingQuotationHeader.
     *
     * @param biddingQuotationHeader the biddingQuotationHeader to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated biddingQuotationHeader,
     * or with status {@code 400 (Bad Request)} if the biddingQuotationHeader is not valid,
     * or with status {@code 500 (Internal Server Error)} if the biddingQuotationHeader couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/bidding-quotation-headers")
    public ResponseEntity<BiddingQuotationHeader> updateBiddingQuotationHeader(
        @Valid @RequestBody BiddingQuotationHeader biddingQuotationHeader
    )
        throws URISyntaxException {
        log.debug("REST request to update BiddingQuotationHeader : {}", biddingQuotationHeader);
        if (biddingQuotationHeader.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        BiddingQuotationHeader result = biddingQuotationHeaderRepository.save(biddingQuotationHeader);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, biddingQuotationHeader.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /bidding-quotation-headers} : get all the biddingQuotationHeaders.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of biddingQuotationHeaders in body.
     */
    @GetMapping("/bidding-quotation-headers")
    public ResponseEntity<List<BiddingQuotationHeader>> getAllBiddingQuotationHeaders(Pageable pageable) {
        log.debug("REST request to get a page of BiddingQuotationHeaders");
        Page<BiddingQuotationHeader> page = biddingQuotationHeaderRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /bidding-quotation-headers/:id} : get the "id" biddingQuotationHeader.
     *
     * @param id the id of the biddingQuotationHeader to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the biddingQuotationHeader, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/bidding-quotation-headers/{id}")
    public ResponseEntity<BiddingQuotationHeader> getBiddingQuotationHeader(@PathVariable Long id) {
        log.debug("REST request to get BiddingQuotationHeader : {}", id);
        Optional<BiddingQuotationHeader> biddingQuotationHeader = biddingQuotationHeaderRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(biddingQuotationHeader);
    }

    /**
     * {@code DELETE  /bidding-quotation-headers/:id} : delete the "id" biddingQuotationHeader.
     *
     * @param id the id of the biddingQuotationHeader to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/bidding-quotation-headers/{id}")
    public ResponseEntity<Void> deleteBiddingQuotationHeader(@PathVariable Long id) {
        log.debug("REST request to delete BiddingQuotationHeader : {}", id);

        biddingQuotationHeaderRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
