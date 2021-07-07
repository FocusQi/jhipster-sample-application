package com.mega.project.srm.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mega.project.srm.domain.enumeration.CalculationItemType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.time.Instant;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * 分数详情\n@author cq
 */
@ApiModel(description = "分数详情\n@author cq")
@Entity
@Table(name = "srm_kpi_score_info")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class KpiScoreInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 计算类型
     */
    @NotNull
    @ApiModelProperty(value = "计算类型", required = true)
    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private CalculationItemType type;

    /**
     * 计算项名称
     */
    @NotNull
    @ApiModelProperty(value = "计算项名称", required = true)
    @Column(name = "cal_item_name", nullable = false)
    private String calItemName;

    /**
     * 权重
     */
    @NotNull
    @ApiModelProperty(value = "权重", required = true)
    @Column(name = "weight", nullable = false)
    private Integer weight;

    /**
     * 得分
     */
    @ApiModelProperty(value = "得分")
    @Column(name = "score")
    private Integer score;

    /**
     * 调整分数
     */
    @ApiModelProperty(value = "调整分数")
    @Column(name = "change_score")
    private Integer changeScore;

    /**
     * 调整原因
     */
    @ApiModelProperty(value = "调整原因")
    @Column(name = "change_reason")
    private String changeReason;

    /**
     * 是否改变
     */
    @ApiModelProperty(value = "是否改变")
    @Column(name = "is_change")
    private Boolean isChange;

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
    @JsonIgnoreProperties(value = "scoreInfos", allowSetters = true)
    private KpiAssTemplateInfo assTemplateInfo;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CalculationItemType getType() {
        return type;
    }

    public KpiScoreInfo type(CalculationItemType type) {
        this.type = type;
        return this;
    }

    public void setType(CalculationItemType type) {
        this.type = type;
    }

    public String getCalItemName() {
        return calItemName;
    }

    public KpiScoreInfo calItemName(String calItemName) {
        this.calItemName = calItemName;
        return this;
    }

    public void setCalItemName(String calItemName) {
        this.calItemName = calItemName;
    }

    public Integer getWeight() {
        return weight;
    }

    public KpiScoreInfo weight(Integer weight) {
        this.weight = weight;
        return this;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Integer getScore() {
        return score;
    }

    public KpiScoreInfo score(Integer score) {
        this.score = score;
        return this;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getChangeScore() {
        return changeScore;
    }

    public KpiScoreInfo changeScore(Integer changeScore) {
        this.changeScore = changeScore;
        return this;
    }

    public void setChangeScore(Integer changeScore) {
        this.changeScore = changeScore;
    }

    public String getChangeReason() {
        return changeReason;
    }

    public KpiScoreInfo changeReason(String changeReason) {
        this.changeReason = changeReason;
        return this;
    }

    public void setChangeReason(String changeReason) {
        this.changeReason = changeReason;
    }

    public Boolean isIsChange() {
        return isChange;
    }

    public KpiScoreInfo isChange(Boolean isChange) {
        this.isChange = isChange;
        return this;
    }

    public void setIsChange(Boolean isChange) {
        this.isChange = isChange;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public KpiScoreInfo createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public KpiScoreInfo createdDate(Instant createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public KpiScoreInfo lastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
        return this;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public Instant getLastModifiedDate() {
        return lastModifiedDate;
    }

    public KpiScoreInfo lastModifiedDate(Instant lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
        return this;
    }

    public void setLastModifiedDate(Instant lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public KpiAssTemplateInfo getAssTemplateInfo() {
        return assTemplateInfo;
    }

    public KpiScoreInfo assTemplateInfo(KpiAssTemplateInfo kpiAssTemplateInfo) {
        this.assTemplateInfo = kpiAssTemplateInfo;
        return this;
    }

    public void setAssTemplateInfo(KpiAssTemplateInfo kpiAssTemplateInfo) {
        this.assTemplateInfo = kpiAssTemplateInfo;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof KpiScoreInfo)) {
            return false;
        }
        return id != null && id.equals(((KpiScoreInfo) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "KpiScoreInfo{" +
            "id=" + getId() +
            ", type='" + getType() + "'" +
            ", calItemName='" + getCalItemName() + "'" +
            ", weight=" + getWeight() +
            ", score=" + getScore() +
            ", changeScore=" + getChangeScore() +
            ", changeReason='" + getChangeReason() + "'" +
            ", isChange='" + isIsChange() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", lastModifiedBy='" + getLastModifiedBy() + "'" +
            ", lastModifiedDate='" + getLastModifiedDate() + "'" +
            "}";
    }
}
