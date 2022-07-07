package com.xuge.ggkt.wechat.controller;


import com.xuge.ggkt.wechat.service.MenuService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 订单明细 订单明细 前端控制器
 * </p>
 *
 * @author xuge
 * @since 2022-07-07
 */
@RestController
@RequestMapping("/admin/wechat/menu")
@ApiOperation("微信菜单管理")
public class MenuController {
  @Autowired
  private MenuService menuService;


}

