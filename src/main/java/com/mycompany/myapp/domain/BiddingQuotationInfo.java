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
import java.time.LocalDate;

import com.mycompany.myapp.domain.enumeration.QuotationStatus;

/**
 * 报价详情\n@author cq
 */
@ApiModel(description = "报价详情\n@author cq")
@Entity
@Table(name = "srm_bidding_quotation_info")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class BiddingQuotationInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    /**
     * 最新报价
     */
    @ApiModelProperty(value = "最新报价")
    @Column(name = "latest_quotation")
    private Double latestQuotation;

    /**
     * 最小订单量
     */
    @ApiModelProperty(value = "最小订单量")
    @Column(name = "min_qty")
    private Double minQty;

    /**
     * 价格有效期
     */
    @ApiModelProperty(value = "价格有效期")
    @Column(name = "valid_date")
    private LocalDate validDate;

    /**
     * 订单后交期
     */
    @ApiModelProperty(value = "订单后交期")
    @Column(name = "delivery_days")
    private Integer deliveryDays;

    /**
     * 历史报价
     */
    @ApiModelProperty(value = "历史报价")
    @Column(name = "history_price")
    private Integer historyPrice;

    /**
     * 现行价格（含税）
     */
    @ApiModelProperty(value = "现行价格（含税）")
    @Column(name = "current_tax_price")
    private Integer currentTaxPrice;

    /**
     * 现行价格有效期
     */
    @ApiModelProperty(value = "现行价格有效期")
    @Column(name = "current_price_valid_date")
    private LocalDate currentPriceValidDate;

    /**
     * 网络价格
     */
    @ApiModelProperty(value = "网络价格")
    @Column(name = "net_price")
    private Double netPrice;

    /**
     * 网络连接
     */
    @ApiModelProperty(value = "网络连接")
    @Column(name = "net_url")
    private String netUrl;

    /**
     * 竞价状态
     */
    @NotNull
    @ApiModelProperty(value = "竞价状态", required = true)
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private QuotationStatus status;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    @Column(name = "remark")
    private String remark;

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

    @ManyToOne
    @JsonIgnoreProperties(value = "quotationInfos", allowSetters = true)
    private BiddingMaterialRound materialRound;

    @ManyToOne
    @JsonIgnoreProperties(value = "quotationInfos", allowSetters = true)
    private BiddingPriceCompare priceCompare;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getLatestQuotation() {
        return latestQuotation;
    }

    public BiddingQuotationInfo latestQuotation(Double latestQuotation) {
        this.latestQuotation = latestQuotation;
        return this;
    }

    public void setLatestQuotation(Double latestQuotation) {
        this.latestQuotation = latestQuotation;
    }

    public Double getMinQty() {
        return minQty;
    }

    public BiddingQuotationInfo minQty(Double minQty) {
        this.minQty = minQty;
        return this;
    }

    public void setMinQty(Double minQty) {
        this.minQty = minQty;
    }

    public LocalDate getValidDate() {
        return validDate;
    }

    public BiddingQuotationInfo validDate(LocalDate validDate) {
        this.validDate = validDate;
        return this;
    }

    public void setValidDate(LocalDate validDate) {
        this.validDate = validDate;
    }

    public Integer getDeliveryDays() {
        return deliveryDays;
    }

    public BiddingQuotationInfo deliveryDays(Integer deliveryDays) {
        this.deliveryDays = deliveryDays;
        return this;
    }

    public void setDeliveryDays(Integer deliveryDays) {
        this.deliveryDays = deliveryDays;
    }

    public Integer getHistoryPrice() {
        return historyPrice;
    }

    public BiddingQuotationInfo historyPrice(Integer historyPrice) {
        this.historyPrice = historyPrice;
        return this;
    }

    public void setHistoryPrice(Integer historyPrice) {
        this.historyPrice = historyPrice;
    }

    public Integer getCurrentTaxPrice() {
        return currentTaxPrice;
    }

    public BiddingQuotationInfo currentTaxPrice(Integer currentTaxPrice) {
        this.currentTaxPrice = currentTaxPrice;
        return this;
    }

    public void setCurrentTaxPrice(Integer currentTaxPrice) {
        this.currentTaxPrice = currentTaxPrice;
    }

    public LocalDate getCurrentPriceValidDate() {
        return currentPriceValidDate;
    }

    public BiddingQuotationInfo currentPriceValidDate(LocalDate currentPriceValidDate) {
        this.currentPriceValidDate = currentPriceValidDate;
        return this;
    }

    public void setCurrentPriceValidDate(LocalDate currentPriceValidDate) {
        this.currentPriceValidDate = currentPriceValidDate;
    }

    public Double getNetPrice() {
        return netPrice;
    }

    public BiddingQuotationInfo netPrice(Double netPrice) {
        this.netPrice = netPrice;
        return this;
    }

    public void setNetPrice(Double netPrice) {
        this.netPrice = netPrice;
    }

    public String getNetUrl() {
        return netUrl;
    }

    public BiddingQuotationInfo netUrl(String netUrl) {
        this.netUrl = netUrl;
        return this;
    }

    public void setNetUrl(String netUrl) {
        this.netUrl = netUrl;
    }

    public QuotationStatus getStatus() {
        return status;
    }

    public BiddingQuotationInfo status(QuotationStatus status) {
        this.status = status;
        return this;
    }

    public void setStatus(QuotationStatus status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public BiddingQuotationInfo remark(String remark) {
        this.remark = remark;
        return this;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public BiddingQuotationInfo createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public BiddingQuotationInfo createdDate(Instant createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public BiddingQuotationInfo lastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
        return this;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public Instant getLastModifiedDate() {
        return lastModifiedDate;
    }

    public BiddingQuotationInfo lastModifiedDate(Instant lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
        return this;
    }

    public void setLastModifiedDate(Instant lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public BiddingMaterialRound getMaterialRound() {
        return materialRound;
    }

    public BiddingQuotationInfo materialRound(BiddingMaterialRound biddingMaterialRound) {
        this.materialRound = biddingMaterialRound;
        return this;
    }

    public void setMaterialRound(BiddingMaterialRound biddingMaterialRound) {
        this.materialRound = biddingMaterialRound;
    }

    public BiddingPriceCompare getPriceCompare() {
        return priceCompare;
    }

    public BiddingQuotationInfo priceCompare(BiddingPriceCompare biddingPriceCompare) {
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
        if (!(o instanceof BiddingQuotationInfo)) {
            return false;
        }
        return id != null && id.equals(((BiddingQuotationInfo) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "BiddingQuotationInfo{" +
            "id=" + getId() +
            ", latestQuotation=" + getLatestQuotation() +
            ", minQty=" + getMinQty() +
            ", validDate='" + getValidDate() + "'" +
            ", deliveryDays=" + getDeliveryDays() +
            ", historyPrice=" + getHistoryPrice() +
            ", currentTaxPrice=" + getCurrentTaxPrice() +
            ", currentPriceValidDate='" + getCurrentPriceValidDate() + "'" +
            ", netPrice=" + getNetPrice() +
            ", netUrl='" + getNetUrl() + "'" +
            ", status='" + getStatus() + "'" +
            ", remark='" + getRemark() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", lastModifiedBy='" + getLastModifiedBy() + "'" +
            ", lastModifiedDate='" + getLastModifiedDate() + "'" +
            "}";
    }
}
