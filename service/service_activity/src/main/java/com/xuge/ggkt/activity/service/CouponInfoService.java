package com.xuge.ggkt.activity.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xuge.model.activity.CouponInfo;
import com.xuge.model.activity.CouponUse;
import com.xuge.vo.activity.CouponUseQueryVo;

/**
 * <p>
 * 优惠券信息 服务类
 * </p>
 *
 * @author xuge
 * @since 2022-07-07
 */
public interface CouponInfoService extends IService<CouponInfo> {

  IPage<CouponUse> selectCouponUsePage(Page<CouponUse> pageParam, CouponUseQueryVo couponUseQueryVo);
}
