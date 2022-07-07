package com.xuge.ggkt.gateway;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@Slf4j
public class ApiGatewayApplication {
    public static void main(String[] args) {

        SpringApplication.run(ApiGatewayApplication.class, args);
        log.info("=================网关服务启动成功=================");
    }
}
