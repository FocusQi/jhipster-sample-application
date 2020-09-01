package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.VendorRound;
import com.mycompany.myapp.repository.VendorRoundRepository;
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
 * REST controller for managing {@link com.mycompany.myapp.domain.VendorRound}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class VendorRoundResource {

    private final Logger log = LoggerFactory.getLogger(VendorRoundResource.class);

    private static final String ENTITY_NAME = "vendorRound";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final VendorRoundRepository vendorRoundRepository;

    public VendorRoundResource(VendorRoundRepository vendorRoundRepository) {
        this.vendorRoundRepository = vendorRoundRepository;
    }

    /**
     * {@code POST  /vendor-rounds} : Create a new vendorRound.
     *
     * @param vendorRound the vendorRound to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new vendorRound, or with status {@code 400 (Bad Request)} if the vendorRound has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/vendor-rounds")
    public ResponseEntity<VendorRound> createVendorRound(@RequestBody VendorRound vendorRound) throws URISyntaxException {
        log.debug("REST request to save VendorRound : {}", vendorRound);
        if (vendorRound.getId() != null) {
            throw new BadRequestAlertException("A new vendorRound cannot already have an ID", ENTITY_NAME, "idexists");
        }
        VendorRound result = vendorRoundRepository.save(vendorRound);
        return ResponseEntity.created(new URI("/api/vendor-rounds/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /vendor-rounds} : Updates an existing vendorRound.
     *
     * @param vendorRound the vendorRound to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated vendorRound,
     * or with status {@code 400 (Bad Request)} if the vendorRound is not valid,
     * or with status {@code 500 (Internal Server Error)} if the vendorRound couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/vendor-rounds")
    public ResponseEntity<VendorRound> updateVendorRound(@RequestBody VendorRound vendorRound) throws URISyntaxException {
        log.debug("REST request to update VendorRound : {}", vendorRound);
        if (vendorRound.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        VendorRound result = vendorRoundRepository.save(vendorRound);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, vendorRound.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /vendor-rounds} : get all the vendorRounds.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of vendorRounds in body.
     */
    @GetMapping("/vendor-rounds")
    public List<VendorRound> getAllVendorRounds() {
        log.debug("REST request to get all VendorRounds");
        return vendorRoundRepository.findAll();
    }

    /**
     * {@code GET  /vendor-rounds/:id} : get the "id" vendorRound.
     *
     * @param id the id of the vendorRound to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the vendorRound, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/vendor-rounds/{id}")
    public ResponseEntity<VendorRound> getVendorRound(@PathVariable Long id) {
        log.debug("REST request to get VendorRound : {}", id);
        Optional<VendorRound> vendorRound = vendorRoundRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(vendorRound);
    }

    /**
     * {@code DELETE  /vendor-rounds/:id} : delete the "id" vendorRound.
     *
     * @param id the id of the vendorRound to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/vendor-rounds/{id}")
    public ResponseEntity<Void> deleteVendorRound(@PathVariable Long id) {
        log.debug("REST request to delete VendorRound : {}", id);

        vendorRoundRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
