package com.bym.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.bym.openfeign.TestOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
@RequestMapping("/test-user")
@RefreshScope
public class TestUserController {

    @Autowired
    private TestOrderService testOrderService;

    @Value("${name:wxt}")
    private String name;

    @Value("${age:32}")
    private String age;

    @GetMapping("/hello")
    @SentinelResource(value = "hello1", blockHandler = "hello_block", fallback = "hello_fallback")
    public String hello(HttpServletRequest request) {
        log.info("hello name={}, age={}", name, age);
        log.info("origin={}, referer={}, path={}", request.getHeader("Origin"), request.getHeader("Referer"), request.getRequestURI());
//        log.info("hello");
        return testOrderService.hello();
    }

    public String hello_block(BlockException ex) {
        log.info("接口被限流", ex);
        return "hello block";
    }

    // 接口被降级时的方法
    public String hello_fallback() {
        log.info("接口被降级");
        return "hello fallback";
    }
}
