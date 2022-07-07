package com.xuge.ggkt.user;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * author: yjx
 * Date :2022/7/716:48
 **/
@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.xuge.ggkt.user.mapper")
@Slf4j
public class ServiceUserApplication {

  public static void main(String[] args) {
    SpringApplication.run(ServiceUserApplication.class, args);
    log.info("===================用户模块启动成功呢===================");
  }

}