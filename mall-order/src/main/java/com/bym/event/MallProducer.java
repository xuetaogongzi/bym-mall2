package com.bym.event;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class MallProducer {

    @Autowired
    private StreamBridge streamBridge;

    public void sendOrderInfo(String orderNo) {
        log.info("sendOrderInfo orderNo: {}", orderNo);
        streamBridge.send(EventConstant.ADD_PAY_EVENT, orderNo);
    }
}
