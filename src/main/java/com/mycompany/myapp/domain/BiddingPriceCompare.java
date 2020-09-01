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
@Table(name = "srm_bidding_price_compare")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class BiddingPriceCompare implements Serializable {

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
    private Set<BiddingQuotationInfo> quotationInfos = new HashSet<>();

    /**
     * 一个比价信息有多个采购订单单头表
     */
    @ApiModelProperty(value = "一个比价信息有多个采购订单单头表")
    @OneToMany(mappedBy = "priceCompare")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<PoHeader> poHeaders = new HashSet<>();

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

    public BiddingPriceCompare compareCode(String compareCode) {
        this.compareCode = compareCode;
        return this;
    }

    public void setCompareCode(String compareCode) {
        this.compareCode = compareCode;
    }

    public CompareStatus getStatus() {
        return status;
    }

    public BiddingPriceCompare status(CompareStatus status) {
        this.status = status;
        return this;
    }

    public void setStatus(CompareStatus status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public BiddingPriceCompare description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public BiddingPriceCompare createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public BiddingPriceCompare createdDate(Instant createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public BiddingPriceCompare lastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
        return this;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public Instant getLastModifiedDate() {
        return lastModifiedDate;
    }

    public BiddingPriceCompare lastModifiedDate(Instant lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
        return this;
    }

    public void setLastModifiedDate(Instant lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public Set<BiddingQuotationInfo> getQuotationInfos() {
        return quotationInfos;
    }

    public BiddingPriceCompare quotationInfos(Set<BiddingQuotationInfo> biddingQuotationInfos) {
        this.quotationInfos = biddingQuotationInfos;
        return this;
    }

    public BiddingPriceCompare addQuotationInfo(BiddingQuotationInfo biddingQuotationInfo) {
        this.quotationInfos.add(biddingQuotationInfo);
        biddingQuotationInfo.setPriceCompare(this);
        return this;
    }

    public BiddingPriceCompare removeQuotationInfo(BiddingQuotationInfo biddingQuotationInfo) {
        this.quotationInfos.remove(biddingQuotationInfo);
        biddingQuotationInfo.setPriceCompare(null);
        return this;
    }

    public void setQuotationInfos(Set<BiddingQuotationInfo> biddingQuotationInfos) {
        this.quotationInfos = biddingQuotationInfos;
    }

    public Set<PoHeader> getPoHeaders() {
        return poHeaders;
    }

    public BiddingPriceCompare poHeaders(Set<PoHeader> poHeaders) {
        this.poHeaders = poHeaders;
        return this;
    }

    public BiddingPriceCompare addPoHeader(PoHeader poHeader) {
        this.poHeaders.add(poHeader);
        poHeader.setPriceCompare(this);
        return this;
    }

    public BiddingPriceCompare removePoHeader(PoHeader poHeader) {
        this.poHeaders.remove(poHeader);
        poHeader.setPriceCompare(null);
        return this;
    }

    public void setPoHeaders(Set<PoHeader> poHeaders) {
        this.poHeaders = poHeaders;
    }

    public BiddingQuotationHeader getBqHeader() {
        return bqHeader;
    }

    public BiddingPriceCompare bqHeader(BiddingQuotationHeader biddingQuotationHeader) {
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
        if (!(o instanceof BiddingPriceCompare)) {
            return false;
        }
        return id != null && id.equals(((BiddingPriceCompare) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "BiddingPriceCompare{" +
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
