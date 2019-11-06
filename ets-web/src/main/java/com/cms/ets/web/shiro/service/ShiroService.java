package com.cms.ets.web.shiro.service;

import com.cms.ets.model.mysql.authority.Role;
import com.cms.ets.model.mysql.authority.User;

import java.util.List;

public interface ShiroService {

    List<Role> getByUserId(String userId);

    User getByUserName(String userName);
}
