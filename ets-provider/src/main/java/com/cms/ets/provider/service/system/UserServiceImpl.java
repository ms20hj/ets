package com.cms.ets.provider.service.system;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cms.ets.api.system.IUserService;
import com.cms.ets.model.mysql.system.User;
import com.cms.ets.provider.mapper.system.UserMapper;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author cms
 * @since 2019-07-22
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
