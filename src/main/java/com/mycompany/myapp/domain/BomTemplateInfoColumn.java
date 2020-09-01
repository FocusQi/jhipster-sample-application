package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * 成本清单明细列\n@author cq
 */
@ApiModel(description = "成本清单明细列\n@author cq")
@Entity
@Table(name = "srm_bom_template_info_column")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class BomTemplateInfoColumn implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    /**
     * 顺序
     */
    @ApiModelProperty(value = "顺序")
    @Column(name = "sort_index")
    private Integer sortIndex;

    /**
     * 列名
     */
    @ApiModelProperty(value = "列名")
    @Column(name = "column_name")
    private String columnName;

    /**
     * 列值
     */
    @ApiModelProperty(value = "列值")
    @Column(name = "column_value")
    private String columnValue;

    @ManyToOne
    @JsonIgnoreProperties(value = "columns", allowSetters = true)
    private BomTemplateInfo info;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getSortIndex() {
        return sortIndex;
    }

    public BomTemplateInfoColumn sortIndex(Integer sortIndex) {
        this.sortIndex = sortIndex;
        return this;
    }

    public void setSortIndex(Integer sortIndex) {
        this.sortIndex = sortIndex;
    }

    public String getColumnName() {
        return columnName;
    }

    public BomTemplateInfoColumn columnName(String columnName) {
        this.columnName = columnName;
        return this;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getColumnValue() {
        return columnValue;
    }

    public BomTemplateInfoColumn columnValue(String columnValue) {
        this.columnValue = columnValue;
        return this;
    }

    public void setColumnValue(String columnValue) {
        this.columnValue = columnValue;
    }

    public BomTemplateInfo getInfo() {
        return info;
    }

    public BomTemplateInfoColumn info(BomTemplateInfo bomTemplateInfo) {
        this.info = bomTemplateInfo;
        return this;
    }

    public void setInfo(BomTemplateInfo bomTemplateInfo) {
        this.info = bomTemplateInfo;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BomTemplateInfoColumn)) {
            return false;
        }
        return id != null && id.equals(((BomTemplateInfoColumn) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "BomTemplateInfoColumn{" +
            "id=" + getId() +
            ", sortIndex=" + getSortIndex() +
            ", columnName='" + getColumnName() + "'" +
            ", columnValue='" + getColumnValue() + "'" +
            "}";
    }
}
