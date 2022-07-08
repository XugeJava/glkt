package com.xuge.ggkt.wechat.controller;


import com.alibaba.fastjson.JSONObject;
import com.xuge.ggkt.result.R;
import com.xuge.ggkt.wechat.service.MenuService;
import com.xuge.ggkt.wechat.utils.ConstantPropertiesUtil;
import com.xuge.ggkt.wechat.utils.HttpClientUtils;
import com.xuge.model.wechat.Menu;
import com.xuge.vo.wechat.MenuVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import me.chanjar.weixin.common.error.WxErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
@Api(tags="微信菜单管理")
public class MenuController {
  @Autowired
  private MenuService menuService;
  @ApiOperation(value = "删除菜单")
  @DeleteMapping("removeMenu")
  public R removeMenu() {
    menuService.removeMenu();
    return R.ok(null);
  }
  @ApiOperation("同步菜单")
  @GetMapping("syncMenu")
  public R syncMenu(){
    menuService.syncMenu();
    return R.ok(null);
  }

  //获取access_token
  @GetMapping("getAccessToken")
  public R getAccessToken() {
    try {
      //拼接请求地址
      StringBuffer buffer = new StringBuffer();
      buffer.append("https://api.weixin.qq.com/cgi-bin/token");
      buffer.append("?grant_type=client_credential");
      buffer.append("&appid=%s");
      buffer.append("&secret=%s");
      //请求地址设置参数
      String url = String.format(buffer.toString(),
              ConstantPropertiesUtil.ACCESS_KEY_ID,
              ConstantPropertiesUtil.ACCESS_KEY_SECRET);
      //发送http请求
      String tokenString = HttpClientUtils.get(url);
      //获去access_token


      JSONObject jsonObject = JSONObject.parseObject(tokenString);
      String access_token = jsonObject.getString("access_token");
      //返回
      return R.ok(access_token);
    } catch (Exception e) {
      e.printStackTrace();
      return R.fail(null);
    }
  }
    //获取所有菜单，按照一级和二级菜单封装
  @ApiOperation("获取所有菜单")
  @GetMapping("findMenuInfo")
  public R findMenuInfo() {
    List<MenuVo> list = menuService.findMenuInfo();
    return R.ok(list);
  }

  //获取所有一级菜单
  @ApiOperation("获取所有一级菜单")
  @GetMapping("findOneMenuInfo")
  public R findOneMenuInfo() {
    List<Menu> list = menuService.findMenuOneInfo();
    return R.ok(list);
  }

  @ApiOperation(value = "根据id获取菜单")
  @GetMapping("get/{id}")
  public R get(@PathVariable Long id) {
    Menu menu = menuService.getById(id);
    return R.ok(menu);
  }

  @ApiOperation(value = "新增菜单")
  @PostMapping("save")
  public R save(@RequestBody Menu menu) {
    menuService.save(menu);
    return R.ok(null);
  }

  @ApiOperation(value = "修改菜单")
  @PutMapping("update")
  public R updateById(@RequestBody Menu menu) {
    menuService.updateById(menu);
    return R.ok(null);
  }

  @ApiOperation(value = "删除菜单")
  @DeleteMapping("remove/{id}")
  public R remove(@PathVariable Long id) {
    menuService.removeById(id);
    return R.ok(null);
  }

  @ApiOperation(value = "根据id列表删除")
  @DeleteMapping("batchRemove")
  public R batchRemove(@RequestBody List<Long> idList) {
    menuService.removeByIds(idList);
    return R.ok(null);
  }
}



