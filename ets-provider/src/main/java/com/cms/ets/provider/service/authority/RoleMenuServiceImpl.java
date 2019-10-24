package com.cms.ets.provider.service.authority;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cms.ets.api.authority.IRoleMenuService;
import com.cms.ets.model.mysql.authority.RoleMenu;
import com.cms.ets.provider.mapper.authority.RoleMenuMapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleMenuServiceImpl extends ServiceImpl<RoleMenuMapper, RoleMenu> implements IRoleMenuService {

    @Override
    public List<String> getAuthMenuIdByRoleId(String roleId) {
        List<RoleMenu> list = getByRoleId(roleId);
        List<String> menuIdList = list.stream().map(RoleMenu::getMenuId).collect(Collectors.toList());
        return menuIdList;
    }

    @Override
    public List<RoleMenu> getByRoleId(String roleId) {
        QueryWrapper<RoleMenu> wrapper = new QueryWrapper<>();
        wrapper.eq("role_id", roleId);
        return list(wrapper);
    }

    @Override
    public void removeByRoleId(String roleId) {
        QueryWrapper<RoleMenu> wrapper = new QueryWrapper<>();
        wrapper.eq("role_id", roleId);
        remove(wrapper);
    }

    @Override
    public void saveAuth(String roleId, List<String> menuIdList) {
        this.removeByRoleId(roleId);
        List<RoleMenu> list = menuIdList.stream().map(menuId -> new RoleMenu(roleId, menuId)).collect(Collectors.toList());
        saveBatch(list);
    }
}
