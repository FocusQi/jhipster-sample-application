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
 * 分数等级配置\n@author cq
 */
@ApiModel(description = "分数等级配置\n@author cq")
@Entity
@Table(name = "srm_kpi_score_grade_config")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class KpiScoreGradeConfig implements Serializable {
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
     * 等级
     */
    @NotNull
    @ApiModelProperty(value = "等级", required = true)
    @Column(name = "grade", nullable = false)
    private String grade;

    /**
     * 绩效改善
     */
    @ApiModelProperty(value = "绩效改善")
    @Column(name = "need_improve")
    private Boolean needImprove;

    /**
     * 等级说明
     */
    @ApiModelProperty(value = "等级说明")
    @Column(name = "grade_description")
    private String gradeDescription;

    /**
     * 操作说明
     */
    @ApiModelProperty(value = "操作说明")
    @Column(name = "oper_description")
    private String operDescription;

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
    @JsonIgnoreProperties(value = "scoreGradeConfigs", allowSetters = true)
    private KpiTemplateGroup templateGroup;

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

    public KpiScoreGradeConfig startRange(Integer startRange) {
        this.startRange = startRange;
        return this;
    }

    public void setStartRange(Integer startRange) {
        this.startRange = startRange;
    }

    public Instant getEndRange() {
        return endRange;
    }

    public KpiScoreGradeConfig endRange(Instant endRange) {
        this.endRange = endRange;
        return this;
    }

    public void setEndRange(Instant endRange) {
        this.endRange = endRange;
    }

    public String getGrade() {
        return grade;
    }

    public KpiScoreGradeConfig grade(String grade) {
        this.grade = grade;
        return this;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public Boolean isNeedImprove() {
        return needImprove;
    }

    public KpiScoreGradeConfig needImprove(Boolean needImprove) {
        this.needImprove = needImprove;
        return this;
    }

    public void setNeedImprove(Boolean needImprove) {
        this.needImprove = needImprove;
    }

    public String getGradeDescription() {
        return gradeDescription;
    }

    public KpiScoreGradeConfig gradeDescription(String gradeDescription) {
        this.gradeDescription = gradeDescription;
        return this;
    }

    public void setGradeDescription(String gradeDescription) {
        this.gradeDescription = gradeDescription;
    }

    public String getOperDescription() {
        return operDescription;
    }

    public KpiScoreGradeConfig operDescription(String operDescription) {
        this.operDescription = operDescription;
        return this;
    }

    public void setOperDescription(String operDescription) {
        this.operDescription = operDescription;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public KpiScoreGradeConfig createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public KpiScoreGradeConfig createdDate(Instant createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public KpiScoreGradeConfig lastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
        return this;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public Instant getLastModifiedDate() {
        return lastModifiedDate;
    }

    public KpiScoreGradeConfig lastModifiedDate(Instant lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
        return this;
    }

    public void setLastModifiedDate(Instant lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public KpiTemplateGroup getTemplateGroup() {
        return templateGroup;
    }

    public KpiScoreGradeConfig templateGroup(KpiTemplateGroup kpiTemplateGroup) {
        this.templateGroup = kpiTemplateGroup;
        return this;
    }

    public void setTemplateGroup(KpiTemplateGroup kpiTemplateGroup) {
        this.templateGroup = kpiTemplateGroup;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof KpiScoreGradeConfig)) {
            return false;
        }
        return id != null && id.equals(((KpiScoreGradeConfig) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "KpiScoreGradeConfig{" +
            "id=" + getId() +
            ", startRange=" + getStartRange() +
            ", endRange='" + getEndRange() + "'" +
            ", grade='" + getGrade() + "'" +
            ", needImprove='" + isNeedImprove() + "'" +
            ", gradeDescription='" + getGradeDescription() + "'" +
            ", operDescription='" + getOperDescription() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", lastModifiedBy='" + getLastModifiedBy() + "'" +
            ", lastModifiedDate='" + getLastModifiedDate() + "'" +
            "}";
    }
}
