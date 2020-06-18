package com.xh.pay.polling.dao.rep;

import com.xh.pay.polling.entity.TradePaymentOrder;
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
public interface TradePaymentOrderRepDao extends JpaRepository<TradePaymentOrder, Long> {

    @Query("select tpo from TradePaymentOrder tpo where tpo.merchantNo=?1 and tpo.merchantOrderNo=?2")
    TradePaymentOrder selectByMerchantNoAndMerchantOrderNo(String merchantNo, String merchantOrderNo);
}
