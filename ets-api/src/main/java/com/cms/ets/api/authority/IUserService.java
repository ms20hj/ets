package com.cms.ets.api.authority;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cms.ets.model.mysql.system.User;

/**
 * <p>
 * 用户表 服务类
 * </p>
 * @author cms
 * @since 2019-07-22
 */
public interface IUserService extends IService<User> {

    /**
     * 分页查询
     * @param page 分页信息
     * @param name 用户名/真实姓名
     * @return IPage<User>
     */
    IPage<User> page(IPage<User> page, String name);
}
