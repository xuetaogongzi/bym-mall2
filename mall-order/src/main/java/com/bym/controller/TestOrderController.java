package com.bym.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.bym.event.MallProducer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@Slf4j
@RestController
@RequestMapping("/test-order")
public class TestOrderController {

    @Autowired
    private MallProducer mallProducer;

    @GetMapping("/hello")
    @SentinelResource(value = "hello2", blockHandler = "hello_block")
    public String hello() {
        log.info("hello");
        // 测试 OpenFeign + Hystrix 降级、容错
//        try {
//            Thread.sleep(5000);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

//        mallProducer.sendOrderInfo(UUID.randomUUID().toString());

        // 测试Sentinel降级
//        int random = new Random().nextInt(100);
//        log.info("random={}", random);
//        if (random % 2 == 0) {
//            throw new RuntimeException();
//        }
        return "hello bym";
    }

    public String hello_block(BlockException ex) {
        log.info("接口被限流", ex);
        return "hello block";
    }
}
