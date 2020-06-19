package com.xh.pay.polling.service.impl;

import com.xh.pay.common.core.utils.AliPayUtil;
import com.xh.pay.common.core.utils.MerchantApiUtil;
import com.xh.pay.notify.service.NotifyRecordService;
import com.xh.pay.polling.dao.rep.TradePaymentOrderRepDao;
import com.xh.pay.polling.dao.rep.TradePaymentRecordRepDao;
import com.xh.pay.polling.dao.rep.UserPayConfigRepDao;
import com.xh.pay.polling.entity.TradePaymentOrder;
import com.xh.pay.polling.entity.TradePaymentRecord;
import com.xh.pay.polling.entity.UserPayConfig;
import com.xh.pay.polling.service.TradePaymentRecordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Title:
 * Description:
 *
 * @author H.Yang
 * @email xhaimail@163.com
 * @date 2020/6/13
 */
@Service
public class TradePaymentRecordServiceImpl implements TradePaymentRecordService {

    private static final Logger LOG = LoggerFactory.getLogger(TradePaymentRecordServiceImpl.class);

    @Resource
    private TradePaymentRecordRepDao tradePaymentRecordRepDao;
    @Resource
    private TradePaymentOrderRepDao tradePaymentOrderRepDao;
    @Resource
    private UserPayConfigRepDao userPayConfigRepDao;
    @Resource
    private NotifyRecordService notifyRecordService;

    /**
     * 处理交易记录 如果交易记录是成功或者本地未支付,查询上游已支付,返回TRUE 如果上游支付结果为未支付,返回FALSE
     *
     * @param bankOrderNo 银行订单号
     * @return
     */
    @Transactional
    @Override
    public boolean processingTradeRecord(String bankOrderNo) {
        //根据订单号获取支付信息
        TradePaymentRecord tradePaymentRecord = tradePaymentRecordRepDao.getByBankOrderNo(bankOrderNo);
        if (tradePaymentRecord == null) {
            LOG.info("不存在该银行订单号[{}]对应的交易记录", bankOrderNo);
            throw new RuntimeException("非法订单号");
        }
        LOG.info("订单号:[{}],交易类型：[{}]", tradePaymentRecord.getBankOrderNo(), tradePaymentRecord.getPayWayCode());

        if (!"等待支付".equals(tradePaymentRecord.getStatus())) {
            LOG.info("该银行订单号[{}]对应的交易记录状态为:{},不需要再处理", bankOrderNo, tradePaymentRecord.getStatus());
            return true;
        }

        // 模拟支付宝
        if ("支付宝".equals(tradePaymentRecord.getPayWayCode())) {
            if ("即时到账".equals(tradePaymentRecord.getPayTypeCode())) {
                // 支付宝--即时到账
                LOG.info("支付宝--即时到账订单查询!订单号:[{}]", tradePaymentRecord.getBankOrderNo());

                // 请求支付宝 统一收单线下交易查询 接口
                Map<String, Object> resultMap = AliPayUtil.singleTradeQuery(tradePaymentRecord.getBankOrderNo());
                if (resultMap.isEmpty()) {
                    return false;
                }

                // 当返回状态为“TRADE_FINISHED”交易成功结束和“TRADE_SUCCESS”支付成功时更新交易状态
                if ("TRADE_SUCCESS".equals(resultMap.get("trade_status")) || "TRADE_FINISHED".equals(resultMap.get("trade_status"))) {
                    this.completeSuccessOrder(tradePaymentRecord, tradePaymentRecord.getBankTrxNo(), new Date(), "订单交易成功");
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * 订单支付成功
     *
     * @param tradePaymentRecord
     * @param bankTrxNo
     * @param timeEnd
     * @param bankReturnMsg
     */
    private void completeSuccessOrder(TradePaymentRecord tradePaymentRecord, String bankTrxNo, Date timeEnd, String bankReturnMsg) {
        LOG.info("订单支付成功!");
        tradePaymentRecord.setPaySuccessTime(timeEnd);
        tradePaymentRecord.setBankTrxNo(bankTrxNo);// 设置银行流水号
        tradePaymentRecord.setBankReturnMsg(bankReturnMsg);
        tradePaymentRecord.setStatus("交易成功");
        // 更新
        tradePaymentRecordRepDao.save(tradePaymentRecord);

        TradePaymentOrder tradePaymentOrder = tradePaymentOrderRepDao.selectByMerchantNoAndMerchantOrderNo(tradePaymentRecord.getMerchantNo(), tradePaymentRecord.getMerchantOrderNo());
        tradePaymentOrder.setStatus("交易成功");
        tradePaymentOrder.setTrxNo(tradePaymentRecord.getTrxNo());// 设置支付平台支付流水号
        // 更新
        tradePaymentOrderRepDao.save(tradePaymentOrder);

        //获取回调地址
        String notifyUrl = this.getMerchantNotifyUrl(tradePaymentRecord, tradePaymentOrder, tradePaymentRecord.getNotifyUrl(), "交易成功");
        notifyRecordService.notifySend(notifyUrl, tradePaymentRecord.getMerchantOrderNo(), tradePaymentRecord.getMerchantNo());
    }

    /**
     * 获取回调地址
     *
     * @param tradePaymentRecord
     * @param tradePaymentOrder
     * @param notifyUrl
     * @param tradeStatus
     * @return
     */
    private String getMerchantNotifyUrl(TradePaymentRecord tradePaymentRecord, TradePaymentOrder tradePaymentOrder, String notifyUrl, String tradeStatus) {
        UserPayConfig userPayConfig = userPayConfigRepDao.getByUserNo(tradePaymentRecord.getMerchantNo());
        if (userPayConfig == null) {
            throw new RuntimeException("用户支付配置有误");
        }
        Map<String, Object> paramMap = new HashMap<>();

        paramMap.put("payKey", userPayConfig.getPayKey());// 企业支付KEY
        paramMap.put("productName", tradePaymentRecord.getProductName());// 商品名称
        paramMap.put("orderNo", tradePaymentRecord.getMerchantOrderNo());// 订单编号
        paramMap.put("orderPrice", tradePaymentRecord.getOrderAmount());// 订单金额 ,单位:元
        paramMap.put("payWayCode", tradePaymentRecord.getPayWayCode());// 支付方式编码 支付宝:ALIPAY/微信:WEIXIN
        paramMap.put("tradeStatus", tradeStatus);// 交易状态
        paramMap.put("orderTime", new SimpleDateFormat("yyyyMMddHHmmss").format(tradePaymentOrder.getOrderTime()));// 订单时间
        String remark = tradePaymentRecord.getRemark(); // 支付备注
        paramMap.put("remark", remark);
        String trxNo = tradePaymentRecord.getTrxNo();// 支付流水号
        paramMap.put("trxNo", trxNo);

        String paramStr = MerchantApiUtil.getParamStr(paramMap);
        String sign = MerchantApiUtil.getSign(paramMap, userPayConfig.getPaySecret());
        notifyUrl = notifyUrl + "?" + paramStr + "&sign=" + sign;

        return notifyUrl;
    }
}
