package com.xuge.ggkt.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xuge.ggkt.user.mapper.UserInfoMapper;
import com.xuge.ggkt.user.service.UserInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xuge.model.user.UserInfo;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author xuge
 * @since 2022-07-07
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements UserInfoService {

  @Override
  public UserInfo getByOpenid(String openId) {
    QueryWrapper<UserInfo> wrapper = new QueryWrapper<>();
    wrapper.eq("open_id",openId);
    return  baseMapper.selectOne(wrapper);
  }
}
