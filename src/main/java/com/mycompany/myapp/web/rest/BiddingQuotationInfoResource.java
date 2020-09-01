package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.BiddingQuotationInfo;
import com.mycompany.myapp.repository.BiddingQuotationInfoRepository;
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
 * REST controller for managing {@link com.mycompany.myapp.domain.BiddingQuotationInfo}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class BiddingQuotationInfoResource {

    private final Logger log = LoggerFactory.getLogger(BiddingQuotationInfoResource.class);

    private static final String ENTITY_NAME = "biddingQuotationInfo";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final BiddingQuotationInfoRepository biddingQuotationInfoRepository;

    public BiddingQuotationInfoResource(BiddingQuotationInfoRepository biddingQuotationInfoRepository) {
        this.biddingQuotationInfoRepository = biddingQuotationInfoRepository;
    }

    /**
     * {@code POST  /bidding-quotation-infos} : Create a new biddingQuotationInfo.
     *
     * @param biddingQuotationInfo the biddingQuotationInfo to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new biddingQuotationInfo, or with status {@code 400 (Bad Request)} if the biddingQuotationInfo has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/bidding-quotation-infos")
    public ResponseEntity<BiddingQuotationInfo> createBiddingQuotationInfo(@Valid @RequestBody BiddingQuotationInfo biddingQuotationInfo) throws URISyntaxException {
        log.debug("REST request to save BiddingQuotationInfo : {}", biddingQuotationInfo);
        if (biddingQuotationInfo.getId() != null) {
            throw new BadRequestAlertException("A new biddingQuotationInfo cannot already have an ID", ENTITY_NAME, "idexists");
        }
        BiddingQuotationInfo result = biddingQuotationInfoRepository.save(biddingQuotationInfo);
        return ResponseEntity.created(new URI("/api/bidding-quotation-infos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /bidding-quotation-infos} : Updates an existing biddingQuotationInfo.
     *
     * @param biddingQuotationInfo the biddingQuotationInfo to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated biddingQuotationInfo,
     * or with status {@code 400 (Bad Request)} if the biddingQuotationInfo is not valid,
     * or with status {@code 500 (Internal Server Error)} if the biddingQuotationInfo couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/bidding-quotation-infos")
    public ResponseEntity<BiddingQuotationInfo> updateBiddingQuotationInfo(@Valid @RequestBody BiddingQuotationInfo biddingQuotationInfo) throws URISyntaxException {
        log.debug("REST request to update BiddingQuotationInfo : {}", biddingQuotationInfo);
        if (biddingQuotationInfo.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        BiddingQuotationInfo result = biddingQuotationInfoRepository.save(biddingQuotationInfo);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, biddingQuotationInfo.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /bidding-quotation-infos} : get all the biddingQuotationInfos.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of biddingQuotationInfos in body.
     */
    @GetMapping("/bidding-quotation-infos")
    public List<BiddingQuotationInfo> getAllBiddingQuotationInfos() {
        log.debug("REST request to get all BiddingQuotationInfos");
        return biddingQuotationInfoRepository.findAll();
    }

    /**
     * {@code GET  /bidding-quotation-infos/:id} : get the "id" biddingQuotationInfo.
     *
     * @param id the id of the biddingQuotationInfo to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the biddingQuotationInfo, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/bidding-quotation-infos/{id}")
    public ResponseEntity<BiddingQuotationInfo> getBiddingQuotationInfo(@PathVariable Long id) {
        log.debug("REST request to get BiddingQuotationInfo : {}", id);
        Optional<BiddingQuotationInfo> biddingQuotationInfo = biddingQuotationInfoRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(biddingQuotationInfo);
    }

    /**
     * {@code DELETE  /bidding-quotation-infos/:id} : delete the "id" biddingQuotationInfo.
     *
     * @param id the id of the biddingQuotationInfo to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/bidding-quotation-infos/{id}")
    public ResponseEntity<Void> deleteBiddingQuotationInfo(@PathVariable Long id) {
        log.debug("REST request to delete BiddingQuotationInfo : {}", id);

        biddingQuotationInfoRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
