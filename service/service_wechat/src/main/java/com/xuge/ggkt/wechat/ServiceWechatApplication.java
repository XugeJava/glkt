package com.xuge.ggkt.wechat;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * author: yjx
 * Date :2022/7/89:56
 **/
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "com.xuge")
@MapperScan("com.xuge.ggkt.wechat.mapper")
@ComponentScan(basePackages = "com.xuge")
@Slf4j
public class ServiceWechatApplication {
  public static void main(String[] args) {
    SpringApplication.run(ServiceWechatApplication.class, args);
    log.info("========微信小城序菜单管理服务启动成功========");
  }
}