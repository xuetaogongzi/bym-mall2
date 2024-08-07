package com.bym.openfeign.fallback;

import com.bym.openfeign.TestOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class TestOrderServiceFallback implements TestOrderService {

    @Override
    public String hello() {
        log.info("fallback hello");
        return "hello default";
    }
}
