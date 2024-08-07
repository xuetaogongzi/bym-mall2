package com.bym.config;

import com.alibaba.cloud.nacos.NacosConfigManager;
import com.alibaba.cloud.nacos.NacosConfigProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class DynamicRoutesLoader implements InitializingBean {

    @Autowired
    private NacosConfigManager configManager;

    @Autowired
    private NacosConfigProperties configProperties;

    @Autowired
    private DynamicRoutesListener dynamicRoutesListener;

    private static final String ROUTES_CONFIG = "mall-gateway-router";

    @Override
    public void afterPropertiesSet() throws Exception {
        // 首次加载
        String routes = configManager.getConfigService().getConfig(ROUTES_CONFIG, configProperties.getGroup(), 10000);
        log.info("routes={}", routes);
        dynamicRoutesListener.receiveConfigInfo(routes);

        // 注册监听器
        configManager.getConfigService().addListener(ROUTES_CONFIG, configProperties.getGroup(), dynamicRoutesListener);
    }
}
