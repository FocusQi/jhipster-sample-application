package com.mega.project.srm.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import java.io.Serializable;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JsonIgnoreProperties(value = "poHeaders", allowSetters = true)
    private BiddingPriceCompare priceCompare;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BiddingPriceCompare getPriceCompare() {
        return priceCompare;
    }

    public PoHeader priceCompare(BiddingPriceCompare biddingPriceCompare) {
        this.priceCompare = biddingPriceCompare;
        return this;
    }

    public void setPriceCompare(BiddingPriceCompare biddingPriceCompare) {
        this.priceCompare = biddingPriceCompare;
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
