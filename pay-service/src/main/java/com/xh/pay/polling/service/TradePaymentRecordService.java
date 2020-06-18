package com.xh.pay.polling.service;

/**
 * <b>Title: 付款记录</b>
 * <p>Description: </p>
 * 
 * @author H.Yang
 * @email xhaimail@163.com
 * @date 2019年9月23日
 */
public interface TradePaymentRecordService {

	/**
	 * 处理交易记录
	 * 如果交易记录是成功或者本地未支付,查询上游已支付,返回TRUE
	 * 如果上游支付结果为未支付,返回FALSE
	 *
	 * @param bankOrderNo 银行订单号
	 * @return
	 */
	boolean processingTradeRecord(String bankOrderNo);
}
