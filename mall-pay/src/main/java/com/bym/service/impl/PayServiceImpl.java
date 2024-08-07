package com.bym.service.impl;

import com.bym.service.PayService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PayServiceImpl implements PayService {

    @Override
    public void addPay(String orderNo) {
        log.info("addPay orderNo={}", orderNo);
    }
}
