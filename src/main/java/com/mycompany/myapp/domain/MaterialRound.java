package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

/**
 * 物料轮次\n@author cq
 */
@ApiModel(description = "物料轮次\n@author cq")
@Entity
@Table(name = "srm_material_round")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class MaterialRound implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    /**
     * 需求量
     */
    @NotNull
    @ApiModelProperty(value = "需求量", required = true)
    @Column(name = "need_qty", nullable = false)
    private Double needQty;

    /**
     * 最高限价
     */
    @ApiModelProperty(value = "最高限价")
    @Column(name = "price_ceiling")
    private Double priceCeiling;

    /**
     * 设计文件地址
     */
    @ApiModelProperty(value = "设计文件地址")
    @Column(name = "file_url")
    private Double fileUrl;

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
     * 一个物料伦次信息有多个报价详情
     */
    @ApiModelProperty(value = "一个物料伦次信息有多个报价详情")
    @OneToMany(mappedBy = "materialRound")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<QuotationInfo> quotationInfos = new HashSet<>();

    /**
     * 一个物料伦次信息有多个物料成本清单模板
     */
    @ApiModelProperty(value = "一个物料伦次信息有多个物料成本清单模板")
    @OneToMany(mappedBy = "materialRound")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<BomTemplateHeader> bomTemplateHeaders = new HashSet<>();

    /**
     * 多个物料伦次信息属于一个物料
     */
    @ApiModelProperty(value = "多个物料伦次信息属于一个物料")
    @ManyToOne
    @JsonIgnoreProperties(value = "materialRounds", allowSetters = true)
    private Material material;

    @ManyToOne
    @JsonIgnoreProperties(value = "materialRounds", allowSetters = true)
    private BiddingRoundInfo roundInfo;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getNeedQty() {
        return needQty;
    }

    public MaterialRound needQty(Double needQty) {
        this.needQty = needQty;
        return this;
    }

    public void setNeedQty(Double needQty) {
        this.needQty = needQty;
    }

    public Double getPriceCeiling() {
        return priceCeiling;
    }

    public MaterialRound priceCeiling(Double priceCeiling) {
        this.priceCeiling = priceCeiling;
        return this;
    }

    public void setPriceCeiling(Double priceCeiling) {
        this.priceCeiling = priceCeiling;
    }

    public Double getFileUrl() {
        return fileUrl;
    }

    public MaterialRound fileUrl(Double fileUrl) {
        this.fileUrl = fileUrl;
        return this;
    }

    public void setFileUrl(Double fileUrl) {
        this.fileUrl = fileUrl;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public MaterialRound createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public MaterialRound createdDate(Instant createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public MaterialRound lastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
        return this;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public Instant getLastModifiedDate() {
        return lastModifiedDate;
    }

    public MaterialRound lastModifiedDate(Instant lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
        return this;
    }

    public void setLastModifiedDate(Instant lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public Set<QuotationInfo> getQuotationInfos() {
        return quotationInfos;
    }

    public MaterialRound quotationInfos(Set<QuotationInfo> quotationInfos) {
        this.quotationInfos = quotationInfos;
        return this;
    }

    public MaterialRound addQuotationInfo(QuotationInfo quotationInfo) {
        this.quotationInfos.add(quotationInfo);
        quotationInfo.setMaterialRound(this);
        return this;
    }

    public MaterialRound removeQuotationInfo(QuotationInfo quotationInfo) {
        this.quotationInfos.remove(quotationInfo);
        quotationInfo.setMaterialRound(null);
        return this;
    }

    public void setQuotationInfos(Set<QuotationInfo> quotationInfos) {
        this.quotationInfos = quotationInfos;
    }

    public Set<BomTemplateHeader> getBomTemplateHeaders() {
        return bomTemplateHeaders;
    }

    public MaterialRound bomTemplateHeaders(Set<BomTemplateHeader> bomTemplateHeaders) {
        this.bomTemplateHeaders = bomTemplateHeaders;
        return this;
    }

    public MaterialRound addBomTemplateHeader(BomTemplateHeader bomTemplateHeader) {
        this.bomTemplateHeaders.add(bomTemplateHeader);
        bomTemplateHeader.setMaterialRound(this);
        return this;
    }

    public MaterialRound removeBomTemplateHeader(BomTemplateHeader bomTemplateHeader) {
        this.bomTemplateHeaders.remove(bomTemplateHeader);
        bomTemplateHeader.setMaterialRound(null);
        return this;
    }

    public void setBomTemplateHeaders(Set<BomTemplateHeader> bomTemplateHeaders) {
        this.bomTemplateHeaders = bomTemplateHeaders;
    }

    public Material getMaterial() {
        return material;
    }

    public MaterialRound material(Material material) {
        this.material = material;
        return this;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public BiddingRoundInfo getRoundInfo() {
        return roundInfo;
    }

    public MaterialRound roundInfo(BiddingRoundInfo biddingRoundInfo) {
        this.roundInfo = biddingRoundInfo;
        return this;
    }

    public void setRoundInfo(BiddingRoundInfo biddingRoundInfo) {
        this.roundInfo = biddingRoundInfo;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MaterialRound)) {
            return false;
        }
        return id != null && id.equals(((MaterialRound) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "MaterialRound{" +
            "id=" + getId() +
            ", needQty=" + getNeedQty() +
            ", priceCeiling=" + getPriceCeiling() +
            ", fileUrl=" + getFileUrl() +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", lastModifiedBy='" + getLastModifiedBy() + "'" +
            ", lastModifiedDate='" + getLastModifiedDate() + "'" +
            "}";
    }
}
