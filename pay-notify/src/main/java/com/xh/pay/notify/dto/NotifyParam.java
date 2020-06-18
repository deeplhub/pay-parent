package com.xh.pay.notify.dto;

import java.util.Map;

/**
 * Title: 回调参数类
 * Description:
 *
 * @author H.Yang
 * @email xhaimail@163.com
 * @date 2020/6/14
 */
public class NotifyParam {
    private Map<Integer, Integer> notifyParams;// 通知时间次数map
    private String successValue;// 通知后用于判断是否成功的返回值。由HttpResponse获取

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

    public Integer getMaxNotifyTime() {
        return notifyParams == null ? 0 : notifyParams.size();
    }


}
