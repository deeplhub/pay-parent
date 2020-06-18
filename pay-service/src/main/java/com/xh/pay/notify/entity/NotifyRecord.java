package com.xh.pay.notify.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Title:
 * Description:
 *
 * @author H.Yang
 * @email xhaimail@163.com
 * @date 2020/6/14
 */
@Entity
@Table(name = "notify_record")
public class NotifyRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;// 主键ID.
    @Column(name = "version")
    private Integer version = 0;// 版本号默认为0
    @Column(name = "status")
    private String status;// 状态 PublicStatusEnum
    @Column(name = "creater")
    private String creater;// 创建人.
    @Column(name = "create_time")
    private Date createTime = new Date();// 创建时间.
    @Column(name = "edit_time")
    private Date editTime;// 修改时间.
    @Column(name = "notify_times")
    private Integer notifyTimes = 0;//通知次数
    @Column(name = "limit_notify_times")
    private Integer limitNotifyTimes = 5;//限制通知次数
    @Column(name = "url")
    private String url;//通知URL
    @Column(name = "merchant_no")
    private String merchantNo;//商户编号
    @Column(name = "merchant_order_no")
    private String merchantOrderNo;//商户订单号
    @Column(name = "notify_type")
    private String notifyType;//通知类型 NotifyTypeEnum

    /** 最后一次通知时间 **/
    @Transient
    private Date lastNotifyTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreater() {
        return creater;
    }

    public void setCreater(String creater) {
        this.creater = creater;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getNotifyTimes() {
        return notifyTimes;
    }

    public void setNotifyTimes(Integer notifyTimes) {
        this.notifyTimes = notifyTimes;
    }

    public Integer getLimitNotifyTimes() {
        return limitNotifyTimes;
    }

    public void setLimitNotifyTimes(Integer limitNotifyTimes) {
        this.limitNotifyTimes = limitNotifyTimes;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMerchantNo() {
        return merchantNo;
    }

    public void setMerchantNo(String merchantNo) {
        this.merchantNo = merchantNo;
    }

    public String getMerchantOrderNo() {
        return merchantOrderNo;
    }

    public void setMerchantOrderNo(String merchantOrderNo) {
        this.merchantOrderNo = merchantOrderNo;
    }

    public String getNotifyType() {
        return notifyType;
    }

    public void setNotifyType(String notifyType) {
        this.notifyType = notifyType;
    }

    public Date getEditTime() {
        return editTime;
    }

    public void setEditTime(Date editTime) {
        this.editTime = editTime;
    }

    public Date getLastNotifyTime() {
        return lastNotifyTime;
    }

    public void setLastNotifyTime(Date lastNotifyTime) {
        this.lastNotifyTime = lastNotifyTime;
    }
}
