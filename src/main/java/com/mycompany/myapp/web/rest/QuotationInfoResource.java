package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.QuotationInfo;
import com.mycompany.myapp.repository.QuotationInfoRepository;
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
 * REST controller for managing {@link com.mycompany.myapp.domain.QuotationInfo}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class QuotationInfoResource {

    private final Logger log = LoggerFactory.getLogger(QuotationInfoResource.class);

    private static final String ENTITY_NAME = "quotationInfo";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final QuotationInfoRepository quotationInfoRepository;

    public QuotationInfoResource(QuotationInfoRepository quotationInfoRepository) {
        this.quotationInfoRepository = quotationInfoRepository;
    }

    /**
     * {@code POST  /quotation-infos} : Create a new quotationInfo.
     *
     * @param quotationInfo the quotationInfo to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new quotationInfo, or with status {@code 400 (Bad Request)} if the quotationInfo has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/quotation-infos")
    public ResponseEntity<QuotationInfo> createQuotationInfo(@Valid @RequestBody QuotationInfo quotationInfo) throws URISyntaxException {
        log.debug("REST request to save QuotationInfo : {}", quotationInfo);
        if (quotationInfo.getId() != null) {
            throw new BadRequestAlertException("A new quotationInfo cannot already have an ID", ENTITY_NAME, "idexists");
        }
        QuotationInfo result = quotationInfoRepository.save(quotationInfo);
        return ResponseEntity.created(new URI("/api/quotation-infos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /quotation-infos} : Updates an existing quotationInfo.
     *
     * @param quotationInfo the quotationInfo to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated quotationInfo,
     * or with status {@code 400 (Bad Request)} if the quotationInfo is not valid,
     * or with status {@code 500 (Internal Server Error)} if the quotationInfo couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/quotation-infos")
    public ResponseEntity<QuotationInfo> updateQuotationInfo(@Valid @RequestBody QuotationInfo quotationInfo) throws URISyntaxException {
        log.debug("REST request to update QuotationInfo : {}", quotationInfo);
        if (quotationInfo.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        QuotationInfo result = quotationInfoRepository.save(quotationInfo);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, quotationInfo.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /quotation-infos} : get all the quotationInfos.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of quotationInfos in body.
     */
    @GetMapping("/quotation-infos")
    public List<QuotationInfo> getAllQuotationInfos() {
        log.debug("REST request to get all QuotationInfos");
        return quotationInfoRepository.findAll();
    }

    /**
     * {@code GET  /quotation-infos/:id} : get the "id" quotationInfo.
     *
     * @param id the id of the quotationInfo to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the quotationInfo, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/quotation-infos/{id}")
    public ResponseEntity<QuotationInfo> getQuotationInfo(@PathVariable Long id) {
        log.debug("REST request to get QuotationInfo : {}", id);
        Optional<QuotationInfo> quotationInfo = quotationInfoRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(quotationInfo);
    }

    /**
     * {@code DELETE  /quotation-infos/:id} : delete the "id" quotationInfo.
     *
     * @param id the id of the quotationInfo to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/quotation-infos/{id}")
    public ResponseEntity<Void> deleteQuotationInfo(@PathVariable Long id) {
        log.debug("REST request to delete QuotationInfo : {}", id);

        quotationInfoRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
