package com.xuge.ggkt.activity.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xuge.ggkt.activity.service.CouponInfoService;
import com.xuge.ggkt.result.R;
import com.xuge.model.activity.CouponInfo;
import com.xuge.model.activity.CouponUse;
import com.xuge.vo.activity.CouponUseQueryVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 优惠券信息 前端控制器
 * </p>
 *
 * @author xuge
 * @since 2022-07-07
 */
@RestController
@RequestMapping("/admin/activity/couponInfo")
@Api(tags="优惠卷管理")
public class CouponInfoController {
  @Autowired
  private CouponInfoService couponInfoService;
  @ApiOperation(value = "获取分页列表")
  @GetMapping("{page}/{limit}")
  public R index(
          @ApiParam(name = "page", value = "当前页码", required = true)
          @PathVariable Long page,
          @ApiParam(name = "limit", value = "每页记录数", required = true)
          @PathVariable Long limit) {
    Page<CouponInfo> pageParam = new Page<>(page, limit);
    IPage<CouponInfo> pageModel = couponInfoService.page(pageParam);
    return R.ok(pageModel);
  }
  @ApiOperation(value = "获取优惠券")
  @GetMapping("get/{id}")
  public R get(@PathVariable String id) {
    CouponInfo couponInfo = couponInfoService.getById(id);
    return R.ok(couponInfo);
  }

  @ApiOperation(value = "新增优惠券")
  @PostMapping("save")
  public R save(@RequestBody CouponInfo couponInfo) {
    couponInfoService.save(couponInfo);
    return R.ok(null);
  }

  @ApiOperation(value = "修改优惠券")
  @PutMapping("update")
  public R updateById(@RequestBody CouponInfo couponInfo) {
    couponInfoService.updateById(couponInfo);
    return R.ok(null);
  }

  @ApiOperation(value = "删除优惠券")
  @DeleteMapping("remove/{id}")
  public R remove(@PathVariable String id) {
    couponInfoService.removeById(id);
    return R.ok(null);
  }

  @ApiOperation(value="根据id列表删除优惠券")
  @DeleteMapping("batchRemove")
  public R batchRemove(@RequestBody List<String> idList){
    couponInfoService.removeByIds(idList);
    return R.ok(null);
  }
  @ApiOperation(value = "获取分页列表")
  @GetMapping("couponUse/{page}/{limit}")
  public R index(
          @ApiParam(name = "page", value = "当前页码", required = true)
          @PathVariable Long page,

          @ApiParam(name = "limit", value = "每页记录数", required = true)
          @PathVariable Long limit,
          @ApiParam(name = "couponUseVo", value = "查询对象", required = false)
                  CouponUseQueryVo couponUseQueryVo) {
    Page<CouponUse> pageParam = new Page<>(page, limit);
    IPage<CouponUse> pageModel = couponInfoService.selectCouponUsePage(pageParam, couponUseQueryVo);
    return R.ok(pageModel);
  }
}

