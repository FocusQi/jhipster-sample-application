package com.mycompany.myapp.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;

/**
 * 退货信息单头表
 */
@ApiModel(description = "退货信息单头表")
@Entity
@Table(name = "return_header")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ReturnHeader implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    /**
     * 退货单号
     */
    @ApiModelProperty(value = "退货单号")
    @Column(name = "from_num")
    private Integer fromNum;

    /**
     * 供应商编码
     */
    @Size(max = 32)
    @ApiModelProperty(value = "供应商编码")
    @Column(name = "v_code", length = 32)
    private String vCode;

    /**
     * 物料编码
     */
    @Size(max = 32)
    @ApiModelProperty(value = "物料编码")
    @Column(name = "m_code", length = 32)
    private String mCode;

    /**
     * 数量
     */
    @ApiModelProperty(value = "数量")
    @Column(name = "quantity")
    private Integer quantity;

    /**
     * 创建者
     */
    @Size(max = 32)
    @ApiModelProperty(value = "创建者")
    @Column(name = "created_by", length = 32)
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
    @Size(max = 32)
    @ApiModelProperty(value = "修改者")
    @Column(name = "last_modified_by", length = 32)
    private String lastModifiedBy;

    /**
     * 修改日期
     */
    @ApiModelProperty(value = "修改日期")
    @Column(name = "last_modified_date")
    private Instant lastModifiedDate;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getFromNum() {
        return fromNum;
    }

    public ReturnHeader fromNum(Integer fromNum) {
        this.fromNum = fromNum;
        return this;
    }

    public void setFromNum(Integer fromNum) {
        this.fromNum = fromNum;
    }

    public String getvCode() {
        return vCode;
    }

    public ReturnHeader vCode(String vCode) {
        this.vCode = vCode;
        return this;
    }

    public void setvCode(String vCode) {
        this.vCode = vCode;
    }

    public String getmCode() {
        return mCode;
    }

    public ReturnHeader mCode(String mCode) {
        this.mCode = mCode;
        return this;
    }

    public void setmCode(String mCode) {
        this.mCode = mCode;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public ReturnHeader quantity(Integer quantity) {
        this.quantity = quantity;
        return this;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public ReturnHeader createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public ReturnHeader createdDate(Instant createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public ReturnHeader lastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
        return this;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public Instant getLastModifiedDate() {
        return lastModifiedDate;
    }

    public ReturnHeader lastModifiedDate(Instant lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
        return this;
    }

    public void setLastModifiedDate(Instant lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ReturnHeader)) {
            return false;
        }
        return id != null && id.equals(((ReturnHeader) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ReturnHeader{" +
            "id=" + getId() +
            ", fromNum=" + getFromNum() +
            ", vCode='" + getvCode() + "'" +
            ", mCode='" + getmCode() + "'" +
            ", quantity=" + getQuantity() +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", lastModifiedBy='" + getLastModifiedBy() + "'" +
            ", lastModifiedDate='" + getLastModifiedDate() + "'" +
            "}";
    }
}
