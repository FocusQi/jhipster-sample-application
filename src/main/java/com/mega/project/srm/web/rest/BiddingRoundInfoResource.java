package com.mega.project.srm.web.rest;

import com.mega.project.srm.domain.BiddingRoundInfo;
import com.mega.project.srm.repository.BiddingRoundInfoRepository;
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
 * REST controller for managing {@link com.mega.project.srm.domain.BiddingRoundInfo}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class BiddingRoundInfoResource {
    private final Logger log = LoggerFactory.getLogger(BiddingRoundInfoResource.class);

    private static final String ENTITY_NAME = "biddingRoundInfo";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final BiddingRoundInfoRepository biddingRoundInfoRepository;

    public BiddingRoundInfoResource(BiddingRoundInfoRepository biddingRoundInfoRepository) {
        this.biddingRoundInfoRepository = biddingRoundInfoRepository;
    }

    /**
     * {@code POST  /bidding-round-infos} : Create a new biddingRoundInfo.
     *
     * @param biddingRoundInfo the biddingRoundInfo to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new biddingRoundInfo, or with status {@code 400 (Bad Request)} if the biddingRoundInfo has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/bidding-round-infos")
    public ResponseEntity<BiddingRoundInfo> createBiddingRoundInfo(@Valid @RequestBody BiddingRoundInfo biddingRoundInfo)
        throws URISyntaxException {
        log.debug("REST request to save BiddingRoundInfo : {}", biddingRoundInfo);
        if (biddingRoundInfo.getId() != null) {
            throw new BadRequestAlertException("A new biddingRoundInfo cannot already have an ID", ENTITY_NAME, "idexists");
        }
        BiddingRoundInfo result = biddingRoundInfoRepository.save(biddingRoundInfo);
        return ResponseEntity
            .created(new URI("/api/bidding-round-infos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /bidding-round-infos} : Updates an existing biddingRoundInfo.
     *
     * @param biddingRoundInfo the biddingRoundInfo to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated biddingRoundInfo,
     * or with status {@code 400 (Bad Request)} if the biddingRoundInfo is not valid,
     * or with status {@code 500 (Internal Server Error)} if the biddingRoundInfo couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/bidding-round-infos")
    public ResponseEntity<BiddingRoundInfo> updateBiddingRoundInfo(@Valid @RequestBody BiddingRoundInfo biddingRoundInfo)
        throws URISyntaxException {
        log.debug("REST request to update BiddingRoundInfo : {}", biddingRoundInfo);
        if (biddingRoundInfo.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        BiddingRoundInfo result = biddingRoundInfoRepository.save(biddingRoundInfo);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, biddingRoundInfo.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /bidding-round-infos} : get all the biddingRoundInfos.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of biddingRoundInfos in body.
     */
    @GetMapping("/bidding-round-infos")
    public ResponseEntity<List<BiddingRoundInfo>> getAllBiddingRoundInfos(Pageable pageable) {
        log.debug("REST request to get a page of BiddingRoundInfos");
        Page<BiddingRoundInfo> page = biddingRoundInfoRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /bidding-round-infos/:id} : get the "id" biddingRoundInfo.
     *
     * @param id the id of the biddingRoundInfo to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the biddingRoundInfo, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/bidding-round-infos/{id}")
    public ResponseEntity<BiddingRoundInfo> getBiddingRoundInfo(@PathVariable Long id) {
        log.debug("REST request to get BiddingRoundInfo : {}", id);
        Optional<BiddingRoundInfo> biddingRoundInfo = biddingRoundInfoRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(biddingRoundInfo);
    }

    /**
     * {@code DELETE  /bidding-round-infos/:id} : delete the "id" biddingRoundInfo.
     *
     * @param id the id of the biddingRoundInfo to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/bidding-round-infos/{id}")
    public ResponseEntity<Void> deleteBiddingRoundInfo(@PathVariable Long id) {
        log.debug("REST request to delete BiddingRoundInfo : {}", id);

        biddingRoundInfoRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
