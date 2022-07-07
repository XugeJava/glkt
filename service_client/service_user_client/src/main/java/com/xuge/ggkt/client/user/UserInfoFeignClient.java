package com.xuge.ggkt.client.user;

import com.xuge.model.user.UserInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * author: yjx
 * Date :2022/7/716:54
 **/
@FeignClient(value = "service-user")
public interface UserInfoFeignClient {

  @GetMapping("/admin/user/userInfo/inner/getById/{id}")
  UserInfo getById(@PathVariable Long id);

}