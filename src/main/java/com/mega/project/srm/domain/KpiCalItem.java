package com.mega.project.srm.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mega.project.srm.domain.enumeration.CalculationItemType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.time.Instant;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * 计算项\n@author cq
 */
@ApiModel(description = "计算项\n@author cq")
@Entity
@Table(name = "srm_kpi_cal_item")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class KpiCalItem implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 计算类型
     */
    @NotNull
    @ApiModelProperty(value = "计算类型", required = true)
    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private CalculationItemType type;

    /**
     * 标题
     */
    @NotNull
    @ApiModelProperty(value = "标题", required = true)
    @Column(name = "title", nullable = false)
    private String title;

    /**
     * 计算项名称
     */
    @NotNull
    @ApiModelProperty(value = "计算项名称", required = true)
    @Column(name = "cal_item_name", nullable = false)
    private String calItemName;

    /**
     * 说明
     */
    @ApiModelProperty(value = "说明")
    @Column(name = "description")
    private String description;

    /**
     * 权重
     */
    @NotNull
    @ApiModelProperty(value = "权重", required = true)
    @Column(name = "weight", nullable = false)
    private Integer weight;

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
    @JsonIgnoreProperties(value = "calItems", allowSetters = true)
    private KpiAssTemplate assTemplate;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CalculationItemType getType() {
        return type;
    }

    public KpiCalItem type(CalculationItemType type) {
        this.type = type;
        return this;
    }

    public void setType(CalculationItemType type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public KpiCalItem title(String title) {
        this.title = title;
        return this;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCalItemName() {
        return calItemName;
    }

    public KpiCalItem calItemName(String calItemName) {
        this.calItemName = calItemName;
        return this;
    }

    public void setCalItemName(String calItemName) {
        this.calItemName = calItemName;
    }

    public String getDescription() {
        return description;
    }

    public KpiCalItem description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getWeight() {
        return weight;
    }

    public KpiCalItem weight(Integer weight) {
        this.weight = weight;
        return this;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public KpiCalItem createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public KpiCalItem createdDate(Instant createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public KpiCalItem lastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
        return this;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public Instant getLastModifiedDate() {
        return lastModifiedDate;
    }

    public KpiCalItem lastModifiedDate(Instant lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
        return this;
    }

    public void setLastModifiedDate(Instant lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public KpiAssTemplate getAssTemplate() {
        return assTemplate;
    }

    public KpiCalItem assTemplate(KpiAssTemplate kpiAssTemplate) {
        this.assTemplate = kpiAssTemplate;
        return this;
    }

    public void setAssTemplate(KpiAssTemplate kpiAssTemplate) {
        this.assTemplate = kpiAssTemplate;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof KpiCalItem)) {
            return false;
        }
        return id != null && id.equals(((KpiCalItem) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "KpiCalItem{" +
            "id=" + getId() +
            ", type='" + getType() + "'" +
            ", title='" + getTitle() + "'" +
            ", calItemName='" + getCalItemName() + "'" +
            ", description='" + getDescription() + "'" +
            ", weight=" + getWeight() +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", lastModifiedBy='" + getLastModifiedBy() + "'" +
            ", lastModifiedDate='" + getLastModifiedDate() + "'" +
            "}";
    }
}
