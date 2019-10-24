package com.cms.ets.provider.service.authority;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cms.ets.api.authority.IRoleMenuService;
import com.cms.ets.api.authority.IRoleService;
import com.cms.ets.api.authority.IRoleUserService;
import com.cms.ets.model.mysql.authority.Role;
import com.cms.ets.provider.mapper.authority.RoleMapper;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 * @author cms
 * @since 2019-10-18
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

    @Autowired
    private IRoleUserService roleUserService;
    @Autowired
    private IRoleMenuService roleMenuService;

    @Override
    public IPage<Role> page(Page<Role> page, String name) {
        QueryWrapper<Role> wrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(name)) {
            wrapper.like("role_name", name);
        }
        return page(page, wrapper);
    }

    @Override
    public boolean checkNameExist(String roleName, String id) {
        QueryWrapper<Role> wrapper = new QueryWrapper<>();
        wrapper.eq("role_name", roleName);
        if (StringUtils.isNotEmpty(id)){
            wrapper.ne("id", id);
        }
        List<Role> list = list(wrapper);
        return !list.isEmpty();
    }

    @Override
    public Role getByIdInitAuthData(String id) {
        Role role = getById(id);
        if (role == null) {
            return null;
        }
        List<String> userIds = roleUserService.getAuthUserIdByRoleId(id);
        List<String> menuIds = roleMenuService.getAuthMenuIdByRoleId(id);
        role.setUserIdList(userIds);
        role.setMenuIdList(menuIds);
        return role;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveOrUpdateAuth(Role role) {
        if (StringUtils.isNotEmpty(role.getId())) {
            updateById(role);
        } else {
            save(role);
        }
        roleUserService.saveAuth(role.getId(), role.getUserIdList());
        roleMenuService.saveAuth(role.getId(), role.getMenuIdList());
    }
}
