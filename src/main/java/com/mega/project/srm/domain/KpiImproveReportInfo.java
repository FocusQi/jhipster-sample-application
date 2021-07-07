package com.mega.project.srm.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mega.project.srm.domain.enumeration.ApprovalStatus;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * 改善报告审批详情\n@author cq
 */
@ApiModel(description = "改善报告审批详情\n@author cq")
@Entity
@Table(name = "srm_kpi_improve_report_info")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class KpiImproveReportInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 报告回复时间
     */
    @ApiModelProperty(value = "报告回复时间")
    @Column(name = "reply_date")
    private LocalDate replyDate;

    /**
     * 审批时间
     */
    @ApiModelProperty(value = "审批时间")
    @Column(name = "approval_date")
    private LocalDate approvalDate;

    /**
     * 审批结果
     */
    @ApiModelProperty(value = "审批结果")
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private ApprovalStatus status;

    /**
     * 改善报告文件地址
     */
    @ApiModelProperty(value = "改善报告文件地址")
    @Column(name = "file_path")
    private String filePath;

    /**
     * 改善报告评价
     */
    @ApiModelProperty(value = "改善报告评价")
    @Column(name = "improve_appraise")
    private String improveAppraise;

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
    @JsonIgnoreProperties(value = "improveReportInfos", allowSetters = true)
    private KpiImproveReport improveReport;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getReplyDate() {
        return replyDate;
    }

    public KpiImproveReportInfo replyDate(LocalDate replyDate) {
        this.replyDate = replyDate;
        return this;
    }

    public void setReplyDate(LocalDate replyDate) {
        this.replyDate = replyDate;
    }

    public LocalDate getApprovalDate() {
        return approvalDate;
    }

    public KpiImproveReportInfo approvalDate(LocalDate approvalDate) {
        this.approvalDate = approvalDate;
        return this;
    }

    public void setApprovalDate(LocalDate approvalDate) {
        this.approvalDate = approvalDate;
    }

    public ApprovalStatus getStatus() {
        return status;
    }

    public KpiImproveReportInfo status(ApprovalStatus status) {
        this.status = status;
        return this;
    }

    public void setStatus(ApprovalStatus status) {
        this.status = status;
    }

    public String getFilePath() {
        return filePath;
    }

    public KpiImproveReportInfo filePath(String filePath) {
        this.filePath = filePath;
        return this;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getImproveAppraise() {
        return improveAppraise;
    }

    public KpiImproveReportInfo improveAppraise(String improveAppraise) {
        this.improveAppraise = improveAppraise;
        return this;
    }

    public void setImproveAppraise(String improveAppraise) {
        this.improveAppraise = improveAppraise;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public KpiImproveReportInfo createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public KpiImproveReportInfo createdDate(Instant createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public KpiImproveReportInfo lastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
        return this;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public Instant getLastModifiedDate() {
        return lastModifiedDate;
    }

    public KpiImproveReportInfo lastModifiedDate(Instant lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
        return this;
    }

    public void setLastModifiedDate(Instant lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public KpiImproveReport getImproveReport() {
        return improveReport;
    }

    public KpiImproveReportInfo improveReport(KpiImproveReport kpiImproveReport) {
        this.improveReport = kpiImproveReport;
        return this;
    }

    public void setImproveReport(KpiImproveReport kpiImproveReport) {
        this.improveReport = kpiImproveReport;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof KpiImproveReportInfo)) {
            return false;
        }
        return id != null && id.equals(((KpiImproveReportInfo) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "KpiImproveReportInfo{" +
            "id=" + getId() +
            ", replyDate='" + getReplyDate() + "'" +
            ", approvalDate='" + getApprovalDate() + "'" +
            ", status='" + getStatus() + "'" +
            ", filePath='" + getFilePath() + "'" +
            ", improveAppraise='" + getImproveAppraise() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", lastModifiedBy='" + getLastModifiedBy() + "'" +
            ", lastModifiedDate='" + getLastModifiedDate() + "'" +
            "}";
    }
}
