package com.xuge.ggkt.vod.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * author: yjx
 * Date :2022/6/3014:30
 **/
@Configuration
@ComponentScan(basePackages = "com.xuge")
@MapperScan("com.xuge.ggkt.vod.mapper")
public class GlobalConfig {
  /**
   *
   * @return  分页插件对象
   */
  @Bean
  public PaginationInterceptor paginationInterceptor(){
    return new PaginationInterceptor();
  }
}
