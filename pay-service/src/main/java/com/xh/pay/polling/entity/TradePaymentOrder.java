package com.xh.pay.polling.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <b>Title: 支付订单表</b>
 * <p>Description: </p>
 *
 * @author H.Yang
 * @email xhaimail@163.com
 * @date 2019年9月26日
 */
@Entity
@Table(name = "trade_payment_order")
public class TradePaymentOrder {

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
    @Column(name = "editor")
    private String editor;// 修改人.
    @Column(name = "edit_time")
    private Date editTime;// 修改时间.
    @Column(name = "product_name")
    private String productName;//商品名称
    @Column(name = "merchant_order_no")
    private String merchantOrderNo;//商户订单编号
    @Column(name = "order_amount")
    private BigDecimal orderAmount;//订单金额
    @Column(name = "order_from")
    private String orderFrom;//订单来源
    @Column(name = "merchant_name")
    private String merchantName;//商户名称
    @Column(name = "merchant_no")
    private String merchantNo;//商户编号
    @Column(name = "order_time")
    private Date orderTime;//订单时间
    @Column(name = "order_date")
    private Date orderDate;//订单日期
    @Column(name = "order_ip")
    private String orderIp;//订单来源IP
    @Column(name = "order_referer_url")
    private String orderRefererUrl;//页面链接
    @Column(name = "return_url")
    private String returnUrl;//页面回调通知地址
    @Column(name = "notify_url")
    private String notifyUrl;//后台异步通知地址
    @Column(name = "cancel_reason")
    private String cancelReason;//订单撤销原因
    @Column(name = "order_period")
    private Integer orderPeriod;//订单有效期
    @Column(name = "expire_time")
    private Date expireTime;//订单到期时间
    @Column(name = "pay_way_code")
    private String payWayCode;//支付通道编号
    @Column(name = "pay_way_name")
    private String payWayName;//支付方式名称
    @Column(name = "remark")
    private String remark;//备注
    @Column(name = "trx_type")
    private String trxType;//交易业务类型
    @Column(name = "trx_no")
    private String trxNo;//支付流水
    @Column(name = "pay_type_code")
    private String payTypeCode;//支付方式类型编码
    @Column(name = "pay_type_name")
    private String payTypeName;//支付方式类型名称
    @Column(name = "fund_into_type")
    private String fundIntoType;//资金流入类型
    @Column(name = "is_refund")
    private String isRefund;//是否退款
    @Column(name = "refund_times")
    private Short refundTimes;//退款次数
    @Column(name = "success_refund_amount")
    private BigDecimal successRefundAmount;//成功退款金额

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

    public String getEditor() {
        return editor;
    }

    public void setEditor(String editor) {
        this.editor = editor;
    }

    public Date getEditTime() {
        return editTime;
    }

    public void setEditTime(Date editTime) {
        this.editTime = editTime;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getMerchantOrderNo() {
        return merchantOrderNo;
    }

    public void setMerchantOrderNo(String merchantOrderNo) {
        this.merchantOrderNo = merchantOrderNo;
    }

    public BigDecimal getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(BigDecimal orderAmount) {
        this.orderAmount = orderAmount;
    }

    public String getOrderFrom() {
        return orderFrom;
    }

    public void setOrderFrom(String orderFrom) {
        this.orderFrom = orderFrom;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public String getMerchantNo() {
        return merchantNo;
    }

    public void setMerchantNo(String merchantNo) {
        this.merchantNo = merchantNo;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public String getOrderIp() {
        return orderIp;
    }

    public void setOrderIp(String orderIp) {
        this.orderIp = orderIp;
    }

    public String getOrderRefererUrl() {
        return orderRefererUrl;
    }

    public void setOrderRefererUrl(String orderRefererUrl) {
        this.orderRefererUrl = orderRefererUrl;
    }

    public String getReturnUrl() {
        return returnUrl;
    }

    public void setReturnUrl(String returnUrl) {
        this.returnUrl = returnUrl;
    }

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }

    public String getCancelReason() {
        return cancelReason;
    }

    public void setCancelReason(String cancelReason) {
        this.cancelReason = cancelReason;
    }

    public Integer getOrderPeriod() {
        return orderPeriod;
    }

    public void setOrderPeriod(Integer orderPeriod) {
        this.orderPeriod = orderPeriod;
    }

    public Date getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Date expireTime) {
        this.expireTime = expireTime;
    }

    public String getPayWayCode() {
        return payWayCode;
    }

    public void setPayWayCode(String payWayCode) {
        this.payWayCode = payWayCode;
    }

    public String getPayWayName() {
        return payWayName;
    }

    public void setPayWayName(String payWayName) {
        this.payWayName = payWayName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getTrxType() {
        return trxType;
    }

    public void setTrxType(String trxType) {
        this.trxType = trxType;
    }

    public String getTrxNo() {
        return trxNo;
    }

    public void setTrxNo(String trxNo) {
        this.trxNo = trxNo;
    }

    public String getPayTypeCode() {
        return payTypeCode;
    }

    public void setPayTypeCode(String payTypeCode) {
        this.payTypeCode = payTypeCode;
    }

    public String getPayTypeName() {
        return payTypeName;
    }

    public void setPayTypeName(String payTypeName) {
        this.payTypeName = payTypeName;
    }

    public String getFundIntoType() {
        return fundIntoType;
    }

    public void setFundIntoType(String fundIntoType) {
        this.fundIntoType = fundIntoType;
    }

    public String getIsRefund() {
        return isRefund;
    }

    public void setIsRefund(String isRefund) {
        this.isRefund = isRefund;
    }

    public Short getRefundTimes() {
        return refundTimes;
    }

    public void setRefundTimes(Short refundTimes) {
        this.refundTimes = refundTimes;
    }

    public BigDecimal getSuccessRefundAmount() {
        return successRefundAmount;
    }

    public void setSuccessRefundAmount(BigDecimal successRefundAmount) {
        this.successRefundAmount = successRefundAmount;
    }
}
