package com.xuge.ggkt.order.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xuge.ggkt.order.mapper.OrderInfoMapper;
import com.xuge.ggkt.order.service.OrderDetailService;
import com.xuge.ggkt.order.service.OrderInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xuge.model.order.OrderDetail;
import com.xuge.model.order.OrderInfo;
import com.xuge.vo.order.OrderInfoQueryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 订单表 订单表 服务实现类
 * </p>
 *
 * @author xuge
 * @since 2022-07-07
 */
@Service
public class OrderInfoServiceImpl extends ServiceImpl<OrderInfoMapper, OrderInfo> implements OrderInfoService {
  @Autowired
  private OrderDetailService orderDetailService;

  @Override
  public Map<String, Object> findPageOrderInfo(Page<OrderInfo> pageParam, OrderInfoQueryVo orderInfoQueryVo) {
    //获取查询条件

    //orderInfoQueryVo获取查询条件
    Long userId = orderInfoQueryVo.getUserId();
    String outTradeNo = orderInfoQueryVo.getOutTradeNo();
    String phone = orderInfoQueryVo.getPhone();
    String createTimeEnd = orderInfoQueryVo.getCreateTimeEnd();
    String createTimeBegin = orderInfoQueryVo.getCreateTimeBegin();
    Integer orderStatus = orderInfoQueryVo.getOrderStatus();


    //拼接查询条件
    QueryWrapper<OrderInfo> qw = new QueryWrapper<>();
    qw.eq(userId!=null,"user_id",userId);
    qw.eq(outTradeNo!=null,"out_trade_no",outTradeNo);
    qw.eq(orderStatus!=null,"order_status",orderStatus);
    qw.eq(phone!=null,"phone",phone);
    if(!StringUtils.isEmpty(createTimeBegin)) {
      qw.ge("create_time",createTimeBegin);
    }
    if(!StringUtils.isEmpty(createTimeEnd)) {
      qw.le("create_time",createTimeEnd);
    }



    //执行分页查询
    Page<OrderInfo> page = baseMapper.selectPage(pageParam, qw);
    long totalCount = page.getTotal();
    long pageCount = page.getPages();
    List<OrderInfo> records = page.getRecords();
    //订单里面包含详情内容，封装详情数据，根据订单id查询详情
    records.stream().forEach(item -> {
      this.getOrderDetail(item);
    });


    //所有需要数据封装map集合，最终返回
    Map<String,Object> map = new HashMap<>();
    map.put("total",totalCount);
    map.put("pageCount",pageCount);
    map.put("records",records);
    return map;
  }

  private OrderInfo getOrderDetail(OrderInfo item) {
    //获取订单Id
    Long id = item.getId();
    //获取订单详情
    OrderDetail orderDetail = orderDetailService.getById(id);
    if(orderDetail!=null){
      //取出课程标题
      String courseName = orderDetail.getCourseName();
      //放入item中
      item.getParam().put("courseName",courseName);
    }
    return item;


  }
}
