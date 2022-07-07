package com.xuge.ggkt.activity.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xuge.ggkt.activity.mapper.CouponInfoMapper;
import com.xuge.ggkt.activity.service.CouponInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xuge.ggkt.activity.service.CouponUseService;
import com.xuge.ggkt.client.user.UserInfoFeignClient;
import com.xuge.model.activity.CouponInfo;
import com.xuge.model.activity.CouponUse;
import com.xuge.model.user.UserInfo;
import com.xuge.vo.activity.CouponUseQueryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 优惠券信息 服务实现类
 * </p>
 *
 * @author xuge
 * @since 2022-07-07
 */
@Service
public class CouponInfoServiceImpl extends ServiceImpl<CouponInfoMapper, CouponInfo> implements CouponInfoService {
  @Autowired
  private CouponUseService couponUseService;
  @Autowired
  private UserInfoFeignClient userInfoFeignClient;
  //获取已经使用优惠卷列表
  @Override
  public IPage<CouponUse> selectCouponUsePage(Page<CouponUse> pageParam, CouponUseQueryVo couponUseQueryVo) {
    //获取条件值
//获取条件
    Long couponId = couponUseQueryVo.getCouponId();
    String couponStatus = couponUseQueryVo.getCouponStatus();
    String getTimeBegin = couponUseQueryVo.getGetTimeBegin();
    String getTimeEnd = couponUseQueryVo.getGetTimeEnd();
    //封装条件
    QueryWrapper<CouponUse> qw = new QueryWrapper<>();


    //拼接条件
    qw.eq(couponId!=null,"coupon_id",couponId);
    qw.eq(couponStatus!=null,"coupon_status",couponStatus);
    qw.ge(getTimeBegin!=null,"start_time",getTimeBegin);
    qw.le(getTimeEnd!=null,"end_time",getTimeEnd);
    //调用方法查询
    IPage<CouponUse> page = couponUseService.page(pageParam, qw);
    //封装用户昵称和手机号
    List<CouponUse> couponUseList = page.getRecords();
    couponUseList.stream().forEach(item->{
      this.getUserInfoBycouponUse(item);
    });
    return page;



  }
  //封装用户昵称和手机号
  private CouponUse getUserInfoBycouponUse(CouponUse item) {
    //获取用户ID
    Long userId = item.getUserId();
    //根据用户ID获取用户信息
    UserInfo userInfo = userInfoFeignClient.getById(userId);
    if(userInfo!=null){
      item.getParam().put("nickName", userInfo.getNickName());
      item.getParam().put("phone", userInfo.getPhone());
    }
    return item;
  }
}
