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

    /**
     * 保存授权
     * @param roleId 角色id
     * @param userIdList 授权用户id集合
     * @date 2019年10月23日16:46:32
     */
    void saveAuth(String roleId, List<String> userIdList);

    /**
     * 根据用户id查询角色用户集合
     * @param userId 用户id
     * @return List<RoleUser>
     * @date 2019年11月5日10:53:01
     */
    List<RoleUser> getByUserId(String userId);
}
