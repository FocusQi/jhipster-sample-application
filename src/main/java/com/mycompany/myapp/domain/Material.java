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
 * 物料\n@author cq
 */
@ApiModel(description = "物料\n@author cq")
@Entity
@Table(name = "sc_material")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Material implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    /**
     * 一个物料有多个物料伦次信息
     */
    @ApiModelProperty(value = "一个物料有多个物料伦次信息")
    @OneToMany(mappedBy = "material")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<MaterialRound> materialRounds = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<MaterialRound> getMaterialRounds() {
        return materialRounds;
    }

    public Material materialRounds(Set<MaterialRound> materialRounds) {
        this.materialRounds = materialRounds;
        return this;
    }

    public Material addMaterialRound(MaterialRound materialRound) {
        this.materialRounds.add(materialRound);
        materialRound.setMaterial(this);
        return this;
    }

    public Material removeMaterialRound(MaterialRound materialRound) {
        this.materialRounds.remove(materialRound);
        materialRound.setMaterial(null);
        return this;
    }

    public void setMaterialRounds(Set<MaterialRound> materialRounds) {
        this.materialRounds = materialRounds;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Material)) {
            return false;
        }
        return id != null && id.equals(((Material) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Material{" +
            "id=" + getId() +
            "}";
    }
}
