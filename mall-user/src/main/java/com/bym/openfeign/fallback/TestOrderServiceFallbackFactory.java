package com.bym.openfeign.fallback;

import com.bym.openfeign.TestOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class TestOrderServiceFallbackFactory implements FallbackFactory<TestOrderService> {

    @Override
    public TestOrderService create(Throwable cause) {
        // 使用这种方法你可以捕捉到具体的异常cause
        return new TestOrderService() {
            @Override
            public String hello() {
                log.info("fallback factory hello", cause);
                return "hello default";
            }
        };
    }
}
