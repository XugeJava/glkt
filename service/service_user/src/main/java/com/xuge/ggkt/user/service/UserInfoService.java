package com.xuge.ggkt.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xuge.model.user.UserInfo;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author xuge
 * @since 2022-07-07
 */
public interface UserInfoService extends IService<UserInfo> {

  UserInfo getByOpenid(String openId);
}
