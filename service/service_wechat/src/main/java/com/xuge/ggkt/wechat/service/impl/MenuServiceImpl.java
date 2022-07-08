package com.xuge.ggkt.wechat.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xuge.ggkt.wechat.mapper.MenuMapper;
import com.xuge.ggkt.wechat.service.MenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xuge.model.wechat.Menu;
import com.xuge.vo.wechat.MenuVo;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 订单明细 订单明细 服务实现类
 * </p>
 *
 * @author xuge
 * @since 2022-07-07
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {
  @Autowired
  private WxMpService wxMpService;
  //获取所有菜单
  @Override
  public List<MenuVo> findMenuInfo() {
    List<MenuVo>list=new ArrayList<>();
    //所有菜单
    List<Menu> menuList = baseMapper.selectList(null);
    //获取所有的一级菜单
    List<Menu> oneMenuList = menuList.stream().filter(menu -> menu.getParentId().longValue() == 0).collect(Collectors.toList());
    //遍历一级菜单
    for(Menu oneMenu:oneMenuList){
      MenuVo oneMenuVo = new MenuVo();
      BeanUtils.copyProperties(oneMenu,oneMenuVo);
      //获取二级菜单
      List<Menu> twoMenuList = menuList.stream()
              .filter(menu -> menu.getParentId().longValue() == oneMenu.getId())
              .sorted(Comparator.comparing(Menu::getSort))
              .collect(Collectors.toList());
      List<MenuVo>children=new ArrayList<>();
      //遍历二级菜单列表，用于封装children集合
      for (Menu twoMenu : twoMenuList) {
        MenuVo twoMenuVo = new MenuVo();
        BeanUtils.copyProperties(twoMenu,twoMenuVo);
        children.add(twoMenuVo);
      }
      //设置当前一级分类的二级分类菜单集合属性
      oneMenuVo.setChildren(children);
      //将当前分封装好的一级菜单Vo对象加入返回集合中
      list.add(oneMenuVo);
    }
    return list;
  }
 //获取一级菜单
  @Override
  public List<Menu> findMenuOneInfo() {
    QueryWrapper<Menu> qw = new QueryWrapper<>();
    qw.eq("parent_id",0);
    return  baseMapper.selectList(qw);
  }
  /**
   * 说明：
   * 自定义菜单最多包括3个一级菜单，每个一级菜单最多包含5个二级菜单。
   * 一级菜单最多4个汉字，二级菜单最多8个汉字，多出来的部分将会以“...”代替。
   * 创建自定义菜单后，菜单的刷新策略是，在用户进入公众号会话页或公众号profile页时，如果发现上一次拉取菜单的请求在5分钟以前，就会拉取一下菜单，如果菜单有更新，就会刷新客户端的菜单。测试时可以尝试取消关注公众账号后再次关注，则可以看到创建后的效果。
   */
  @Override
  public void syncMenu()  {
    //获取所有菜单
    List<MenuVo> menuVoList = this.findMenuInfo();
    //总菜单==json数组
    JSONArray buttonList = new JSONArray();
    //遍历所有一级菜单
    for(MenuVo oneMenuVo : menuVoList) {
      //创建json对象
      JSONObject one = new JSONObject();
      one.put("name", oneMenuVo.getName());
      JSONArray subButton = new JSONArray();
      //遍历的当前菜单的二级菜单
      for(MenuVo twoMenuVo : oneMenuVo.getChildren()) {
        JSONObject view = new JSONObject();
        view.put("type", twoMenuVo.getType());
        if(twoMenuVo.getType().equals("view")) {
          view.put("name", twoMenuVo.getName());
          view.put("url", "http://ggkt2.vipgz1.91tunnel.com/#"
                  +twoMenuVo.getUrl());
        } else {
          view.put("name", twoMenuVo.getName());
          view.put("key", twoMenuVo.getMeunKey());
        }
        subButton.add(view);
      }
      one.put("sub_button", subButton);
      buttonList.add(one);
    }
    //菜单
    JSONObject button = new JSONObject();
    button.put("button", buttonList);
    try {
      //执行请求，数据同步
      this.wxMpService.getMenuService().menuCreate(button.toJSONString());
    } catch (WxErrorException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void removeMenu() {
    try {
      wxMpService.getMenuService().menuDelete();
    } catch (WxErrorException e) {
      e.printStackTrace();
    }
  }
}
