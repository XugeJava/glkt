package com.xuge.ggkt.order.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xuge.ggkt.order.service.OrderInfoService;
import com.xuge.ggkt.result.R;
import com.xuge.model.order.OrderInfo;
import com.xuge.vo.order.OrderInfoQueryVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * <p>
 * 订单表 订单表 前端控制器
 * </p>
 *
 * @author xuge
 * @since 2022-07-07
 */
@RestController
@RequestMapping("/admin/order/orderInfo")
public class OrderInfoController {
  @Autowired
  private OrderInfoService orderInfoService;
  @ApiOperation(value = "获取分页列表")
  @GetMapping("{page}/{limit}")
  public R page(@PathVariable Integer page, @PathVariable Integer limit, OrderInfoQueryVo orderInfoQueryVo){
    Page<OrderInfo> pageParam = new Page<>(page, limit);
    Map<String,Object> map = orderInfoService.findPageOrderInfo(pageParam, orderInfoQueryVo);
    return R.ok(map);

  }

}

