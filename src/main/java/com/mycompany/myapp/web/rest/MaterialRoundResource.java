package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.MaterialRound;
import com.mycompany.myapp.repository.MaterialRoundRepository;
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
 * REST controller for managing {@link com.mycompany.myapp.domain.MaterialRound}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class MaterialRoundResource {

    private final Logger log = LoggerFactory.getLogger(MaterialRoundResource.class);

    private static final String ENTITY_NAME = "materialRound";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MaterialRoundRepository materialRoundRepository;

    public MaterialRoundResource(MaterialRoundRepository materialRoundRepository) {
        this.materialRoundRepository = materialRoundRepository;
    }

    /**
     * {@code POST  /material-rounds} : Create a new materialRound.
     *
     * @param materialRound the materialRound to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new materialRound, or with status {@code 400 (Bad Request)} if the materialRound has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/material-rounds")
    public ResponseEntity<MaterialRound> createMaterialRound(@Valid @RequestBody MaterialRound materialRound) throws URISyntaxException {
        log.debug("REST request to save MaterialRound : {}", materialRound);
        if (materialRound.getId() != null) {
            throw new BadRequestAlertException("A new materialRound cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MaterialRound result = materialRoundRepository.save(materialRound);
        return ResponseEntity.created(new URI("/api/material-rounds/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /material-rounds} : Updates an existing materialRound.
     *
     * @param materialRound the materialRound to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated materialRound,
     * or with status {@code 400 (Bad Request)} if the materialRound is not valid,
     * or with status {@code 500 (Internal Server Error)} if the materialRound couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/material-rounds")
    public ResponseEntity<MaterialRound> updateMaterialRound(@Valid @RequestBody MaterialRound materialRound) throws URISyntaxException {
        log.debug("REST request to update MaterialRound : {}", materialRound);
        if (materialRound.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MaterialRound result = materialRoundRepository.save(materialRound);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, materialRound.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /material-rounds} : get all the materialRounds.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of materialRounds in body.
     */
    @GetMapping("/material-rounds")
    public List<MaterialRound> getAllMaterialRounds() {
        log.debug("REST request to get all MaterialRounds");
        return materialRoundRepository.findAll();
    }

    /**
     * {@code GET  /material-rounds/:id} : get the "id" materialRound.
     *
     * @param id the id of the materialRound to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the materialRound, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/material-rounds/{id}")
    public ResponseEntity<MaterialRound> getMaterialRound(@PathVariable Long id) {
        log.debug("REST request to get MaterialRound : {}", id);
        Optional<MaterialRound> materialRound = materialRoundRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(materialRound);
    }

    /**
     * {@code DELETE  /material-rounds/:id} : delete the "id" materialRound.
     *
     * @param id the id of the materialRound to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/material-rounds/{id}")
    public ResponseEntity<Void> deleteMaterialRound(@PathVariable Long id) {
        log.debug("REST request to delete MaterialRound : {}", id);

        materialRoundRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
