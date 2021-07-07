package com.mega.project.srm.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mega.project.srm.domain.enumeration.ImproveReportStatus;
import com.mega.project.srm.domain.enumeration.ImproveType;
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
 * 绩效改善报告\n@author cq
 */
@ApiModel(description = "绩效改善报告\n@author cq")
@Entity
@Table(name = "srm_kpi_improve_report")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class KpiImproveReport implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 绩效改善类型
     */
    @NotNull
    @ApiModelProperty(value = "绩效改善类型", required = true)
    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private ImproveType type;

    /**
     * 改善报告审批时间
     */
    @ApiModelProperty(value = "改善报告审批时间")
    @Column(name = "approval_date")
    private LocalDate approvalDate;

    /**
     * 审批次数
     */
    @NotNull
    @ApiModelProperty(value = "审批次数", required = true)
    @Column(name = "approval_times", nullable = false)
    private Integer approvalTimes;

    /**
     * 改善报告建立日期
     */
    @ApiModelProperty(value = "改善报告建立日期")
    @Column(name = "create_time")
    private LocalDate createTime;

    /**
     * 上传改善报告有效期
     */
    @ApiModelProperty(value = "上传改善报告有效期")
    @Column(name = "valid_date")
    private LocalDate validDate;

    /**
     * 状态
     */
    @NotNull
    @ApiModelProperty(value = "状态", required = true)
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private ImproveReportStatus status;

    /**
     * 绩效改善报告模板
     */
    @ApiModelProperty(value = "绩效改善报告模板")
    @Column(name = "template_file_path")
    private String templateFilePath;

    /**
     * 异常编码
     */
    @ApiModelProperty(value = "异常编码")
    @Column(name = "abnormal_code")
    private String abnormalCode;

    /**
     * 异常添加日期
     */
    @ApiModelProperty(value = "异常添加日期")
    @Column(name = "abnormal_create_date")
    private LocalDate abnormalCreateDate;

    /**
     * 异常分类
     */
    @ApiModelProperty(value = "异常分类")
    @Column(name = "abnormal_type")
    private String abnormalType;

    /**
     * 异常等级
     */
    @ApiModelProperty(value = "异常等级")
    @Column(name = "abnormal_grade")
    private String abnormalGrade;

    /**
     * 异常名称
     */
    @ApiModelProperty(value = "异常名称")
    @Column(name = "abnormal_name")
    private String abnormalName;

    /**
     * 异常内容
     */
    @ApiModelProperty(value = "异常内容")
    @Column(name = "abnormal_content")
    private String abnormalContent;

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
     * 一个【绩效改善报告】有多个【改善报告审批详情】
     */
    @ApiModelProperty(value = "一个【绩效改善报告】有多个【改善报告审批详情】")
    @OneToMany(mappedBy = "improveReport")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<KpiImproveReportInfo> improveReportInfos = new HashSet<>();

    @OneToOne(mappedBy = "improveReport")
    @JsonIgnore
    private KpiAssGroupInfo assGroupInfo;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ImproveType getType() {
        return type;
    }

    public KpiImproveReport type(ImproveType type) {
        this.type = type;
        return this;
    }

    public void setType(ImproveType type) {
        this.type = type;
    }

    public LocalDate getApprovalDate() {
        return approvalDate;
    }

    public KpiImproveReport approvalDate(LocalDate approvalDate) {
        this.approvalDate = approvalDate;
        return this;
    }

    public void setApprovalDate(LocalDate approvalDate) {
        this.approvalDate = approvalDate;
    }

    public Integer getApprovalTimes() {
        return approvalTimes;
    }

    public KpiImproveReport approvalTimes(Integer approvalTimes) {
        this.approvalTimes = approvalTimes;
        return this;
    }

    public void setApprovalTimes(Integer approvalTimes) {
        this.approvalTimes = approvalTimes;
    }

    public LocalDate getCreateTime() {
        return createTime;
    }

    public KpiImproveReport createTime(LocalDate createTime) {
        this.createTime = createTime;
        return this;
    }

    public void setCreateTime(LocalDate createTime) {
        this.createTime = createTime;
    }

    public LocalDate getValidDate() {
        return validDate;
    }

    public KpiImproveReport validDate(LocalDate validDate) {
        this.validDate = validDate;
        return this;
    }

    public void setValidDate(LocalDate validDate) {
        this.validDate = validDate;
    }

    public ImproveReportStatus getStatus() {
        return status;
    }

    public KpiImproveReport status(ImproveReportStatus status) {
        this.status = status;
        return this;
    }

    public void setStatus(ImproveReportStatus status) {
        this.status = status;
    }

    public String getTemplateFilePath() {
        return templateFilePath;
    }

    public KpiImproveReport templateFilePath(String templateFilePath) {
        this.templateFilePath = templateFilePath;
        return this;
    }

    public void setTemplateFilePath(String templateFilePath) {
        this.templateFilePath = templateFilePath;
    }

    public String getAbnormalCode() {
        return abnormalCode;
    }

    public KpiImproveReport abnormalCode(String abnormalCode) {
        this.abnormalCode = abnormalCode;
        return this;
    }

    public void setAbnormalCode(String abnormalCode) {
        this.abnormalCode = abnormalCode;
    }

    public LocalDate getAbnormalCreateDate() {
        return abnormalCreateDate;
    }

    public KpiImproveReport abnormalCreateDate(LocalDate abnormalCreateDate) {
        this.abnormalCreateDate = abnormalCreateDate;
        return this;
    }

    public void setAbnormalCreateDate(LocalDate abnormalCreateDate) {
        this.abnormalCreateDate = abnormalCreateDate;
    }

    public String getAbnormalType() {
        return abnormalType;
    }

    public KpiImproveReport abnormalType(String abnormalType) {
        this.abnormalType = abnormalType;
        return this;
    }

    public void setAbnormalType(String abnormalType) {
        this.abnormalType = abnormalType;
    }

    public String getAbnormalGrade() {
        return abnormalGrade;
    }

    public KpiImproveReport abnormalGrade(String abnormalGrade) {
        this.abnormalGrade = abnormalGrade;
        return this;
    }

    public void setAbnormalGrade(String abnormalGrade) {
        this.abnormalGrade = abnormalGrade;
    }

    public String getAbnormalName() {
        return abnormalName;
    }

    public KpiImproveReport abnormalName(String abnormalName) {
        this.abnormalName = abnormalName;
        return this;
    }

    public void setAbnormalName(String abnormalName) {
        this.abnormalName = abnormalName;
    }

    public String getAbnormalContent() {
        return abnormalContent;
    }

    public KpiImproveReport abnormalContent(String abnormalContent) {
        this.abnormalContent = abnormalContent;
        return this;
    }

    public void setAbnormalContent(String abnormalContent) {
        this.abnormalContent = abnormalContent;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public KpiImproveReport createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public KpiImproveReport createdDate(Instant createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public KpiImproveReport lastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
        return this;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public Instant getLastModifiedDate() {
        return lastModifiedDate;
    }

    public KpiImproveReport lastModifiedDate(Instant lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
        return this;
    }

    public void setLastModifiedDate(Instant lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public Set<KpiImproveReportInfo> getImproveReportInfos() {
        return improveReportInfos;
    }

    public KpiImproveReport improveReportInfos(Set<KpiImproveReportInfo> kpiImproveReportInfos) {
        this.improveReportInfos = kpiImproveReportInfos;
        return this;
    }

    public KpiImproveReport addImproveReportInfo(KpiImproveReportInfo kpiImproveReportInfo) {
        this.improveReportInfos.add(kpiImproveReportInfo);
        kpiImproveReportInfo.setImproveReport(this);
        return this;
    }

    public KpiImproveReport removeImproveReportInfo(KpiImproveReportInfo kpiImproveReportInfo) {
        this.improveReportInfos.remove(kpiImproveReportInfo);
        kpiImproveReportInfo.setImproveReport(null);
        return this;
    }

    public void setImproveReportInfos(Set<KpiImproveReportInfo> kpiImproveReportInfos) {
        this.improveReportInfos = kpiImproveReportInfos;
    }

    public KpiAssGroupInfo getAssGroupInfo() {
        return assGroupInfo;
    }

    public KpiImproveReport assGroupInfo(KpiAssGroupInfo kpiAssGroupInfo) {
        this.assGroupInfo = kpiAssGroupInfo;
        return this;
    }

    public void setAssGroupInfo(KpiAssGroupInfo kpiAssGroupInfo) {
        this.assGroupInfo = kpiAssGroupInfo;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof KpiImproveReport)) {
            return false;
        }
        return id != null && id.equals(((KpiImproveReport) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "KpiImproveReport{" +
            "id=" + getId() +
            ", type='" + getType() + "'" +
            ", approvalDate='" + getApprovalDate() + "'" +
            ", approvalTimes=" + getApprovalTimes() +
            ", createTime='" + getCreateTime() + "'" +
            ", validDate='" + getValidDate() + "'" +
            ", status='" + getStatus() + "'" +
            ", templateFilePath='" + getTemplateFilePath() + "'" +
            ", abnormalCode='" + getAbnormalCode() + "'" +
            ", abnormalCreateDate='" + getAbnormalCreateDate() + "'" +
            ", abnormalType='" + getAbnormalType() + "'" +
            ", abnormalGrade='" + getAbnormalGrade() + "'" +
            ", abnormalName='" + getAbnormalName() + "'" +
            ", abnormalContent='" + getAbnormalContent() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", lastModifiedBy='" + getLastModifiedBy() + "'" +
            ", lastModifiedDate='" + getLastModifiedDate() + "'" +
            "}";
    }
}
