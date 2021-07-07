package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

import com.mycompany.myapp.domain.enumeration.CompareStatus;

/**
 * 比价表\n@author cq
 */
@ApiModel(description = "比价表\n@author cq")
@Entity
@Table(name = "srm_price_compare")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class PriceCompare implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    /**
     * 比价编号
     */
    @NotNull
    @ApiModelProperty(value = "比价编号", required = true)
    @Column(name = "compare_code", nullable = false)
    private String compareCode;

    /**
     * 比价状态
     */
    @NotNull
    @ApiModelProperty(value = "比价状态", required = true)
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private CompareStatus status;

    /**
     * 比价说明
     */
    @ApiModelProperty(value = "比价说明")
    @Column(name = "description")
    private String description;

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
     * 一个比价信息有多个报价详情
     */
    @ApiModelProperty(value = "一个比价信息有多个报价详情")
    @OneToMany(mappedBy = "priceCompare")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<QuotationInfo> quotationInfos = new HashSet<>();

    @OneToOne(mappedBy = "priceCompare")
    @JsonIgnore
    private BiddingQuotationHeader bqHeader;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCompareCode() {
        return compareCode;
    }

    public PriceCompare compareCode(String compareCode) {
        this.compareCode = compareCode;
        return this;
    }

    public void setCompareCode(String compareCode) {
        this.compareCode = compareCode;
    }

    public CompareStatus getStatus() {
        return status;
    }

    public PriceCompare status(CompareStatus status) {
        this.status = status;
        return this;
    }

    public void setStatus(CompareStatus status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public PriceCompare description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public PriceCompare createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public PriceCompare createdDate(Instant createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public PriceCompare lastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
        return this;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public Instant getLastModifiedDate() {
        return lastModifiedDate;
    }

    public PriceCompare lastModifiedDate(Instant lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
        return this;
    }

    public void setLastModifiedDate(Instant lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public Set<QuotationInfo> getQuotationInfos() {
        return quotationInfos;
    }

    public PriceCompare quotationInfos(Set<QuotationInfo> quotationInfos) {
        this.quotationInfos = quotationInfos;
        return this;
    }

    public PriceCompare addQuotationInfo(QuotationInfo quotationInfo) {
        this.quotationInfos.add(quotationInfo);
        quotationInfo.setPriceCompare(this);
        return this;
    }

    public PriceCompare removeQuotationInfo(QuotationInfo quotationInfo) {
        this.quotationInfos.remove(quotationInfo);
        quotationInfo.setPriceCompare(null);
        return this;
    }

    public void setQuotationInfos(Set<QuotationInfo> quotationInfos) {
        this.quotationInfos = quotationInfos;
    }

    public BiddingQuotationHeader getBqHeader() {
        return bqHeader;
    }

    public PriceCompare bqHeader(BiddingQuotationHeader biddingQuotationHeader) {
        this.bqHeader = biddingQuotationHeader;
        return this;
    }

    public void setBqHeader(BiddingQuotationHeader biddingQuotationHeader) {
        this.bqHeader = biddingQuotationHeader;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PriceCompare)) {
            return false;
        }
        return id != null && id.equals(((PriceCompare) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PriceCompare{" +
            "id=" + getId() +
            ", compareCode='" + getCompareCode() + "'" +
            ", status='" + getStatus() + "'" +
            ", description='" + getDescription() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", lastModifiedBy='" + getLastModifiedBy() + "'" +
            ", lastModifiedDate='" + getLastModifiedDate() + "'" +
            "}";
    }
}
