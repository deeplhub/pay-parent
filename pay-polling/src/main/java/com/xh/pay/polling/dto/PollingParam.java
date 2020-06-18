package com.xh.pay.polling.dto;

import java.util.Map;

/**
 * Title:
 * Description:
 *
 * @author H.Yang
 * @email xhaimail@163.com
 * @date 2020/6/13
 */
public class PollingParam {

    private Map<Integer, Integer> notifyParams;//通知参数（通知规则Map）

    /**
     * 通知后用于判断是否成功的返回值（成功标识）,由HttpResponse获取
     */
    private String successValue;

    public Map<Integer, Integer> getNotifyParams() {
        return notifyParams;
    }

    public void setNotifyParams(Map<Integer, Integer> notifyParams) {
        this.notifyParams = notifyParams;
    }

    public String getSuccessValue() {
        return successValue;
    }

    public void setSuccessValue(String successValue) {
        this.successValue = successValue;
    }

    /**
     * 最大通知次数限制.
     *
     * @return
     */
    public Integer getMaxNotifyTimes() {
        return notifyParams == null ? 0 : notifyParams.size();
    }
}
