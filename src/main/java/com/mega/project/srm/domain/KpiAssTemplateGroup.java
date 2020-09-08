package com.mega.project.srm.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
 * 考核模板详情\n@author cq
 */
@ApiModel(description = "考核模板详情\n@author cq")
@Entity
@Table(name = "srm_kpi_ass_template_group")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class KpiAssTemplateGroup implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 得分
     */
    @ApiModelProperty(value = "得分")
    @Column(name = "score")
    private Integer score;

    /**
     * 权重
     */
    @NotNull
    @ApiModelProperty(value = "权重", required = true)
    @Column(name = "weight", nullable = false)
    private Integer weight;

    /**
     * 模板名称
     */
    @ApiModelProperty(value = "模板名称")
    @Column(name = "template_name")
    private String templateName;

    /**
     * 角色
     */
    @ApiModelProperty(value = "角色")
    @Column(name = "role_name")
    private String roleName;

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
     * 一个【考核模板详情】有多个【考核模板明细】
     */
    @ApiModelProperty(value = "一个【考核模板详情】有多个【考核模板明细】")
    @OneToMany(mappedBy = "assTemplateGroup")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<KpiAssTemplateInfo> assTemplateInfos = new HashSet<>();

    /**
     * 多个【考核模板详情】属于一个【考核模板】
     */
    @ApiModelProperty(value = "多个【考核模板详情】属于一个【考核模板】")
    @ManyToOne
    @JsonIgnoreProperties(value = "kpiAssTemplateGroups", allowSetters = true)
    private KpiAssTemplate assTemplate;

    @ManyToOne
    @JsonIgnoreProperties(value = "assTemplateGroups", allowSetters = true)
    private KpiAssGroupInfo assGroupInfo;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getScore() {
        return score;
    }

    public KpiAssTemplateGroup score(Integer score) {
        this.score = score;
        return this;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getWeight() {
        return weight;
    }

    public KpiAssTemplateGroup weight(Integer weight) {
        this.weight = weight;
        return this;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public String getTemplateName() {
        return templateName;
    }

    public KpiAssTemplateGroup templateName(String templateName) {
        this.templateName = templateName;
        return this;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public String getRoleName() {
        return roleName;
    }

    public KpiAssTemplateGroup roleName(String roleName) {
        this.roleName = roleName;
        return this;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public KpiAssTemplateGroup createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public KpiAssTemplateGroup createdDate(Instant createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public KpiAssTemplateGroup lastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
        return this;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public Instant getLastModifiedDate() {
        return lastModifiedDate;
    }

    public KpiAssTemplateGroup lastModifiedDate(Instant lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
        return this;
    }

    public void setLastModifiedDate(Instant lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public Set<KpiAssTemplateInfo> getAssTemplateInfos() {
        return assTemplateInfos;
    }

    public KpiAssTemplateGroup assTemplateInfos(Set<KpiAssTemplateInfo> kpiAssTemplateInfos) {
        this.assTemplateInfos = kpiAssTemplateInfos;
        return this;
    }

    public KpiAssTemplateGroup addAssTemplateInfo(KpiAssTemplateInfo kpiAssTemplateInfo) {
        this.assTemplateInfos.add(kpiAssTemplateInfo);
        kpiAssTemplateInfo.setAssTemplateGroup(this);
        return this;
    }

    public KpiAssTemplateGroup removeAssTemplateInfo(KpiAssTemplateInfo kpiAssTemplateInfo) {
        this.assTemplateInfos.remove(kpiAssTemplateInfo);
        kpiAssTemplateInfo.setAssTemplateGroup(null);
        return this;
    }

    public void setAssTemplateInfos(Set<KpiAssTemplateInfo> kpiAssTemplateInfos) {
        this.assTemplateInfos = kpiAssTemplateInfos;
    }

    public KpiAssTemplate getAssTemplate() {
        return assTemplate;
    }

    public KpiAssTemplateGroup assTemplate(KpiAssTemplate kpiAssTemplate) {
        this.assTemplate = kpiAssTemplate;
        return this;
    }

    public void setAssTemplate(KpiAssTemplate kpiAssTemplate) {
        this.assTemplate = kpiAssTemplate;
    }

    public KpiAssGroupInfo getAssGroupInfo() {
        return assGroupInfo;
    }

    public KpiAssTemplateGroup assGroupInfo(KpiAssGroupInfo kpiAssGroupInfo) {
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
        if (!(o instanceof KpiAssTemplateGroup)) {
            return false;
        }
        return id != null && id.equals(((KpiAssTemplateGroup) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "KpiAssTemplateGroup{" +
            "id=" + getId() +
            ", score=" + getScore() +
            ", weight=" + getWeight() +
            ", templateName='" + getTemplateName() + "'" +
            ", roleName='" + getRoleName() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", lastModifiedBy='" + getLastModifiedBy() + "'" +
            ", lastModifiedDate='" + getLastModifiedDate() + "'" +
            "}";
    }
}
