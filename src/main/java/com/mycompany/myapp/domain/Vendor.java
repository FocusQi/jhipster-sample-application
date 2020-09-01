package com.mycompany.myapp.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * 供应商\n@author cq
 */
@ApiModel(description = "供应商\n@author cq")
@Entity
@Table(name = "vm_vendor")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Vendor implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    /**
     * 一个供应商有多个供应商轮次
     */
    @ApiModelProperty(value = "一个供应商有多个供应商轮次")
    @OneToMany(mappedBy = "vendor")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<VendorRound> vendorRounds = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<VendorRound> getVendorRounds() {
        return vendorRounds;
    }

    public Vendor vendorRounds(Set<VendorRound> vendorRounds) {
        this.vendorRounds = vendorRounds;
        return this;
    }

    public Vendor addVendorRound(VendorRound vendorRound) {
        this.vendorRounds.add(vendorRound);
        vendorRound.setVendor(this);
        return this;
    }

    public Vendor removeVendorRound(VendorRound vendorRound) {
        this.vendorRounds.remove(vendorRound);
        vendorRound.setVendor(null);
        return this;
    }

    public void setVendorRounds(Set<VendorRound> vendorRounds) {
        this.vendorRounds = vendorRounds;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Vendor)) {
            return false;
        }
        return id != null && id.equals(((Vendor) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Vendor{" +
            "id=" + getId() +
            "}";
    }
}
