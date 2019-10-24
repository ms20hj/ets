package com.cms.ets.provider.service.authority;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cms.ets.api.authority.IRoleUserService;
import com.cms.ets.model.mysql.authority.RoleUser;
import com.cms.ets.provider.mapper.authority.RoleUserMapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleUserServiceImpl extends ServiceImpl<RoleUserMapper, RoleUser> implements IRoleUserService {

    @Override
    public List<String> getAuthUserIdByRoleId(String roleId) {
        List<RoleUser> list = getByRoleId(roleId);
        List<String> userIdList = list.stream().map(RoleUser::getUserId).collect(Collectors.toList());
        return userIdList;
    }

    @Override
    public List<RoleUser> getByRoleId(String roleId) {
        QueryWrapper<RoleUser> wrapper = new QueryWrapper<>();
        wrapper.eq("role_id", roleId);
        return list(wrapper);
    }

    @Override
    public void removeByRoleId(String roleId) {
        QueryWrapper<RoleUser> wrapper = new QueryWrapper<>();
        wrapper.eq("role_id", roleId);
        remove(wrapper);
    }

    @Override
    public void saveAuth(String roleId, List<String> userIdList) {
        this.removeByRoleId(roleId);
        List<RoleUser> list = userIdList.stream().map(userId -> new RoleUser(roleId, userId)).collect(Collectors.toList());
        saveBatch(list);
    }
}
