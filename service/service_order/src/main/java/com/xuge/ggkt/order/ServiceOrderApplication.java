package com.xuge.ggkt.order;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * author: yjx
 * Date :2022/7/715:30
 **/
@SpringBootApplication
@Slf4j
@EnableDiscoveryClient
public class ServiceOrderApplication {
  public static void main(String[] args) {
    SpringApplication.run(ServiceOrderApplication.class, args);
    log.info("================订单服务启动成功================");
  }
}