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
 * 考核模板\n@author cq
 */
@ApiModel(description = "考核模板\n@author cq")
@Entity
@Table(name = "srm_kpi_ass_template")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class KpiAssTemplate implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 模板名称
     */
    @ApiModelProperty(value = "模板名称")
    @Column(name = "template_name")
    private String templateName;

    /**
     * 文件地址
     */
    @ApiModelProperty(value = "文件地址")
    @Column(name = "file_path")
    private String filePath;

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
     * 一个【考核模板】有多个【计算项】
     */
    @ApiModelProperty(value = "一个【考核模板】有多个【计算项】")
    @OneToMany(mappedBy = "assTemplate")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<KpiCalItem> calItems = new HashSet<>();

    /**
     * 多个【考核模板】属于一个【角色】
     */
    @ApiModelProperty(value = "多个【考核模板】属于一个【角色】")
    @ManyToOne
    @JsonIgnoreProperties(value = "kpiAssTemplates", allowSetters = true)
    private Role role;

    /**
     * 多个【考核模板】属于一个【分数计算配置模板】
     */
    @ApiModelProperty(value = "多个【考核模板】属于一个【分数计算配置模板】")
    @ManyToOne
    @JsonIgnoreProperties(value = "kpiAssTemplates", allowSetters = true)
    private KpiScoreCalConfig scoreCalConfig;

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

    public KpiAssTemplate templateName(String templateName) {
        this.templateName = templateName;
        return this;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public String getFilePath() {
        return filePath;
    }

    public KpiAssTemplate filePath(String filePath) {
        this.filePath = filePath;
        return this;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public KpiAssTemplate createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public KpiAssTemplate createdDate(Instant createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public KpiAssTemplate lastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
        return this;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public Instant getLastModifiedDate() {
        return lastModifiedDate;
    }

    public KpiAssTemplate lastModifiedDate(Instant lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
        return this;
    }

    public void setLastModifiedDate(Instant lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public Set<KpiCalItem> getCalItems() {
        return calItems;
    }

    public KpiAssTemplate calItems(Set<KpiCalItem> kpiCalItems) {
        this.calItems = kpiCalItems;
        return this;
    }

    public KpiAssTemplate addCalItem(KpiCalItem kpiCalItem) {
        this.calItems.add(kpiCalItem);
        kpiCalItem.setAssTemplate(this);
        return this;
    }

    public KpiAssTemplate removeCalItem(KpiCalItem kpiCalItem) {
        this.calItems.remove(kpiCalItem);
        kpiCalItem.setAssTemplate(null);
        return this;
    }

    public void setCalItems(Set<KpiCalItem> kpiCalItems) {
        this.calItems = kpiCalItems;
    }

    public Role getRole() {
        return role;
    }

    public KpiAssTemplate role(Role role) {
        this.role = role;
        return this;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public KpiScoreCalConfig getScoreCalConfig() {
        return scoreCalConfig;
    }

    public KpiAssTemplate scoreCalConfig(KpiScoreCalConfig kpiScoreCalConfig) {
        this.scoreCalConfig = kpiScoreCalConfig;
        return this;
    }

    public void setScoreCalConfig(KpiScoreCalConfig kpiScoreCalConfig) {
        this.scoreCalConfig = kpiScoreCalConfig;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof KpiAssTemplate)) {
            return false;
        }
        return id != null && id.equals(((KpiAssTemplate) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "KpiAssTemplate{" +
            "id=" + getId() +
            ", templateName='" + getTemplateName() + "'" +
            ", filePath='" + getFilePath() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", lastModifiedBy='" + getLastModifiedBy() + "'" +
            ", lastModifiedDate='" + getLastModifiedDate() + "'" +
            "}";
    }
}
