package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.PriceCompare;
import com.mycompany.myapp.repository.PriceCompareRepository;
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
 * REST controller for managing {@link com.mycompany.myapp.domain.PriceCompare}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class PriceCompareResource {

    private final Logger log = LoggerFactory.getLogger(PriceCompareResource.class);

    private static final String ENTITY_NAME = "priceCompare";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PriceCompareRepository priceCompareRepository;

    public PriceCompareResource(PriceCompareRepository priceCompareRepository) {
        this.priceCompareRepository = priceCompareRepository;
    }

    /**
     * {@code POST  /price-compares} : Create a new priceCompare.
     *
     * @param priceCompare the priceCompare to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new priceCompare, or with status {@code 400 (Bad Request)} if the priceCompare has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/price-compares")
    public ResponseEntity<PriceCompare> createPriceCompare(@Valid @RequestBody PriceCompare priceCompare) throws URISyntaxException {
        log.debug("REST request to save PriceCompare : {}", priceCompare);
        if (priceCompare.getId() != null) {
            throw new BadRequestAlertException("A new priceCompare cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PriceCompare result = priceCompareRepository.save(priceCompare);
        return ResponseEntity.created(new URI("/api/price-compares/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /price-compares} : Updates an existing priceCompare.
     *
     * @param priceCompare the priceCompare to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated priceCompare,
     * or with status {@code 400 (Bad Request)} if the priceCompare is not valid,
     * or with status {@code 500 (Internal Server Error)} if the priceCompare couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/price-compares")
    public ResponseEntity<PriceCompare> updatePriceCompare(@Valid @RequestBody PriceCompare priceCompare) throws URISyntaxException {
        log.debug("REST request to update PriceCompare : {}", priceCompare);
        if (priceCompare.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PriceCompare result = priceCompareRepository.save(priceCompare);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, priceCompare.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /price-compares} : get all the priceCompares.
     *
     * @param filter the filter of the request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of priceCompares in body.
     */
    @GetMapping("/price-compares")
    public List<PriceCompare> getAllPriceCompares(@RequestParam(required = false) String filter) {
        if ("bqheader-is-null".equals(filter)) {
            log.debug("REST request to get all PriceCompares where bqHeader is null");
            return StreamSupport
                .stream(priceCompareRepository.findAll().spliterator(), false)
                .filter(priceCompare -> priceCompare.getBqHeader() == null)
                .collect(Collectors.toList());
        }
        log.debug("REST request to get all PriceCompares");
        return priceCompareRepository.findAll();
    }

    /**
     * {@code GET  /price-compares/:id} : get the "id" priceCompare.
     *
     * @param id the id of the priceCompare to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the priceCompare, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/price-compares/{id}")
    public ResponseEntity<PriceCompare> getPriceCompare(@PathVariable Long id) {
        log.debug("REST request to get PriceCompare : {}", id);
        Optional<PriceCompare> priceCompare = priceCompareRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(priceCompare);
    }

    /**
     * {@code DELETE  /price-compares/:id} : delete the "id" priceCompare.
     *
     * @param id the id of the priceCompare to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/price-compares/{id}")
    public ResponseEntity<Void> deletePriceCompare(@PathVariable Long id) {
        log.debug("REST request to delete PriceCompare : {}", id);

        priceCompareRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
