package com.mega.project.srm.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mega.project.srm.domain.enumeration.AssStatus;
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
 * 考核模板明细\n@author cq
 */
@ApiModel(description = "考核模板明细\n@author cq")
@Entity
@Table(name = "srm_kpi_ass_template_info")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class KpiAssTemplateInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 考核单号
     */
    @NotNull
    @ApiModelProperty(value = "考核单号", required = true)
    @Column(name = "ass_code", nullable = false)
    private String assCode;

    /**
     * 总得分
     */
    @ApiModelProperty(value = "总得分")
    @Column(name = "total_score")
    private Integer totalScore;

    /**
     * 状态
     */
    @NotNull
    @ApiModelProperty(value = "状态", required = true)
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private AssStatus status;

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
     * 一个【考核模板明细】有多个【分数详情】
     */
    @ApiModelProperty(value = "一个【考核模板明细】有多个【分数详情】")
    @OneToMany(mappedBy = "assTemplateInfo")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<KpiScoreInfo> scoreInfos = new HashSet<>();

    /**
     * 多个【考核模板明细】属于一个【用户】
     */
    @ApiModelProperty(value = "多个【考核模板明细】属于一个【用户】")
    @ManyToOne
    @JsonIgnoreProperties(value = "kpiAssTemplateInfos", allowSetters = true)
    private User user;

    @ManyToOne
    @JsonIgnoreProperties(value = "assTemplateInfos", allowSetters = true)
    private KpiAssTemplateGroup assTemplateGroup;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAssCode() {
        return assCode;
    }

    public KpiAssTemplateInfo assCode(String assCode) {
        this.assCode = assCode;
        return this;
    }

    public void setAssCode(String assCode) {
        this.assCode = assCode;
    }

    public Integer getTotalScore() {
        return totalScore;
    }

    public KpiAssTemplateInfo totalScore(Integer totalScore) {
        this.totalScore = totalScore;
        return this;
    }

    public void setTotalScore(Integer totalScore) {
        this.totalScore = totalScore;
    }

    public AssStatus getStatus() {
        return status;
    }

    public KpiAssTemplateInfo status(AssStatus status) {
        this.status = status;
        return this;
    }

    public void setStatus(AssStatus status) {
        this.status = status;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public KpiAssTemplateInfo createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public KpiAssTemplateInfo createdDate(Instant createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public KpiAssTemplateInfo lastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
        return this;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public Instant getLastModifiedDate() {
        return lastModifiedDate;
    }

    public KpiAssTemplateInfo lastModifiedDate(Instant lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
        return this;
    }

    public void setLastModifiedDate(Instant lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public Set<KpiScoreInfo> getScoreInfos() {
        return scoreInfos;
    }

    public KpiAssTemplateInfo scoreInfos(Set<KpiScoreInfo> kpiScoreInfos) {
        this.scoreInfos = kpiScoreInfos;
        return this;
    }

    public KpiAssTemplateInfo addScoreInfo(KpiScoreInfo kpiScoreInfo) {
        this.scoreInfos.add(kpiScoreInfo);
        kpiScoreInfo.setAssTemplateInfo(this);
        return this;
    }

    public KpiAssTemplateInfo removeScoreInfo(KpiScoreInfo kpiScoreInfo) {
        this.scoreInfos.remove(kpiScoreInfo);
        kpiScoreInfo.setAssTemplateInfo(null);
        return this;
    }

    public void setScoreInfos(Set<KpiScoreInfo> kpiScoreInfos) {
        this.scoreInfos = kpiScoreInfos;
    }

    public User getUser() {
        return user;
    }

    public KpiAssTemplateInfo user(User user) {
        this.user = user;
        return this;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public KpiAssTemplateGroup getAssTemplateGroup() {
        return assTemplateGroup;
    }

    public KpiAssTemplateInfo assTemplateGroup(KpiAssTemplateGroup kpiAssTemplateGroup) {
        this.assTemplateGroup = kpiAssTemplateGroup;
        return this;
    }

    public void setAssTemplateGroup(KpiAssTemplateGroup kpiAssTemplateGroup) {
        this.assTemplateGroup = kpiAssTemplateGroup;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof KpiAssTemplateInfo)) {
            return false;
        }
        return id != null && id.equals(((KpiAssTemplateInfo) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "KpiAssTemplateInfo{" +
            "id=" + getId() +
            ", assCode='" + getAssCode() + "'" +
            ", totalScore=" + getTotalScore() +
            ", status='" + getStatus() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", lastModifiedBy='" + getLastModifiedBy() + "'" +
            ", lastModifiedDate='" + getLastModifiedDate() + "'" +
            "}";
    }
}
