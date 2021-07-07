package com.mega.project.srm.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mega.project.srm.domain.enumeration.AssGroupStatus;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * 考核模板组详情\n@author cq
 */
@ApiModel(description = "考核模板组详情\n@author cq")
@Entity
@Table(name = "srm_kpi_ass_group_info")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class KpiAssGroupInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 绩效单号
     */
    @NotNull
    @ApiModelProperty(value = "绩效单号", required = true)
    @Column(name = "kpi_code", nullable = false)
    private String kpiCode;

    /**
     * 总得分
     */
    @ApiModelProperty(value = "总得分")
    @Column(name = "total_score")
    private Integer totalScore;

    /**
     * 开始日期
     */
    @NotNull
    @ApiModelProperty(value = "开始日期", required = true)
    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    /**
     * 结束日期
     */
    @NotNull
    @ApiModelProperty(value = "结束日期", required = true)
    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;

    /**
     * 状态
     */
    @NotNull
    @ApiModelProperty(value = "状态", required = true)
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private AssGroupStatus status;

    /**
     * 等级
     */
    @ApiModelProperty(value = "等级")
    @Column(name = "grade")
    private String grade;

    /**
     * 等级说明
     */
    @ApiModelProperty(value = "等级说明")
    @Column(name = "grade_description")
    private String gradeDescription;

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
     * 【考核模板组详情】和【绩效改善报告】一对一
     */
    @ApiModelProperty(value = "【考核模板组详情】和【绩效改善报告】一对一")
    @OneToOne
    @JoinColumn(unique = true)
    private KpiImproveReport improveReport;

    /**
     * 一个【考核模板组详情】有多个【考核模板详情】
     */
    @ApiModelProperty(value = "一个【考核模板组详情】有多个【考核模板详情】")
    @OneToMany(mappedBy = "assGroupInfo")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<KpiAssTemplateGroup> assTemplateGroups = new HashSet<>();

    /**
     * 多个【考核模板组详情】属于一个【供应商】
     */
    @ApiModelProperty(value = "多个【考核模板组详情】属于一个【供应商】")
    @ManyToOne
    @JsonIgnoreProperties(value = "kpiAssGroupInfos", allowSetters = true)
    private Vendor vendor;

    @ManyToOne
    @JsonIgnoreProperties(value = "assGroupInfos", allowSetters = true)
    private KpiAssGroup assGroup;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKpiCode() {
        return kpiCode;
    }

    public KpiAssGroupInfo kpiCode(String kpiCode) {
        this.kpiCode = kpiCode;
        return this;
    }

    public void setKpiCode(String kpiCode) {
        this.kpiCode = kpiCode;
    }

    public Integer getTotalScore() {
        return totalScore;
    }

    public KpiAssGroupInfo totalScore(Integer totalScore) {
        this.totalScore = totalScore;
        return this;
    }

    public void setTotalScore(Integer totalScore) {
        this.totalScore = totalScore;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public KpiAssGroupInfo startDate(LocalDate startDate) {
        this.startDate = startDate;
        return this;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public KpiAssGroupInfo endDate(LocalDate endDate) {
        this.endDate = endDate;
        return this;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public AssGroupStatus getStatus() {
        return status;
    }

    public KpiAssGroupInfo status(AssGroupStatus status) {
        this.status = status;
        return this;
    }

    public void setStatus(AssGroupStatus status) {
        this.status = status;
    }

    public String getGrade() {
        return grade;
    }

    public KpiAssGroupInfo grade(String grade) {
        this.grade = grade;
        return this;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getGradeDescription() {
        return gradeDescription;
    }

    public KpiAssGroupInfo gradeDescription(String gradeDescription) {
        this.gradeDescription = gradeDescription;
        return this;
    }

    public void setGradeDescription(String gradeDescription) {
        this.gradeDescription = gradeDescription;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public KpiAssGroupInfo createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public KpiAssGroupInfo createdDate(Instant createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public KpiAssGroupInfo lastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
        return this;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public Instant getLastModifiedDate() {
        return lastModifiedDate;
    }

    public KpiAssGroupInfo lastModifiedDate(Instant lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
        return this;
    }

    public void setLastModifiedDate(Instant lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public KpiImproveReport getImproveReport() {
        return improveReport;
    }

    public KpiAssGroupInfo improveReport(KpiImproveReport kpiImproveReport) {
        this.improveReport = kpiImproveReport;
        return this;
    }

    public void setImproveReport(KpiImproveReport kpiImproveReport) {
        this.improveReport = kpiImproveReport;
    }

    public Set<KpiAssTemplateGroup> getAssTemplateGroups() {
        return assTemplateGroups;
    }

    public KpiAssGroupInfo assTemplateGroups(Set<KpiAssTemplateGroup> kpiAssTemplateGroups) {
        this.assTemplateGroups = kpiAssTemplateGroups;
        return this;
    }

    public KpiAssGroupInfo addAssTemplateGroup(KpiAssTemplateGroup kpiAssTemplateGroup) {
        this.assTemplateGroups.add(kpiAssTemplateGroup);
        kpiAssTemplateGroup.setAssGroupInfo(this);
        return this;
    }

    public KpiAssGroupInfo removeAssTemplateGroup(KpiAssTemplateGroup kpiAssTemplateGroup) {
        this.assTemplateGroups.remove(kpiAssTemplateGroup);
        kpiAssTemplateGroup.setAssGroupInfo(null);
        return this;
    }

    public void setAssTemplateGroups(Set<KpiAssTemplateGroup> kpiAssTemplateGroups) {
        this.assTemplateGroups = kpiAssTemplateGroups;
    }

    public Vendor getVendor() {
        return vendor;
    }

    public KpiAssGroupInfo vendor(Vendor vendor) {
        this.vendor = vendor;
        return this;
    }

    public void setVendor(Vendor vendor) {
        this.vendor = vendor;
    }

    public KpiAssGroup getAssGroup() {
        return assGroup;
    }

    public KpiAssGroupInfo assGroup(KpiAssGroup kpiAssGroup) {
        this.assGroup = kpiAssGroup;
        return this;
    }

    public void setAssGroup(KpiAssGroup kpiAssGroup) {
        this.assGroup = kpiAssGroup;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof KpiAssGroupInfo)) {
            return false;
        }
        return id != null && id.equals(((KpiAssGroupInfo) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "KpiAssGroupInfo{" +
            "id=" + getId() +
            ", kpiCode='" + getKpiCode() + "'" +
            ", totalScore=" + getTotalScore() +
            ", startDate='" + getStartDate() + "'" +
            ", endDate='" + getEndDate() + "'" +
            ", status='" + getStatus() + "'" +
            ", grade='" + getGrade() + "'" +
            ", gradeDescription='" + getGradeDescription() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", lastModifiedBy='" + getLastModifiedBy() + "'" +
            ", lastModifiedDate='" + getLastModifiedDate() + "'" +
            "}";
    }
}
