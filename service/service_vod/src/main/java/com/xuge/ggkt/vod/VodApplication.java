package com.xuge.ggkt.vod;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * author: yjx
 * Date :2022/6/3014:28
 **/
@SpringBootApplication
@Slf4j
@EnableDiscoveryClient
public class VodApplication {
  public static void main(String[] args) {
    SpringApplication.run(VodApplication.class,args);
    System.out.println("(♥◠‿◠)ﾉﾞ 恭喜你, 教育项目启动成功   ლ(´ڡ`ლ)ﾞ  \n" +
            " .-------.       ____     __        \n" +
            " |  _ _   \\      \\   \\   /  /    \n" +
            " | ( ' )  |       \\  _. /  '       \n" +
            " |(_ o _) /        _( )_ .'         \n" +
            " | (_,_).' __  ___(_ o _)'          \n" +
            " |  |\\ \\  |  ||   |(_,_)'         \n" +
            " |  | \\ `'   /|   `-'  /           \n" +
            " |  |  \\    /  \\      /           \n" +
            " ''-'   `'-'    `-..-'              ");
    System.out.println("=============================================");

  }
}
