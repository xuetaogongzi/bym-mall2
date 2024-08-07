package com.bym.openfeign;

import com.bym.openfeign.fallback.TestOrderServiceFallback;
import com.bym.openfeign.fallback.TestOrderServiceFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "mall-order", path = "/test-order",
//        fallback = TestOrderServiceFallback.class // 通过fallback指定降级逻辑
        fallbackFactory = TestOrderServiceFallbackFactory.class // 通过抽象工厂来定义降级逻辑
)
public interface TestOrderService {

    @GetMapping("/hello")
    String hello();
}
