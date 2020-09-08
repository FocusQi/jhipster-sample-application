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
 * 考核群组\n@author cq
 */
@ApiModel(description = "考核群组\n@author cq")
@Entity
@Table(name = "srm_kpi_ass_group")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class KpiAssGroup implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 考核群组单号
     */
    @NotNull
    @ApiModelProperty(value = "考核群组单号", required = true)
    @Column(name = "ag_code", nullable = false)
    private String agCode;

    /**
     * 考核年度
     */
    @NotNull
    @ApiModelProperty(value = "考核年度", required = true)
    @Column(name = "ag_year", nullable = false)
    private String agYear;

    /**
     * 考核类型
     */
    @NotNull
    @ApiModelProperty(value = "考核类型", required = true)
    @Column(name = "ag_type", nullable = false)
    private String agType;

    /**
     * 考核月/季度
     */
    @ApiModelProperty(value = "考核月/季度")
    @Column(name = "ag_period")
    private String agPeriod;

    /**
     * 考核开始日期
     */
    @NotNull
    @ApiModelProperty(value = "考核开始日期", required = true)
    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    /**
     * 考核结束日期
     */
    @NotNull
    @ApiModelProperty(value = "考核结束日期", required = true)
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

    /**
     * 一个【考核群组】有多个【考核模板组详情】
     */
    @ApiModelProperty(value = "一个【考核群组】有多个【考核模板组详情】")
    @OneToMany(mappedBy = "assGroup")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<KpiAssGroupInfo> assGroupInfos = new HashSet<>();

    /**
     * 多个【考核群组】属于一个【模板组】
     */
    @ApiModelProperty(value = "多个【考核群组】属于一个【模板组】")
    @ManyToOne
    @JsonIgnoreProperties(value = "kpiAssGroups", allowSetters = true)
    private KpiTemplateGroup templateGroup;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAgCode() {
        return agCode;
    }

    public KpiAssGroup agCode(String agCode) {
        this.agCode = agCode;
        return this;
    }

    public void setAgCode(String agCode) {
        this.agCode = agCode;
    }

    public String getAgYear() {
        return agYear;
    }

    public KpiAssGroup agYear(String agYear) {
        this.agYear = agYear;
        return this;
    }

    public void setAgYear(String agYear) {
        this.agYear = agYear;
    }

    public String getAgType() {
        return agType;
    }

    public KpiAssGroup agType(String agType) {
        this.agType = agType;
        return this;
    }

    public void setAgType(String agType) {
        this.agType = agType;
    }

    public String getAgPeriod() {
        return agPeriod;
    }

    public KpiAssGroup agPeriod(String agPeriod) {
        this.agPeriod = agPeriod;
        return this;
    }

    public void setAgPeriod(String agPeriod) {
        this.agPeriod = agPeriod;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public KpiAssGroup startDate(LocalDate startDate) {
        this.startDate = startDate;
        return this;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public KpiAssGroup endDate(LocalDate endDate) {
        this.endDate = endDate;
        return this;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public AssGroupStatus getStatus() {
        return status;
    }

    public KpiAssGroup status(AssGroupStatus status) {
        this.status = status;
        return this;
    }

    public void setStatus(AssGroupStatus status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public KpiAssGroup remark(String remark) {
        this.remark = remark;
        return this;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public KpiAssGroup createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public KpiAssGroup createdDate(Instant createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public KpiAssGroup lastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
        return this;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public Instant getLastModifiedDate() {
        return lastModifiedDate;
    }

    public KpiAssGroup lastModifiedDate(Instant lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
        return this;
    }

    public void setLastModifiedDate(Instant lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public Set<KpiAssGroupInfo> getAssGroupInfos() {
        return assGroupInfos;
    }

    public KpiAssGroup assGroupInfos(Set<KpiAssGroupInfo> kpiAssGroupInfos) {
        this.assGroupInfos = kpiAssGroupInfos;
        return this;
    }

    public KpiAssGroup addAssGroupInfo(KpiAssGroupInfo kpiAssGroupInfo) {
        this.assGroupInfos.add(kpiAssGroupInfo);
        kpiAssGroupInfo.setAssGroup(this);
        return this;
    }

    public KpiAssGroup removeAssGroupInfo(KpiAssGroupInfo kpiAssGroupInfo) {
        this.assGroupInfos.remove(kpiAssGroupInfo);
        kpiAssGroupInfo.setAssGroup(null);
        return this;
    }

    public void setAssGroupInfos(Set<KpiAssGroupInfo> kpiAssGroupInfos) {
        this.assGroupInfos = kpiAssGroupInfos;
    }

    public KpiTemplateGroup getTemplateGroup() {
        return templateGroup;
    }

    public KpiAssGroup templateGroup(KpiTemplateGroup kpiTemplateGroup) {
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
        if (!(o instanceof KpiAssGroup)) {
            return false;
        }
        return id != null && id.equals(((KpiAssGroup) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "KpiAssGroup{" +
            "id=" + getId() +
            ", agCode='" + getAgCode() + "'" +
            ", agYear='" + getAgYear() + "'" +
            ", agType='" + getAgType() + "'" +
            ", agPeriod='" + getAgPeriod() + "'" +
            ", startDate='" + getStartDate() + "'" +
            ", endDate='" + getEndDate() + "'" +
            ", status='" + getStatus() + "'" +
            ", remark='" + getRemark() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", lastModifiedBy='" + getLastModifiedBy() + "'" +
            ", lastModifiedDate='" + getLastModifiedDate() + "'" +
            "}";
    }
}
