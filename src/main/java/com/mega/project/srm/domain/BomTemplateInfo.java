package com.mega.project.srm.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * 成本清单明细\n@author cq
 */
@ApiModel(description = "成本清单明细\n@author cq")
@Entity
@Table(name = "srm_bom_template_info")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class BomTemplateInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 单位
     */
    @ApiModelProperty(value = "单位")
    @Column(name = "uom")
    private String uom;

    /**
     * 用量
     */
    @ApiModelProperty(value = "用量")
    @Column(name = "use_qty")
    private Double useQty;

    /**
     * 单价
     */
    @ApiModelProperty(value = "单价")
    @Column(name = "unit_price")
    private Double unitPrice;

    /**
     * 合计（未税）
     */
    @ApiModelProperty(value = "合计（未税）")
    @Column(name = "total")
    private Double total;

    /**
     * 税率
     */
    @ApiModelProperty(value = "税率")
    @Column(name = "tax")
    private Double tax;

    /**
     * 合计（含税）
     */
    @ApiModelProperty(value = "合计（含税）")
    @Column(name = "tax_total")
    private Double taxTotal;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    @Column(name = "remark")
    private Double remark;

    /**
     * 创建者
     */
    @ApiModelProperty(value = "创建者")
    @Column(name = "created_by")
    private String createdBy;

    /**
     * 创建日期
     */
    @ApiModelProperty(value = "创建日期")
    @Column(name = "created_date")
    private Instant createdDate;

    /**
     * 修改者
     */
    @ApiModelProperty(value = "修改者")
    @Column(name = "last_modified_by")
    private String lastModifiedBy;

    /**
     * 修改日期
     */
    @ApiModelProperty(value = "修改日期")
    @Column(name = "last_modified_date")
    private Instant lastModifiedDate;

    /**
     * 一个物料成本清单模板有多个成本清单明细
     */
    @ApiModelProperty(value = "一个物料成本清单模板有多个成本清单明细")
    @OneToMany(mappedBy = "info")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<BomTemplateInfoColumn> columns = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = "infos", allowSetters = true)
    private BomTemplateHeader header;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUom() {
        return uom;
    }

    public BomTemplateInfo uom(String uom) {
        this.uom = uom;
        return this;
    }

    public void setUom(String uom) {
        this.uom = uom;
    }

    public Double getUseQty() {
        return useQty;
    }

    public BomTemplateInfo useQty(Double useQty) {
        this.useQty = useQty;
        return this;
    }

    public void setUseQty(Double useQty) {
        this.useQty = useQty;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public BomTemplateInfo unitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
        return this;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Double getTotal() {
        return total;
    }

    public BomTemplateInfo total(Double total) {
        this.total = total;
        return this;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Double getTax() {
        return tax;
    }

    public BomTemplateInfo tax(Double tax) {
        this.tax = tax;
        return this;
    }

    public void setTax(Double tax) {
        this.tax = tax;
    }

    public Double getTaxTotal() {
        return taxTotal;
    }

    public BomTemplateInfo taxTotal(Double taxTotal) {
        this.taxTotal = taxTotal;
        return this;
    }

    public void setTaxTotal(Double taxTotal) {
        this.taxTotal = taxTotal;
    }

    public Double getRemark() {
        return remark;
    }

    public BomTemplateInfo remark(Double remark) {
        this.remark = remark;
        return this;
    }

    public void setRemark(Double remark) {
        this.remark = remark;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public BomTemplateInfo createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public BomTemplateInfo createdDate(Instant createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public BomTemplateInfo lastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
        return this;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public Instant getLastModifiedDate() {
        return lastModifiedDate;
    }

    public BomTemplateInfo lastModifiedDate(Instant lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
        return this;
    }

    public void setLastModifiedDate(Instant lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public Set<BomTemplateInfoColumn> getColumns() {
        return columns;
    }

    public BomTemplateInfo columns(Set<BomTemplateInfoColumn> bomTemplateInfoColumns) {
        this.columns = bomTemplateInfoColumns;
        return this;
    }

    public BomTemplateInfo addColumn(BomTemplateInfoColumn bomTemplateInfoColumn) {
        this.columns.add(bomTemplateInfoColumn);
        bomTemplateInfoColumn.setInfo(this);
        return this;
    }

    public BomTemplateInfo removeColumn(BomTemplateInfoColumn bomTemplateInfoColumn) {
        this.columns.remove(bomTemplateInfoColumn);
        bomTemplateInfoColumn.setInfo(null);
        return this;
    }

    public void setColumns(Set<BomTemplateInfoColumn> bomTemplateInfoColumns) {
        this.columns = bomTemplateInfoColumns;
    }

    public BomTemplateHeader getHeader() {
        return header;
    }

    public BomTemplateInfo header(BomTemplateHeader bomTemplateHeader) {
        this.header = bomTemplateHeader;
        return this;
    }

    public void setHeader(BomTemplateHeader bomTemplateHeader) {
        this.header = bomTemplateHeader;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BomTemplateInfo)) {
            return false;
        }
        return id != null && id.equals(((BomTemplateInfo) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "BomTemplateInfo{" +
            "id=" + getId() +
            ", uom='" + getUom() + "'" +
            ", useQty=" + getUseQty() +
            ", unitPrice=" + getUnitPrice() +
            ", total=" + getTotal() +
            ", tax=" + getTax() +
            ", taxTotal=" + getTaxTotal() +
            ", remark=" + getRemark() +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", lastModifiedBy='" + getLastModifiedBy() + "'" +
            ", lastModifiedDate='" + getLastModifiedDate() + "'" +
            "}";
    }
}
