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
 * 分数计算配置模板\n@author cq
 */
@ApiModel(description = "分数计算配置模板\n@author cq")
@Entity
@Table(name = "srm_kpi_score_cal_config")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class KpiScoreCalConfig implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 模板名称
     */
    @NotNull
    @ApiModelProperty(value = "模板名称", required = true)
    @Column(name = "template_name", nullable = false)
    private String templateName;

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
     * 一个【分数计算配置模板】有多个【分数计算模板配置项】
     */
    @ApiModelProperty(value = "一个【分数计算配置模板】有多个【分数计算模板配置项】")
    @OneToMany(mappedBy = "scoreCalConfig")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<KpiScoreCalConfigItem> scoreCalConfigItems = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTemplateName() {
        return templateName;
    }

    public KpiScoreCalConfig templateName(String templateName) {
        this.templateName = templateName;
        return this;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public KpiScoreCalConfig createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public KpiScoreCalConfig createdDate(Instant createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public KpiScoreCalConfig lastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
        return this;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public Instant getLastModifiedDate() {
        return lastModifiedDate;
    }

    public KpiScoreCalConfig lastModifiedDate(Instant lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
        return this;
    }

    public void setLastModifiedDate(Instant lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public Set<KpiScoreCalConfigItem> getScoreCalConfigItems() {
        return scoreCalConfigItems;
    }

    public KpiScoreCalConfig scoreCalConfigItems(Set<KpiScoreCalConfigItem> kpiScoreCalConfigItems) {
        this.scoreCalConfigItems = kpiScoreCalConfigItems;
        return this;
    }

    public KpiScoreCalConfig addScoreCalConfigItem(KpiScoreCalConfigItem kpiScoreCalConfigItem) {
        this.scoreCalConfigItems.add(kpiScoreCalConfigItem);
        kpiScoreCalConfigItem.setScoreCalConfig(this);
        return this;
    }

    public KpiScoreCalConfig removeScoreCalConfigItem(KpiScoreCalConfigItem kpiScoreCalConfigItem) {
        this.scoreCalConfigItems.remove(kpiScoreCalConfigItem);
        kpiScoreCalConfigItem.setScoreCalConfig(null);
        return this;
    }

    public void setScoreCalConfigItems(Set<KpiScoreCalConfigItem> kpiScoreCalConfigItems) {
        this.scoreCalConfigItems = kpiScoreCalConfigItems;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof KpiScoreCalConfig)) {
            return false;
        }
        return id != null && id.equals(((KpiScoreCalConfig) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "KpiScoreCalConfig{" +
            "id=" + getId() +
            ", templateName='" + getTemplateName() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", lastModifiedBy='" + getLastModifiedBy() + "'" +
            ", lastModifiedDate='" + getLastModifiedDate() + "'" +
            "}";
    }
}
