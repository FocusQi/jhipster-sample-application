package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * 采购订单单头表\n@author cq
 */
@ApiModel(description = "采购订单单头表\n@author cq")
@Entity
@Table(name = "sc_po_header")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class PoHeader implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @ManyToOne
    @JsonIgnoreProperties(value = "poHeaders", allowSetters = true)
    private PriceCompare priceCompare;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PriceCompare getPriceCompare() {
        return priceCompare;
    }

    public PoHeader priceCompare(PriceCompare priceCompare) {
        this.priceCompare = priceCompare;
        return this;
    }

    public void setPriceCompare(PriceCompare priceCompare) {
        this.priceCompare = priceCompare;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PoHeader)) {
            return false;
        }
        return id != null && id.equals(((PoHeader) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PoHeader{" +
            "id=" + getId() +
            "}";
    }
}
