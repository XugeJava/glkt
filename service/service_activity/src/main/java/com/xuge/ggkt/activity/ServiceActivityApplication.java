package com.xuge.ggkt.activity;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * author: yjx
 * Date :2022/7/716:29
 **/
@SpringBootApplication
@EnableDiscoveryClient
@Slf4j
@EnableFeignClients(basePackages = "com.xuge")
@ComponentScan(basePackages = "com.xuge")
public class ServiceActivityApplication {

  public static void main(String[] args) {
    SpringApplication.run(ServiceActivityApplication.class, args);
    log.info("==========优惠卷服务启动成功=============");
  }
}