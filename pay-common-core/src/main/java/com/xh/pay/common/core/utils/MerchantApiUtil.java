package com.xh.pay.common.core.utils;

import org.apache.commons.lang3.StringUtils;
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
public class MerchantApiUtil {

    private static final Logger LOG = LoggerFactory.getLogger(MerchantApiUtil.class);

    public static String getParamStr(Map<String, Object> paramMap) {
        SortedMap<String, Object> smap = new TreeMap<String, Object>(paramMap);
        StringBuffer stringBuffer = new StringBuffer();
        for (Map.Entry<String, Object> m : smap.entrySet()) {
            Object value = m.getValue();
            if (value != null && StringUtils.isNotBlank(String.valueOf(value))) {
                stringBuffer.append(m.getKey()).append("=").append(value).append("&");
            }
        }
        stringBuffer.delete(stringBuffer.length() - 1, stringBuffer.length());

        return stringBuffer.toString();
    }

    public static String getSign(Map<String, Object> paramMap, String paySecret) {
        SortedMap<String, Object> smap = new TreeMap<String, Object>(paramMap);

        if (smap.get("sign") != null) {
            smap.remove("sign");
        }

        StringBuffer stringBuffer = new StringBuffer();
        for (Map.Entry<String, Object> m : smap.entrySet()) {
            Object value = m.getValue();
            if (value != null && StringUtils.isNotBlank(String.valueOf(value))) {
                stringBuffer.append(m.getKey()).append("=").append(value).append("&");
            }
        }
        stringBuffer.delete(stringBuffer.length() - 1, stringBuffer.length());

        LOG.info("签名原文：{}", stringBuffer.toString());

        String argPreSign = stringBuffer.append("&paySecret=").append(paySecret).toString();
        String signStr = MD5Util.encode(argPreSign).toUpperCase();
        LOG.info("签名结果:{}", signStr);
        return signStr;
    }
}
