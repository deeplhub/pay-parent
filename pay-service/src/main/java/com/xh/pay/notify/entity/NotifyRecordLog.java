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
@Table(name="notify_record_log")
public class NotifyRecordLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;// 主键ID.
    @Column(name = "version")
    private Integer version = 0;// 版本号默认为0
    @Column(name = "editor")
    private String editor;// 修改人.
    @Column(name = "creater")
    private String creater;// 创建人.
    @Column(name = "createTime")
    private Date createTime = new Date();// 创建时间.
    @Column(name = "edit_time")
    private Date editTime;// 修改时间.
    /** 通知记录ID **/
    @Column(name = "notify_id")
    private Long notifyId;

    /** 请求信息 **/
    @Column(name = "request")
    private String request;

    /** 返回信息 **/
    @Column(name = "response")
    private String response;

    /** 商户编号 **/
    @Column(name = "merchant_no")
    private String merchantNo;

    /** 商户订单号 **/
    @Column(name = "merchant_order_no")
    private String merchantOrderNo;

    /** HTTP状态 **/
    @Column(name = "http_status")
    private Integer httpStatus;
    @Transient
    private String status;// 状态 PublicStatusEnum

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

    public String getEditor() {
        return editor;
    }

    public void setEditor(String editor) {
        this.editor = editor;
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

    public Date getEditTime() {
        return editTime;
    }

    public void setEditTime(Date editTime) {
        this.editTime = editTime;
    }

    public Long getNotifyId() {
        return notifyId;
    }

    public void setNotifyId(Long notifyId) {
        this.notifyId = notifyId;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
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

    public Integer getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(Integer httpStatus) {
        this.httpStatus = httpStatus;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
