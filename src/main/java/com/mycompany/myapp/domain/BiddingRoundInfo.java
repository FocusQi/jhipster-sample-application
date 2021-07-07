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
 * 竞价轮次详情\n@author cq
 */
@ApiModel(description = "竞价轮次详情\n@author cq")
@Entity
@Table(name = "srm_bidding_round_info")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class BiddingRoundInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    /**
     * 轮次
     */
    @NotNull
    @ApiModelProperty(value = "轮次", required = true)
    @Column(name = "round", nullable = false)
    private Integer round;

    /**
     * 开始时间
     */
    @NotNull
    @ApiModelProperty(value = "开始时间", required = true)
    @Column(name = "start_time", nullable = false)
    private Instant startTime;

    /**
     * 结束时间
     */
    @NotNull
    @ApiModelProperty(value = "结束时间", required = true)
    @Column(name = "end_time", nullable = false)
    private Instant endTime;

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
     * 一个竞价轮次详情有多个供应商轮次
     */
    @ApiModelProperty(value = "一个竞价轮次详情有多个供应商轮次")
    @OneToMany(mappedBy = "roundInfo")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<VendorRound> vendorRounds = new HashSet<>();

    /**
     * 一个竞价轮次详情有多个物料伦次信息
     */
    @ApiModelProperty(value = "一个竞价轮次详情有多个物料伦次信息")
    @OneToMany(mappedBy = "roundInfo")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<MaterialRound> materialRounds = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = "roundInfos", allowSetters = true)
    private BiddingQuotationHeader header;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getRound() {
        return round;
    }

    public BiddingRoundInfo round(Integer round) {
        this.round = round;
        return this;
    }

    public void setRound(Integer round) {
        this.round = round;
    }

    public Instant getStartTime() {
        return startTime;
    }

    public BiddingRoundInfo startTime(Instant startTime) {
        this.startTime = startTime;
        return this;
    }

    public void setStartTime(Instant startTime) {
        this.startTime = startTime;
    }

    public Instant getEndTime() {
        return endTime;
    }

    public BiddingRoundInfo endTime(Instant endTime) {
        this.endTime = endTime;
        return this;
    }

    public void setEndTime(Instant endTime) {
        this.endTime = endTime;
    }

    public String getDescription() {
        return description;
    }

    public BiddingRoundInfo description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public BiddingRoundInfo createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public BiddingRoundInfo createdDate(Instant createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public BiddingRoundInfo lastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
        return this;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public Instant getLastModifiedDate() {
        return lastModifiedDate;
    }

    public BiddingRoundInfo lastModifiedDate(Instant lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
        return this;
    }

    public void setLastModifiedDate(Instant lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public Set<VendorRound> getVendorRounds() {
        return vendorRounds;
    }

    public BiddingRoundInfo vendorRounds(Set<VendorRound> vendorRounds) {
        this.vendorRounds = vendorRounds;
        return this;
    }

    public BiddingRoundInfo addVendorRound(VendorRound vendorRound) {
        this.vendorRounds.add(vendorRound);
        vendorRound.setRoundInfo(this);
        return this;
    }

    public BiddingRoundInfo removeVendorRound(VendorRound vendorRound) {
        this.vendorRounds.remove(vendorRound);
        vendorRound.setRoundInfo(null);
        return this;
    }

    public void setVendorRounds(Set<VendorRound> vendorRounds) {
        this.vendorRounds = vendorRounds;
    }

    public Set<MaterialRound> getMaterialRounds() {
        return materialRounds;
    }

    public BiddingRoundInfo materialRounds(Set<MaterialRound> materialRounds) {
        this.materialRounds = materialRounds;
        return this;
    }

    public BiddingRoundInfo addMaterialRound(MaterialRound materialRound) {
        this.materialRounds.add(materialRound);
        materialRound.setRoundInfo(this);
        return this;
    }

    public BiddingRoundInfo removeMaterialRound(MaterialRound materialRound) {
        this.materialRounds.remove(materialRound);
        materialRound.setRoundInfo(null);
        return this;
    }

    public void setMaterialRounds(Set<MaterialRound> materialRounds) {
        this.materialRounds = materialRounds;
    }

    public BiddingQuotationHeader getHeader() {
        return header;
    }

    public BiddingRoundInfo header(BiddingQuotationHeader biddingQuotationHeader) {
        this.header = biddingQuotationHeader;
        return this;
    }

    public void setHeader(BiddingQuotationHeader biddingQuotationHeader) {
        this.header = biddingQuotationHeader;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BiddingRoundInfo)) {
            return false;
        }
        return id != null && id.equals(((BiddingRoundInfo) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "BiddingRoundInfo{" +
            "id=" + getId() +
            ", round=" + getRound() +
            ", startTime='" + getStartTime() + "'" +
            ", endTime='" + getEndTime() + "'" +
            ", description='" + getDescription() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", lastModifiedBy='" + getLastModifiedBy() + "'" +
            ", lastModifiedDate='" + getLastModifiedDate() + "'" +
            "}";
    }
}
