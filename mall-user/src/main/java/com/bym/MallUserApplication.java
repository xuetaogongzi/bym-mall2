package com.bym;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
@SpringBootApplication
@EnableFeignClients(basePackages = {"com.bym"})
public class MallUserApplication {

//    public static List<String> array = new ArrayList<>();
//
//    public static void apply() {
//        String str = UUID.randomUUID().toString();
//        array.add(str);
//    }

    public static void main(String[] args) {
//        for (int i = 0; i < 10000; i++) {
//            apply();
//        }

        SpringApplication.run(MallUserApplication.class, args);
//        ConfigurableApplicationContext applicationContext = SpringApplication.run(MallUserApplication.class, args);
//        String name = applicationContext.getEnvironment().getProperty("name");
//        String age = applicationContext.getEnvironment().getProperty("age");
//        log.info("MallUserApplication name={}, age={}", name, age);
    }
}
