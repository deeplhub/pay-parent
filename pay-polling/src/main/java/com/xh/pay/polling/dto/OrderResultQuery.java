package com.xh.pay.polling.dto;

import com.alibaba.fastjson.JSONObject;

import java.util.Date;
import java.util.Map;

/**
 * <b>Title: 订单结果查询实体,主要用于MQ查询上游订单结果时,查询规则及查询结果</b>
 * <p>Description: </p>
 *
 * @author H.Yang
 * @email xhaimail@163.com
 * @date 2019年9月23日
 */
public class OrderResultQuery {

    private Long id;// 主键ID.
    private Integer version = 0;// 版本号默认为0
    private String status;// 状态 PublicStatusEnum
    private String creater;// 创建人.
    private Date createTime = new Date();// 创建时间.
    private String editor;// 修改人.
    private Date editTime;// 修改时间.
    private String remark;// 描述

    /**
     * 通知规则
     */
    private String notifyRule;
    /**
     * 最后一次通知时间
     **/
    private Date lastNotifyTime;
    /**
     * 通知次数
     **/
    private Integer notifyTimes;
    /**
     * 限制通知次数
     **/
    private Integer limitNotifyTimes;
    /**
     * 银行订单号
     **/
    private String bankOrderNo;

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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getNotifyRule() {
        return notifyRule;
    }

    public void setNotifyRule(String notifyRule) {
        this.notifyRule = notifyRule;
    }

    public Date getLastNotifyTime() {
        return lastNotifyTime;
    }

    public void setLastNotifyTime(Date lastNotifyTime) {
        this.lastNotifyTime = lastNotifyTime;
    }

    public Integer getNotifyTimes() {
        return notifyTimes;
    }

    public void setNotifyTimes(Integer notifyTimes) {
        this.notifyTimes = notifyTimes;
    }

    public Integer getLimitNotifyTimes() {
        return limitNotifyTimes;
    }

    public void setLimitNotifyTimes(Integer limitNotifyTimes) {
        this.limitNotifyTimes = limitNotifyTimes;
    }

    public String getBankOrderNo() {
        return bankOrderNo;
    }

    public void setBankOrderNo(String bankOrderNo) {
        this.bankOrderNo = bankOrderNo;
    }

    /**
     * 获取通知规则
     *
     * @return
     */
    public Map<Integer, Integer> getNotifyRuleMap() {
        return (Map) JSONObject.parseObject(getNotifyRule());
    }
}
