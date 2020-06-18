package com.xh.pay.web.gateway;

import com.alibaba.fastjson.JSON;
import com.xh.pay.polling.service.TradePaymentRecordService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Title:
 * Description:
 *
 * @author H.Yang
 * @email xhaimail@163.com
 * @date 2020/6/15
 */
@RestController
public class WebGatewayController {

    @Resource
    private TradePaymentRecordService tradePaymentRecordService;

    @RequestMapping(value = "/index")
    public String index() {
        System.out.println("访问成功！");

        String bankOrderNo = "100001";
        String json = JSON.toJSONString(tradePaymentRecordService.processingTradeRecord(bankOrderNo));
        return json;
    }
}
