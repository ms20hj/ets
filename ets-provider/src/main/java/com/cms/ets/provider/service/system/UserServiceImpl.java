package com.cms.ets.provider.service.system;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cms.ets.api.authority.IUserService;
import com.cms.ets.model.mysql.system.User;
import com.cms.ets.provider.mapper.system.UserMapper;
import org.apache.commons.lang.StringUtils;

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

    @Override
    public IPage<User> page(IPage<User> page, String name) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        if (StringUtils.isNotEmpty(name)) {
            wrapper.like("user_name",name).or().like("real_name", name);
        }
        wrapper.orderByDesc("create_time");
        return page(page, wrapper);
    }
}
