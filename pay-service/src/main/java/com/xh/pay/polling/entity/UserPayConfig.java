package com.xh.pay.polling.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * <b>Title: 支付配置</b>
 * <p>Description: </p>
 *
 * @author H.Yang
 * @email xhaimail@163.com
 * @date 2019年9月26日
 */
@Entity
@Table(name = "user_pay_config")
public class UserPayConfig {

    @Id
    @Column(name = "id")
    private Long id;// 主键ID.
    @Column(name = "version")
    private Integer version = 0;// 版本号默认为0
    @Column(name = "status")
    private String status;// 状态 PublicStatusEnum
    @Column(name = "create_time")
    private Date createTime = new Date();// 创建时间.
    @Column(name = "edit_time")
    private Date editTime;// 修改时间.
    @Column(name = "audit_status")
    private String auditStatus;
    @Column(name = "is_auto_sett")
    private String isAutoSett;
    @Column(name = "product_code")
    private String productCode;
    @Column(name = "product_name")
    private String productName;
    @Column(name = "user_no")
    private String userNo;
    @Column(name = "user_name")
    private String userName;
    @Column(name = "risk_day")
    private Integer riskDay;
    @Column(name = "pay_key")
    private String payKey;
    @Column(name = "fund_into_type")
    private String fundIntoType;
    @Column(name = "pay_secret")
    private String paySecret;

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

    public String getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(String auditStatus) {
        this.auditStatus = auditStatus;
    }

    public String getIsAutoSett() {
        return isAutoSett;
    }

    public void setIsAutoSett(String isAutoSett) {
        this.isAutoSett = isAutoSett;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getRiskDay() {
        return riskDay;
    }

    public void setRiskDay(Integer riskDay) {
        this.riskDay = riskDay;
    }

    public String getPayKey() {
        return payKey;
    }

    public void setPayKey(String payKey) {
        this.payKey = payKey;
    }

    public String getFundIntoType() {
        return fundIntoType;
    }

    public void setFundIntoType(String fundIntoType) {
        this.fundIntoType = fundIntoType;
    }

    public String getPaySecret() {
        return paySecret;
    }

    public void setPaySecret(String paySecret) {
        this.paySecret = paySecret;
    }
}
