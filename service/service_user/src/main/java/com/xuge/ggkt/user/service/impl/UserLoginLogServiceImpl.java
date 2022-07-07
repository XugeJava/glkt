package com.xuge.ggkt.user.service.impl;

import com.xuge.ggkt.user.mapper.UserLoginLogMapper;
import com.xuge.ggkt.user.service.UserLoginLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xuge.model.user.UserLoginLog;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户登陆记录表 服务实现类
 * </p>
 *
 * @author xuge
 * @since 2022-07-07
 */
@Service
public class UserLoginLogServiceImpl extends ServiceImpl<UserLoginLogMapper, UserLoginLog> implements UserLoginLogService {

}
