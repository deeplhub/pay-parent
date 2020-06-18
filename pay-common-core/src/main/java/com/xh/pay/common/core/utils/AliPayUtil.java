package com.xh.pay.common.core.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * Title:
 * Description:
 *
 * @author H.Yang
 * @email xhaimail@163.com
 * @date 2020/6/13
 */
public class AliPayUtil {

    private static final Logger LOG = LoggerFactory.getLogger(AliPayUtil.class);

    /**
     * 交易查询
     *
     * @param outTradeNo
     * @return
     */
    public static Map<String, Object> singleTradeQuery(String outTradeNo) {
        LOG.info("alipay.trade.query(统一收单线下交易查询)");
        // 请求支付宝 统一收单线下交易查询 接口
        //....

        //返回结果
        SortedMap<String, Object> responseMap = new TreeMap<>();
        responseMap.put("code", "10000");
        responseMap.put("msg", "Success");
        responseMap.put("sign", "ASDFGHJKLQWERTYUIOPZXCVBNM");
        responseMap.put("trade_no", "2013112011001004330000121536");// 支付宝交易号
        responseMap.put("out_trade_no", outTradeNo);// 商家订单号
        responseMap.put("buyer_logon_id", "2088101117955611");// 买家支付宝账号
        responseMap.put("trade_status", "TRADE_SUCCESS");//交易状态
        responseMap.put("total_amount", "0.01");// 交易的订单金额
        responseMap.put("buyer_user_id", "2088101117955611");// 买家在支付宝的用户id

        return responseMap;
    }
}
