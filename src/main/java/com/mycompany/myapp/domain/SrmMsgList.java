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
@Table(name = "srm_msg_list")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SrmMsgList implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    /**
     * MSG ID
     */
    @ApiModelProperty(value = "MSG ID")
    @Column(name = "msg_id")
    private Integer msgId;

    /**
     * topic
     */
    @Size(max = 500)
    @ApiModelProperty(value = "topic")
    @Column(name = "msg_topic", length = 500)
    private String msgTopic;

    /**
     * 信息内容
     */
    @Size(max = 4000)
    @ApiModelProperty(value = "信息内容")
    @Column(name = "msg_content", length = 4000)
    private String msgContent;

    /**
     * 收信人
     */
    @Size(max = 4000)
    @ApiModelProperty(value = "收信人")
    @Column(name = "msg_mail_to", length = 4000)
    private String msgMailTo;

    /**
     * SMS To
     */
    @Size(max = 500)
    @ApiModelProperty(value = "SMS To")
    @Column(name = "msg_sms_to", length = 500)
    private String msgSmsTo;

    /**
     * 创建日期
     */
    @ApiModelProperty(value = "创建日期")
    @Column(name = "msg_create_time")
    private Instant msgCreateTime;

    /**
     * 发送日期
     */
    @ApiModelProperty(value = "发送日期")
    @Column(name = "msg_send_time")
    private Instant msgSendTime;

    /**
     * 消息状态
     */
    @Size(max = 3)
    @ApiModelProperty(value = "消息状态")
    @Column(name = "msg_status", length = 3)
    private String msgStatus;

    /**
     * 消息状态
     */
    @Size(max = 50)
    @ApiModelProperty(value = "消息状态")
    @Column(name = "msf_memo", length = 50)
    private String msfMemo;

    /**
     * 消息状态
     */
    @Size(max = 2)
    @ApiModelProperty(value = "消息状态")
    @Column(name = "msg_to_back_up", length = 2)
    private String msgToBackUp;

    /**
     * 消息状态
     */
    @Size(max = 4000)
    @ApiModelProperty(value = "消息状态")
    @Column(name = "remark", length = 4000)
    private String remark;

    /**
     * 修改者
     */
    @ApiModelProperty(value = "修改者")
    @Column(name = "last_modified_by")
    private String lastModifiedBy;

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

    public Integer getMsgId() {
        return msgId;
    }

    public SrmMsgList msgId(Integer msgId) {
        this.msgId = msgId;
        return this;
    }

    public void setMsgId(Integer msgId) {
        this.msgId = msgId;
    }

    public String getMsgTopic() {
        return msgTopic;
    }

    public SrmMsgList msgTopic(String msgTopic) {
        this.msgTopic = msgTopic;
        return this;
    }

    public void setMsgTopic(String msgTopic) {
        this.msgTopic = msgTopic;
    }

    public String getMsgContent() {
        return msgContent;
    }

    public SrmMsgList msgContent(String msgContent) {
        this.msgContent = msgContent;
        return this;
    }

    public void setMsgContent(String msgContent) {
        this.msgContent = msgContent;
    }

    public String getMsgMailTo() {
        return msgMailTo;
    }

    public SrmMsgList msgMailTo(String msgMailTo) {
        this.msgMailTo = msgMailTo;
        return this;
    }

    public void setMsgMailTo(String msgMailTo) {
        this.msgMailTo = msgMailTo;
    }

    public String getMsgSmsTo() {
        return msgSmsTo;
    }

    public SrmMsgList msgSmsTo(String msgSmsTo) {
        this.msgSmsTo = msgSmsTo;
        return this;
    }

    public void setMsgSmsTo(String msgSmsTo) {
        this.msgSmsTo = msgSmsTo;
    }

    public Instant getMsgCreateTime() {
        return msgCreateTime;
    }

    public SrmMsgList msgCreateTime(Instant msgCreateTime) {
        this.msgCreateTime = msgCreateTime;
        return this;
    }

    public void setMsgCreateTime(Instant msgCreateTime) {
        this.msgCreateTime = msgCreateTime;
    }

    public Instant getMsgSendTime() {
        return msgSendTime;
    }

    public SrmMsgList msgSendTime(Instant msgSendTime) {
        this.msgSendTime = msgSendTime;
        return this;
    }

    public void setMsgSendTime(Instant msgSendTime) {
        this.msgSendTime = msgSendTime;
    }

    public String getMsgStatus() {
        return msgStatus;
    }

    public SrmMsgList msgStatus(String msgStatus) {
        this.msgStatus = msgStatus;
        return this;
    }

    public void setMsgStatus(String msgStatus) {
        this.msgStatus = msgStatus;
    }

    public String getMsfMemo() {
        return msfMemo;
    }

    public SrmMsgList msfMemo(String msfMemo) {
        this.msfMemo = msfMemo;
        return this;
    }

    public void setMsfMemo(String msfMemo) {
        this.msfMemo = msfMemo;
    }

    public String getMsgToBackUp() {
        return msgToBackUp;
    }

    public SrmMsgList msgToBackUp(String msgToBackUp) {
        this.msgToBackUp = msgToBackUp;
        return this;
    }

    public void setMsgToBackUp(String msgToBackUp) {
        this.msgToBackUp = msgToBackUp;
    }

    public String getRemark() {
        return remark;
    }

    public SrmMsgList remark(String remark) {
        this.remark = remark;
        return this;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public SrmMsgList lastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
        return this;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public SrmMsgList createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public SrmMsgList createdDate(Instant createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public Instant getLastModifiedDate() {
        return lastModifiedDate;
    }

    public SrmMsgList lastModifiedDate(Instant lastModifiedDate) {
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
        if (!(o instanceof SrmMsgList)) {
            return false;
        }
        return id != null && id.equals(((SrmMsgList) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "SrmMsgList{" +
            "id=" + getId() +
            ", msgId=" + getMsgId() +
            ", msgTopic='" + getMsgTopic() + "'" +
            ", msgContent='" + getMsgContent() + "'" +
            ", msgMailTo='" + getMsgMailTo() + "'" +
            ", msgSmsTo='" + getMsgSmsTo() + "'" +
            ", msgCreateTime='" + getMsgCreateTime() + "'" +
            ", msgSendTime='" + getMsgSendTime() + "'" +
            ", msgStatus='" + getMsgStatus() + "'" +
            ", msfMemo='" + getMsfMemo() + "'" +
            ", msgToBackUp='" + getMsgToBackUp() + "'" +
            ", remark='" + getRemark() + "'" +
            ", lastModifiedBy='" + getLastModifiedBy() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", lastModifiedDate='" + getLastModifiedDate() + "'" +
            "}";
    }
}
