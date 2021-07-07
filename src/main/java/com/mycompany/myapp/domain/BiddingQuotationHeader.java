package com.mycompany.myapp.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import com.mycompany.myapp.domain.enumeration.ModeType;

import com.mycompany.myapp.domain.enumeration.BiddingType;

import com.mycompany.myapp.domain.enumeration.BiddingStatus;

/**
 * 询报价头表\n@author cq
 */
@ApiModel(description = "询报价头表\n@author cq")
@Entity
@Table(name = "srm_bidding_quotation_header")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class BiddingQuotationHeader implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    /**
     * 竞价单编号
     */
    @NotNull
    @ApiModelProperty(value = "竞价单编号", required = true)
    @Column(name = "bidding_code", nullable = false)
    private String biddingCode;

    /**
     * 模式类型
     */
    @NotNull
    @ApiModelProperty(value = "模式类型", required = true)
    @Enumerated(EnumType.STRING)
    @Column(name = "mode_type", nullable = false)
    private ModeType modeType;

    /**
     * 竞价类型
     */
    @NotNull
    @ApiModelProperty(value = "竞价类型", required = true)
    @Enumerated(EnumType.STRING)
    @Column(name = "bidding_type", nullable = false)
    private BiddingType biddingType;

    /**
     * 开立日期
     */
    @NotNull
    @ApiModelProperty(value = "开立日期", required = true)
    @Column(name = "issuance_date", nullable = false)
    private LocalDate issuanceDate;

    /**
     * 竞价轮数
     */
    @ApiModelProperty(value = "竞价轮数")
    @Column(name = "bidding_times")
    private Integer biddingTimes;

    /**
     * 总轮数
     */
    @ApiModelProperty(value = "总轮数")
    @Column(name = "sum_times")
    private Integer sumTimes;

    /**
     * 每次竞价可报价次数
     */
    @ApiModelProperty(value = "每次竞价可报价次数")
    @Column(name = "max_quotation_times")
    private Integer maxQuotationTimes;

    /**
     * 竞价状态
     */
    @NotNull
    @ApiModelProperty(value = "竞价状态", required = true)
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private BiddingStatus status;

    /**
     * 是否必需填写成本清单
     */
    @ApiModelProperty(value = "是否必需填写成本清单")
    @Column(name = "cost_list_required")
    private Boolean costListRequired;

    /**
     * 竞价说明
     */
    @ApiModelProperty(value = "竞价说明")
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
     * 询报价单和比价表一对一
     */
    @ApiModelProperty(value = "询报价单和比价表一对一")
    @OneToOne
    @JoinColumn(unique = true)
    private PriceCompare priceCompare;

    /**
     * 一个询报价单有多个开启人
     */
    @ApiModelProperty(value = "一个询报价单有多个开启人")
    @OneToMany(mappedBy = "bqHeader")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Opener> openers = new HashSet<>();

    /**
     * 一个询报价单有多个竞价轮次详情
     */
    @ApiModelProperty(value = "一个询报价单有多个竞价轮次详情")
    @OneToMany(mappedBy = "header")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<BiddingRoundInfo> roundInfos = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBiddingCode() {
        return biddingCode;
    }

    public BiddingQuotationHeader biddingCode(String biddingCode) {
        this.biddingCode = biddingCode;
        return this;
    }

    public void setBiddingCode(String biddingCode) {
        this.biddingCode = biddingCode;
    }

    public ModeType getModeType() {
        return modeType;
    }

    public BiddingQuotationHeader modeType(ModeType modeType) {
        this.modeType = modeType;
        return this;
    }

    public void setModeType(ModeType modeType) {
        this.modeType = modeType;
    }

    public BiddingType getBiddingType() {
        return biddingType;
    }

    public BiddingQuotationHeader biddingType(BiddingType biddingType) {
        this.biddingType = biddingType;
        return this;
    }

    public void setBiddingType(BiddingType biddingType) {
        this.biddingType = biddingType;
    }

    public LocalDate getIssuanceDate() {
        return issuanceDate;
    }

    public BiddingQuotationHeader issuanceDate(LocalDate issuanceDate) {
        this.issuanceDate = issuanceDate;
        return this;
    }

    public void setIssuanceDate(LocalDate issuanceDate) {
        this.issuanceDate = issuanceDate;
    }

    public Integer getBiddingTimes() {
        return biddingTimes;
    }

    public BiddingQuotationHeader biddingTimes(Integer biddingTimes) {
        this.biddingTimes = biddingTimes;
        return this;
    }

    public void setBiddingTimes(Integer biddingTimes) {
        this.biddingTimes = biddingTimes;
    }

    public Integer getSumTimes() {
        return sumTimes;
    }

    public BiddingQuotationHeader sumTimes(Integer sumTimes) {
        this.sumTimes = sumTimes;
        return this;
    }

    public void setSumTimes(Integer sumTimes) {
        this.sumTimes = sumTimes;
    }

    public Integer getMaxQuotationTimes() {
        return maxQuotationTimes;
    }

    public BiddingQuotationHeader maxQuotationTimes(Integer maxQuotationTimes) {
        this.maxQuotationTimes = maxQuotationTimes;
        return this;
    }

    public void setMaxQuotationTimes(Integer maxQuotationTimes) {
        this.maxQuotationTimes = maxQuotationTimes;
    }

    public BiddingStatus getStatus() {
        return status;
    }

    public BiddingQuotationHeader status(BiddingStatus status) {
        this.status = status;
        return this;
    }

    public void setStatus(BiddingStatus status) {
        this.status = status;
    }

    public Boolean isCostListRequired() {
        return costListRequired;
    }

    public BiddingQuotationHeader costListRequired(Boolean costListRequired) {
        this.costListRequired = costListRequired;
        return this;
    }

    public void setCostListRequired(Boolean costListRequired) {
        this.costListRequired = costListRequired;
    }

    public String getDescription() {
        return description;
    }

    public BiddingQuotationHeader description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public BiddingQuotationHeader createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public BiddingQuotationHeader createdDate(Instant createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public BiddingQuotationHeader lastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
        return this;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public Instant getLastModifiedDate() {
        return lastModifiedDate;
    }

    public BiddingQuotationHeader lastModifiedDate(Instant lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
        return this;
    }

    public void setLastModifiedDate(Instant lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public PriceCompare getPriceCompare() {
        return priceCompare;
    }

    public BiddingQuotationHeader priceCompare(PriceCompare priceCompare) {
        this.priceCompare = priceCompare;
        return this;
    }

    public void setPriceCompare(PriceCompare priceCompare) {
        this.priceCompare = priceCompare;
    }

    public Set<Opener> getOpeners() {
        return openers;
    }

    public BiddingQuotationHeader openers(Set<Opener> openers) {
        this.openers = openers;
        return this;
    }

    public BiddingQuotationHeader addOpener(Opener opener) {
        this.openers.add(opener);
        opener.setBqHeader(this);
        return this;
    }

    public BiddingQuotationHeader removeOpener(Opener opener) {
        this.openers.remove(opener);
        opener.setBqHeader(null);
        return this;
    }

    public void setOpeners(Set<Opener> openers) {
        this.openers = openers;
    }

    public Set<BiddingRoundInfo> getRoundInfos() {
        return roundInfos;
    }

    public BiddingQuotationHeader roundInfos(Set<BiddingRoundInfo> biddingRoundInfos) {
        this.roundInfos = biddingRoundInfos;
        return this;
    }

    public BiddingQuotationHeader addRoundInfo(BiddingRoundInfo biddingRoundInfo) {
        this.roundInfos.add(biddingRoundInfo);
        biddingRoundInfo.setHeader(this);
        return this;
    }

    public BiddingQuotationHeader removeRoundInfo(BiddingRoundInfo biddingRoundInfo) {
        this.roundInfos.remove(biddingRoundInfo);
        biddingRoundInfo.setHeader(null);
        return this;
    }

    public void setRoundInfos(Set<BiddingRoundInfo> biddingRoundInfos) {
        this.roundInfos = biddingRoundInfos;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BiddingQuotationHeader)) {
            return false;
        }
        return id != null && id.equals(((BiddingQuotationHeader) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "BiddingQuotationHeader{" +
            "id=" + getId() +
            ", biddingCode='" + getBiddingCode() + "'" +
            ", modeType='" + getModeType() + "'" +
            ", biddingType='" + getBiddingType() + "'" +
            ", issuanceDate='" + getIssuanceDate() + "'" +
            ", biddingTimes=" + getBiddingTimes() +
            ", sumTimes=" + getSumTimes() +
            ", maxQuotationTimes=" + getMaxQuotationTimes() +
            ", status='" + getStatus() + "'" +
            ", costListRequired='" + isCostListRequired() + "'" +
            ", description='" + getDescription() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", lastModifiedBy='" + getLastModifiedBy() + "'" +
            ", lastModifiedDate='" + getLastModifiedDate() + "'" +
            "}";
    }
}
