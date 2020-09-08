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
 * 模板组明细\n@author cq
 */
@ApiModel(description = "模板组明细\n@author cq")
@Entity
@Table(name = "srm_kpi_template_group_info")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class KpiTemplateGroupInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 计分权重
     */
    @ApiModelProperty(value = "计分权重")
    @Column(name = "scoring_weight")
    private Integer scoringWeight;

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
     * 多个【模板组明细】属于一个【考核模板】
     */
    @ApiModelProperty(value = "多个【模板组明细】属于一个【考核模板】")
    @ManyToOne
    @JsonIgnoreProperties(value = "kpiTemplateGroupInfos", allowSetters = true)
    private KpiAssTemplate assTemplate;

    /**
     * 多个【模板组明细】对应多个【用户表】
     */
    @ApiModelProperty(value = "多个【模板组明细】对应多个【用户表】")
    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JoinTable(
        name = "srm_kpi_template_group_info_users",
        joinColumns = @JoinColumn(name = "kpi_template_group_info_id", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "users_id", referencedColumnName = "id")
    )
    private Set<User> users = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = "templateGroupInfos", allowSetters = true)
    private KpiTemplateGroup templateGroup;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getScoringWeight() {
        return scoringWeight;
    }

    public KpiTemplateGroupInfo scoringWeight(Integer scoringWeight) {
        this.scoringWeight = scoringWeight;
        return this;
    }

    public void setScoringWeight(Integer scoringWeight) {
        this.scoringWeight = scoringWeight;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public KpiTemplateGroupInfo createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public KpiTemplateGroupInfo createdDate(Instant createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public KpiTemplateGroupInfo lastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
        return this;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public Instant getLastModifiedDate() {
        return lastModifiedDate;
    }

    public KpiTemplateGroupInfo lastModifiedDate(Instant lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
        return this;
    }

    public void setLastModifiedDate(Instant lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public KpiAssTemplate getAssTemplate() {
        return assTemplate;
    }

    public KpiTemplateGroupInfo assTemplate(KpiAssTemplate kpiAssTemplate) {
        this.assTemplate = kpiAssTemplate;
        return this;
    }

    public void setAssTemplate(KpiAssTemplate kpiAssTemplate) {
        this.assTemplate = kpiAssTemplate;
    }

    public Set<User> getUsers() {
        return users;
    }

    public KpiTemplateGroupInfo users(Set<User> users) {
        this.users = users;
        return this;
    }

    public KpiTemplateGroupInfo addUsers(User user) {
        this.users.add(user);
        return this;
    }

    public KpiTemplateGroupInfo removeUsers(User user) {
        this.users.remove(user);
        return this;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public KpiTemplateGroup getTemplateGroup() {
        return templateGroup;
    }

    public KpiTemplateGroupInfo templateGroup(KpiTemplateGroup kpiTemplateGroup) {
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
        if (!(o instanceof KpiTemplateGroupInfo)) {
            return false;
        }
        return id != null && id.equals(((KpiTemplateGroupInfo) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "KpiTemplateGroupInfo{" +
            "id=" + getId() +
            ", scoringWeight=" + getScoringWeight() +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", lastModifiedBy='" + getLastModifiedBy() + "'" +
            ", lastModifiedDate='" + getLastModifiedDate() + "'" +
            "}";
    }
}
