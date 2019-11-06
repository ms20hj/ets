package com.cms.ets.web.shiro.service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.cms.ets.api.authority.IRoleService;
import com.cms.ets.api.authority.IUserService;
import com.cms.ets.model.mysql.authority.Role;
import com.cms.ets.model.mysql.authority.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShiroServiceImpl implements ShiroService{

    @Reference
    private IUserService userService;
    @Reference
    private IRoleService roleService;

    @Override
    public List<Role> getByUserId(String userId) {
        return roleService.getByUserId(userId);
    }

    @Override
    public User getByUserName(String userName) {
        return userService.getByUserName(userName);
    }
}
