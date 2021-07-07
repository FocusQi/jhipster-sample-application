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
 * 分数计算模板配置项\n@author cq
 */
@ApiModel(description = "分数计算模板配置项\n@author cq")
@Entity
@Table(name = "srm_kpi_score_cal_config_item")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class KpiScoreCalConfigItem implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 单位
     */
    @ApiModelProperty(value = "单位")
    @Column(name = "unit_type")
    private String unitType;

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
     * 一个【分数计算模板配置项】有多个【分值对照表】
     */
    @ApiModelProperty(value = "一个【分数计算模板配置项】有多个【分值对照表】")
    @OneToMany(mappedBy = "scoreCalConfigItem")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<KpiScoreReference> scoreReferences = new HashSet<>();

    /**
     * 多个【分数计算模板配置项】属于一个【计算项】
     */
    @ApiModelProperty(value = "多个【分数计算模板配置项】属于一个【计算项】")
    @ManyToOne
    @JsonIgnoreProperties(value = "kpiScoreCalConfigItems", allowSetters = true)
    private KpiCalItem calItem;

    @ManyToOne
    @JsonIgnoreProperties(value = "scoreCalConfigItems", allowSetters = true)
    private KpiScoreCalConfig scoreCalConfig;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUnitType() {
        return unitType;
    }

    public KpiScoreCalConfigItem unitType(String unitType) {
        this.unitType = unitType;
        return this;
    }

    public void setUnitType(String unitType) {
        this.unitType = unitType;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public KpiScoreCalConfigItem createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public KpiScoreCalConfigItem createdDate(Instant createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public KpiScoreCalConfigItem lastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
        return this;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public Instant getLastModifiedDate() {
        return lastModifiedDate;
    }

    public KpiScoreCalConfigItem lastModifiedDate(Instant lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
        return this;
    }

    public void setLastModifiedDate(Instant lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public Set<KpiScoreReference> getScoreReferences() {
        return scoreReferences;
    }

    public KpiScoreCalConfigItem scoreReferences(Set<KpiScoreReference> kpiScoreReferences) {
        this.scoreReferences = kpiScoreReferences;
        return this;
    }

    public KpiScoreCalConfigItem addScoreReference(KpiScoreReference kpiScoreReference) {
        this.scoreReferences.add(kpiScoreReference);
        kpiScoreReference.setScoreCalConfigItem(this);
        return this;
    }

    public KpiScoreCalConfigItem removeScoreReference(KpiScoreReference kpiScoreReference) {
        this.scoreReferences.remove(kpiScoreReference);
        kpiScoreReference.setScoreCalConfigItem(null);
        return this;
    }

    public void setScoreReferences(Set<KpiScoreReference> kpiScoreReferences) {
        this.scoreReferences = kpiScoreReferences;
    }

    public KpiCalItem getCalItem() {
        return calItem;
    }

    public KpiScoreCalConfigItem calItem(KpiCalItem kpiCalItem) {
        this.calItem = kpiCalItem;
        return this;
    }

    public void setCalItem(KpiCalItem kpiCalItem) {
        this.calItem = kpiCalItem;
    }

    public KpiScoreCalConfig getScoreCalConfig() {
        return scoreCalConfig;
    }

    public KpiScoreCalConfigItem scoreCalConfig(KpiScoreCalConfig kpiScoreCalConfig) {
        this.scoreCalConfig = kpiScoreCalConfig;
        return this;
    }

    public void setScoreCalConfig(KpiScoreCalConfig kpiScoreCalConfig) {
        this.scoreCalConfig = kpiScoreCalConfig;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof KpiScoreCalConfigItem)) {
            return false;
        }
        return id != null && id.equals(((KpiScoreCalConfigItem) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "KpiScoreCalConfigItem{" +
            "id=" + getId() +
            ", unitType='" + getUnitType() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", lastModifiedBy='" + getLastModifiedBy() + "'" +
            ", lastModifiedDate='" + getLastModifiedDate() + "'" +
            "}";
    }
}
