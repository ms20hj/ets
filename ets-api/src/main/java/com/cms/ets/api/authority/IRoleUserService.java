package com.cms.ets.api.authority;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cms.ets.model.mysql.authority.RoleUser;

import java.util.List;

public interface IRoleUserService extends IService<RoleUser> {

    /**
     * 根据角色id获取已授权的 用户id集合
     * @param roleId 角色id
     * @return List<String>
     * @date 2019年10月21日17:34:54
     */
    List<String> getAuthUserIdByRoleId(String roleId);

    /**
     * 根据角色id查询
     * @param roleId 角色id
     * @return List<RoleUser>
     * @date 2019年10月21日17:36:11
     */
    List<RoleUser> getByRoleId(String roleId);

    /**
     * 根据角色id删除
     * @param roleId 角色id
     * @date 2019年10月21日17:36:18
     */
    void removeByRoleId(String roleId);
}
