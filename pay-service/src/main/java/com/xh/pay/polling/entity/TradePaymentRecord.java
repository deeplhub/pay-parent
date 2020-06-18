package com.xh.pay.polling.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <b>Title: 支付记录表</b>
 * <p>Description: </p>
 *
 * @author H.Yang
 * @email xhaimail@163.com
 * @date 2019年9月23日
 */
@Entity
@Table(name = "trade_payment_record")
public class TradePaymentRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;// 主键ID
    @Column(name = "version")
    private Integer version = 0;// 版本号默认为0
    @Column(name = "status")
    private String status;// 状态 PublicStatusEnum
    @Column(name = "creater")
    private String creater;// 创建人
    @Column(name = "create_time")
    private Date createTime = new Date();// 创建时间
    @Column(name = "editor")
    private String editor;// 修改人.
    @Column(name = "edit_time")
    private Date editTime;// 修改时间.
    @Column(name = "product_name")
    private String productName;//商品名称
    @Column(name = "merchant_order_no")
    private String merchantOrderNo;//商户订单号
    @Column(name = "trx_no")
    private String trxNo;//支付流水号
    @Column(name = "bank_order_no")
    private String bankOrderNo;//银行订单号
    @Column(name = "bank_trx_no")
    private String bankTrxNo;//银行流水号
    @Column(name = "merchant_name")
    private String merchantName;//商户名称
    @Column(name = "merchant_no")
    private String merchantNo;//商户编号
    @Column(name = "payer_user_no")
    private String payerUserNo;//付款方编号
    @Column(name = "payer_name")
    private String payerName;//付款方名称
    @Column(name = "payer_pay_amount")
    private BigDecimal payerPayAmount;//付款方支付金额
    @Column(name = "payer_fee")
    private BigDecimal payerFee;//付款方手续费
    @Column(name = "payer_account_type")
    private String payerAccountType;//付款方账户类型
    @Column(name = "receiver_user_no")
    private String receiverUserNo;//收款方编号
    @Column(name = "receiver_name")
    private String receiverName;//收款方名称
    @Column(name = "receiver_pay_amount")
    private BigDecimal receiverPayAmount;//收款方收款金额
    @Column(name = "receiver_fee")
    private BigDecimal receiverFee;//收款方手续费
    @Column(name = "receiver_account_type")
    private String receiverAccountType;//收款方账户类型
    @Column(name = "order_ip")
    private String orderIp;//下单IP
    @Column(name = "order_referer_url")
    private String orderRefererUrl;//页面链接
    @Column(name = "order_amount")
    private BigDecimal orderAmount;//订单金额
    @Column(name = "plat_income")
    private BigDecimal platIncome = BigDecimal.ZERO;//平台收入 初始创建默认为
    @Column(name = "fee_rate")
    private BigDecimal feeRate = BigDecimal.ZERO;//费率
    @Column(name = "plat_cost")
    private BigDecimal platCost = BigDecimal.ZERO;//平台成本
    @Column(name = "plat_profit")
    private BigDecimal platProfit = BigDecimal.ZERO;//平台利润
    @Column(name = "return_url")
    private String returnUrl;//支付结果页面通知地址
    @Column(name = "notify_url")
    private String notifyUrl;//支付结果后台通知地址
    @Column(name = "pay_way_code")
    private String payWayCode;//支付通道编码
    @Column(name = "pay_way_name")
    private String payWayName;//支付通道名称
    @Column(name = "pay_success_time")
    private Date paySuccessTime;//成功支付时间
    @Column(name = "complete_time")
    private Date completeTime;//完成时间
    @Column(name = "is_refund")
    private String isRefund;//是否退款
    @Column(name = "refund_times")
    private Integer refundTimes;//退款次数
    @Column(name = "success_refund_amount")
    private BigDecimal successRefundAmount;//成功退款金额
    @Column(name = "trx_type")
    private String trxType;//业务类型
    @Column(name = "order_from")
    private String orderFrom;//订单来源
    @Column(name = "pay_type_code")
    private String payTypeCode;//支付方式类型编码
    @Column(name = "pay_type_name")
    private String payTypeName;//支付方式类型名称
    @Column(name = "fund_into_type")
    private String fundIntoType;//资金流入类型
    @Column(name = "remark")
    private String remark;//备注
    @Column(name = "bank_return_msg")
    private String bankReturnMsg;//银行返回信息

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

    public String getTrxNo() {
        return trxNo;
    }

    public void setTrxNo(String trxNo) {
        this.trxNo = trxNo;
    }

    public String getBankOrderNo() {
        return bankOrderNo;
    }

    public void setBankOrderNo(String bankOrderNo) {
        this.bankOrderNo = bankOrderNo;
    }

    public String getBankTrxNo() {
        return bankTrxNo;
    }

    public void setBankTrxNo(String bankTrxNo) {
        this.bankTrxNo = bankTrxNo;
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

    public String getPayerUserNo() {
        return payerUserNo;
    }

    public void setPayerUserNo(String payerUserNo) {
        this.payerUserNo = payerUserNo;
    }

    public String getPayerName() {
        return payerName;
    }

    public void setPayerName(String payerName) {
        this.payerName = payerName;
    }

    public BigDecimal getPayerPayAmount() {
        return payerPayAmount;
    }

    public void setPayerPayAmount(BigDecimal payerPayAmount) {
        this.payerPayAmount = payerPayAmount;
    }

    public BigDecimal getPayerFee() {
        return payerFee;
    }

    public void setPayerFee(BigDecimal payerFee) {
        this.payerFee = payerFee;
    }

    public String getPayerAccountType() {
        return payerAccountType;
    }

    public void setPayerAccountType(String payerAccountType) {
        this.payerAccountType = payerAccountType;
    }

    public String getReceiverUserNo() {
        return receiverUserNo;
    }

    public void setReceiverUserNo(String receiverUserNo) {
        this.receiverUserNo = receiverUserNo;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public BigDecimal getReceiverPayAmount() {
        return receiverPayAmount;
    }

    public void setReceiverPayAmount(BigDecimal receiverPayAmount) {
        this.receiverPayAmount = receiverPayAmount;
    }

    public BigDecimal getReceiverFee() {
        return receiverFee;
    }

    public void setReceiverFee(BigDecimal receiverFee) {
        this.receiverFee = receiverFee;
    }

    public String getReceiverAccountType() {
        return receiverAccountType;
    }

    public void setReceiverAccountType(String receiverAccountType) {
        this.receiverAccountType = receiverAccountType;
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

    public BigDecimal getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(BigDecimal orderAmount) {
        this.orderAmount = orderAmount;
    }

    public BigDecimal getPlatIncome() {
        return platIncome;
    }

    public void setPlatIncome(BigDecimal platIncome) {
        this.platIncome = platIncome;
    }

    public BigDecimal getFeeRate() {
        return feeRate;
    }

    public void setFeeRate(BigDecimal feeRate) {
        this.feeRate = feeRate;
    }

    public BigDecimal getPlatCost() {
        return platCost;
    }

    public void setPlatCost(BigDecimal platCost) {
        this.platCost = platCost;
    }

    public BigDecimal getPlatProfit() {
        return platProfit;
    }

    public void setPlatProfit(BigDecimal platProfit) {
        this.platProfit = platProfit;
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

    public Date getPaySuccessTime() {
        return paySuccessTime;
    }

    public void setPaySuccessTime(Date paySuccessTime) {
        this.paySuccessTime = paySuccessTime;
    }

    public Date getCompleteTime() {
        return completeTime;
    }

    public void setCompleteTime(Date completeTime) {
        this.completeTime = completeTime;
    }

    public String getIsRefund() {
        return isRefund;
    }

    public void setIsRefund(String isRefund) {
        this.isRefund = isRefund;
    }

    public Integer getRefundTimes() {
        return refundTimes;
    }

    public void setRefundTimes(Integer refundTimes) {
        this.refundTimes = refundTimes;
    }

    public BigDecimal getSuccessRefundAmount() {
        return successRefundAmount;
    }

    public void setSuccessRefundAmount(BigDecimal successRefundAmount) {
        this.successRefundAmount = successRefundAmount;
    }

    public String getTrxType() {
        return trxType;
    }

    public void setTrxType(String trxType) {
        this.trxType = trxType;
    }

    public String getOrderFrom() {
        return orderFrom;
    }

    public void setOrderFrom(String orderFrom) {
        this.orderFrom = orderFrom;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getBankReturnMsg() {
        return bankReturnMsg;
    }

    public void setBankReturnMsg(String bankReturnMsg) {
        this.bankReturnMsg = bankReturnMsg;
    }

}
