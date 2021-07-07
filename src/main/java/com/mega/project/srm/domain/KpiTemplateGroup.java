package com.mega.project.srm.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * 模板组\n@author cq
 */
@ApiModel(description = "模板组\n@author cq")
@Entity
@Table(name = "srm_kpi_template_group")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class KpiTemplateGroup implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 模板组名称
     */
    @NotNull
    @ApiModelProperty(value = "模板组名称", required = true)
    @Column(name = "template_group_name", nullable = false)
    private String templateGroupName;

    /**
     * 说明
     */
    @ApiModelProperty(value = "说明")
    @Column(name = "description")
    private String description;

    /**
     * 是否启用
     */
    @ApiModelProperty(value = "是否启用")
    @Column(name = "is_active")
    private Boolean isActive;

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
     * 一个【模板组】有多个【分数等级配置】
     */
    @ApiModelProperty(value = "一个【模板组】有多个【分数等级配置】")
    @OneToMany(mappedBy = "templateGroup")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<KpiScoreGradeConfig> scoreGradeConfigs = new HashSet<>();

    /**
     * 一个【模板组】有多个【模板组明细】
     */
    @ApiModelProperty(value = "一个【模板组】有多个【模板组明细】")
    @OneToMany(mappedBy = "templateGroup")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<KpiTemplateGroupInfo> templateGroupInfos = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTemplateGroupName() {
        return templateGroupName;
    }

    public KpiTemplateGroup templateGroupName(String templateGroupName) {
        this.templateGroupName = templateGroupName;
        return this;
    }

    public void setTemplateGroupName(String templateGroupName) {
        this.templateGroupName = templateGroupName;
    }

    public String getDescription() {
        return description;
    }

    public KpiTemplateGroup description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean isIsActive() {
        return isActive;
    }

    public KpiTemplateGroup isActive(Boolean isActive) {
        this.isActive = isActive;
        return this;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public KpiTemplateGroup createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public KpiTemplateGroup createdDate(Instant createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public KpiTemplateGroup lastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
        return this;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public Instant getLastModifiedDate() {
        return lastModifiedDate;
    }

    public KpiTemplateGroup lastModifiedDate(Instant lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
        return this;
    }

    public void setLastModifiedDate(Instant lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public Set<KpiScoreGradeConfig> getScoreGradeConfigs() {
        return scoreGradeConfigs;
    }

    public KpiTemplateGroup scoreGradeConfigs(Set<KpiScoreGradeConfig> kpiScoreGradeConfigs) {
        this.scoreGradeConfigs = kpiScoreGradeConfigs;
        return this;
    }

    public KpiTemplateGroup addScoreGradeConfig(KpiScoreGradeConfig kpiScoreGradeConfig) {
        this.scoreGradeConfigs.add(kpiScoreGradeConfig);
        kpiScoreGradeConfig.setTemplateGroup(this);
        return this;
    }

    public KpiTemplateGroup removeScoreGradeConfig(KpiScoreGradeConfig kpiScoreGradeConfig) {
        this.scoreGradeConfigs.remove(kpiScoreGradeConfig);
        kpiScoreGradeConfig.setTemplateGroup(null);
        return this;
    }

    public void setScoreGradeConfigs(Set<KpiScoreGradeConfig> kpiScoreGradeConfigs) {
        this.scoreGradeConfigs = kpiScoreGradeConfigs;
    }

    public Set<KpiTemplateGroupInfo> getTemplateGroupInfos() {
        return templateGroupInfos;
    }

    public KpiTemplateGroup templateGroupInfos(Set<KpiTemplateGroupInfo> kpiTemplateGroupInfos) {
        this.templateGroupInfos = kpiTemplateGroupInfos;
        return this;
    }

    public KpiTemplateGroup addTemplateGroupInfo(KpiTemplateGroupInfo kpiTemplateGroupInfo) {
        this.templateGroupInfos.add(kpiTemplateGroupInfo);
        kpiTemplateGroupInfo.setTemplateGroup(this);
        return this;
    }

    public KpiTemplateGroup removeTemplateGroupInfo(KpiTemplateGroupInfo kpiTemplateGroupInfo) {
        this.templateGroupInfos.remove(kpiTemplateGroupInfo);
        kpiTemplateGroupInfo.setTemplateGroup(null);
        return this;
    }

    public void setTemplateGroupInfos(Set<KpiTemplateGroupInfo> kpiTemplateGroupInfos) {
        this.templateGroupInfos = kpiTemplateGroupInfos;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof KpiTemplateGroup)) {
            return false;
        }
        return id != null && id.equals(((KpiTemplateGroup) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "KpiTemplateGroup{" +
            "id=" + getId() +
            ", templateGroupName='" + getTemplateGroupName() + "'" +
            ", description='" + getDescription() + "'" +
            ", isActive='" + isIsActive() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", lastModifiedBy='" + getLastModifiedBy() + "'" +
            ", lastModifiedDate='" + getLastModifiedDate() + "'" +
            "}";
    }
}
