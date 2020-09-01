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
 * 物料成本清单模板\n@author cq
 */
@ApiModel(description = "物料成本清单模板\n@author cq")
@Entity
@Table(name = "srm_bom_template_header")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class BomTemplateHeader implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 模板编码
     */
    @NotNull
    @ApiModelProperty(value = "模板编码", required = true)
    @Column(name = "template_code", nullable = false)
    private String templateCode;

    /**
     * 模板名称
     */
    @NotNull
    @ApiModelProperty(value = "模板名称", required = true)
    @Column(name = "template_name", nullable = false)
    private String templateName;

    /**
     * 模板类型
     */
    @NotNull
    @ApiModelProperty(value = "模板类型", required = true)
    @Column(name = "template_type", nullable = false)
    private String templateType;

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
     * 一个物料成本清单模板有多个成本清单明细
     */
    @ApiModelProperty(value = "一个物料成本清单模板有多个成本清单明细")
    @OneToMany(mappedBy = "header")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<BomTemplateInfo> infos = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = "bomTemplateHeaders", allowSetters = true)
    private BiddingMaterialRound materialRound;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTemplateCode() {
        return templateCode;
    }

    public BomTemplateHeader templateCode(String templateCode) {
        this.templateCode = templateCode;
        return this;
    }

    public void setTemplateCode(String templateCode) {
        this.templateCode = templateCode;
    }

    public String getTemplateName() {
        return templateName;
    }

    public BomTemplateHeader templateName(String templateName) {
        this.templateName = templateName;
        return this;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public String getTemplateType() {
        return templateType;
    }

    public BomTemplateHeader templateType(String templateType) {
        this.templateType = templateType;
        return this;
    }

    public void setTemplateType(String templateType) {
        this.templateType = templateType;
    }

    public String getRemark() {
        return remark;
    }

    public BomTemplateHeader remark(String remark) {
        this.remark = remark;
        return this;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public BomTemplateHeader createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public BomTemplateHeader createdDate(Instant createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public BomTemplateHeader lastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
        return this;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public Instant getLastModifiedDate() {
        return lastModifiedDate;
    }

    public BomTemplateHeader lastModifiedDate(Instant lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
        return this;
    }

    public void setLastModifiedDate(Instant lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public Set<BomTemplateInfo> getInfos() {
        return infos;
    }

    public BomTemplateHeader infos(Set<BomTemplateInfo> bomTemplateInfos) {
        this.infos = bomTemplateInfos;
        return this;
    }

    public BomTemplateHeader addInfo(BomTemplateInfo bomTemplateInfo) {
        this.infos.add(bomTemplateInfo);
        bomTemplateInfo.setHeader(this);
        return this;
    }

    public BomTemplateHeader removeInfo(BomTemplateInfo bomTemplateInfo) {
        this.infos.remove(bomTemplateInfo);
        bomTemplateInfo.setHeader(null);
        return this;
    }

    public void setInfos(Set<BomTemplateInfo> bomTemplateInfos) {
        this.infos = bomTemplateInfos;
    }

    public BiddingMaterialRound getMaterialRound() {
        return materialRound;
    }

    public BomTemplateHeader materialRound(BiddingMaterialRound biddingMaterialRound) {
        this.materialRound = biddingMaterialRound;
        return this;
    }

    public void setMaterialRound(BiddingMaterialRound biddingMaterialRound) {
        this.materialRound = biddingMaterialRound;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BomTemplateHeader)) {
            return false;
        }
        return id != null && id.equals(((BomTemplateHeader) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "BomTemplateHeader{" +
            "id=" + getId() +
            ", templateCode='" + getTemplateCode() + "'" +
            ", templateName='" + getTemplateName() + "'" +
            ", templateType='" + getTemplateType() + "'" +
            ", remark='" + getRemark() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", lastModifiedBy='" + getLastModifiedBy() + "'" +
            ", lastModifiedDate='" + getLastModifiedDate() + "'" +
            "}";
    }
}
