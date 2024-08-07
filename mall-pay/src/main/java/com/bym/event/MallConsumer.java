package com.bym.event;

import com.bym.service.PayService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.function.Consumer;

@Slf4j
@Service
public class MallConsumer {

    @Autowired
    private PayService payService;

    @Bean
    public Consumer<String> addPay() {
        return request -> {
            log.info("receive: {}", request);
            payService.addPay(request);
        };
    }
}
