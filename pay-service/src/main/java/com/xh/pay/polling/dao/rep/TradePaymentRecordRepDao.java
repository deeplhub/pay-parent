package com.xh.pay.polling.dao.rep;

import com.xh.pay.polling.entity.TradePaymentRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Title:
 * Description:
 *
 * @author H.Yang
 * @email xhaimail@163.com
 * @date 2020/6/13
 */
public interface TradePaymentRecordRepDao extends JpaRepository<TradePaymentRecord, Long> {

    /**
     * 根据订单号获取支付信息
     *
     * @param bankOrderNo
     * @return
     */
    @Query(value = "SELECT tpr FROM TradePaymentRecord tpr WHERE tpr.bankOrderNo=?1")
    TradePaymentRecord getByBankOrderNo(String bankOrderNo);

}
