package com.xuge.ggkt.wechat.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xuge.ggkt.wechat.mapper.MenuMapper;
import com.xuge.ggkt.wechat.service.MenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xuge.model.wechat.Menu;
import com.xuge.vo.wechat.MenuVo;
import org.springframework.beans.BeanUtils;
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
}
