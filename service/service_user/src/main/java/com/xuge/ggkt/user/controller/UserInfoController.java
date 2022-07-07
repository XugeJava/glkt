package com.xuge.ggkt.user.controller;


import com.xuge.ggkt.user.service.UserInfoService;
import com.xuge.model.user.UserInfo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author xuge
 * @since 2022-07-07
 */
@RestController
@RequestMapping("/admin/user/userInfo")
public class UserInfoController {

  @Autowired
  private UserInfoService userService;

  @ApiOperation(value = "获取")
  @GetMapping("inner/getById/{id}")
  public UserInfo getById(@PathVariable Long id) {
    return userService.getById(id);
  }
}
