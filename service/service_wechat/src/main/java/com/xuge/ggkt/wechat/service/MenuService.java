package com.xuge.ggkt.wechat.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xuge.model.wechat.Menu;
import com.xuge.vo.wechat.MenuVo;

import java.util.List;

/**
 * <p>
 * 订单明细 订单明细 服务类
 * </p>
 *
 * @author xuge
 * @since 2022-07-07
 */
public interface MenuService extends IService<Menu> {

  List<MenuVo> findMenuInfo();

  List<Menu> findMenuOneInfo();

  void syncMenu();

  void removeMenu();
}
