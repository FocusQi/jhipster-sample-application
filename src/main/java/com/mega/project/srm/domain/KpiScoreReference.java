package com.mega.project.srm.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.time.Instant;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * 分值对照表\n@author cq
 */
@ApiModel(description = "分值对照表\n@author cq")
@Entity
@Table(name = "srm_kpi_score_reference")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class KpiScoreReference implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 开始范围
     */
    @NotNull
    @ApiModelProperty(value = "开始范围", required = true)
    @Column(name = "start_range", nullable = false)
    private Integer startRange;

    /**
     * 结束范围
     */
    @NotNull
    @ApiModelProperty(value = "结束范围", required = true)
    @Column(name = "end_range", nullable = false)
    private Instant endRange;

    /**
     * 得分
     */
    @ApiModelProperty(value = "得分")
    @Column(name = "score")
    private Integer score;

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
    @JsonIgnoreProperties(value = "scoreReferences", allowSetters = true)
    private KpiScoreCalConfigItem scoreCalConfigItem;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getStartRange() {
        return startRange;
    }

    public KpiScoreReference startRange(Integer startRange) {
        this.startRange = startRange;
        return this;
    }

    public void setStartRange(Integer startRange) {
        this.startRange = startRange;
    }

    public Instant getEndRange() {
        return endRange;
    }

    public KpiScoreReference endRange(Instant endRange) {
        this.endRange = endRange;
        return this;
    }

    public void setEndRange(Instant endRange) {
        this.endRange = endRange;
    }

    public Integer getScore() {
        return score;
    }

    public KpiScoreReference score(Integer score) {
        this.score = score;
        return this;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public KpiScoreReference createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public KpiScoreReference createdDate(Instant createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public KpiScoreReference lastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
        return this;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public Instant getLastModifiedDate() {
        return lastModifiedDate;
    }

    public KpiScoreReference lastModifiedDate(Instant lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
        return this;
    }

    public void setLastModifiedDate(Instant lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public KpiScoreCalConfigItem getScoreCalConfigItem() {
        return scoreCalConfigItem;
    }

    public KpiScoreReference scoreCalConfigItem(KpiScoreCalConfigItem kpiScoreCalConfigItem) {
        this.scoreCalConfigItem = kpiScoreCalConfigItem;
        return this;
    }

    public void setScoreCalConfigItem(KpiScoreCalConfigItem kpiScoreCalConfigItem) {
        this.scoreCalConfigItem = kpiScoreCalConfigItem;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof KpiScoreReference)) {
            return false;
        }
        return id != null && id.equals(((KpiScoreReference) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "KpiScoreReference{" +
            "id=" + getId() +
            ", startRange=" + getStartRange() +
            ", endRange='" + getEndRange() + "'" +
            ", score=" + getScore() +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", lastModifiedBy='" + getLastModifiedBy() + "'" +
            ", lastModifiedDate='" + getLastModifiedDate() + "'" +
            "}";
    }
}
