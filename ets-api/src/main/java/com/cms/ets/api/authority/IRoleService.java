package com.cms.ets.api.authority;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cms.ets.model.mysql.authority.Role;

/**
 * <p>
 * 角色表 服务类
 * </p>
 *
 * @author cms
 * @since 2019-10-18
 */
public interface IRoleService extends IService<Role> {

    /**
     * 分页查询
     * @param page 分页参数
     * @param name 名称
     * @return Role
     */
    IPage<Role> page(Page<Role> page, String name);

    /**
     * 校验角色名称是否已存在
     * @param roleName 角色名称
     * @param id 主键
     * @return 存在true, 不存在false
     */
    boolean checkNameExist(String roleName, String id);

    /**
     * 获取角色，初始化授权是管理数据集合
     * @param id
     * @return
     */
    Role getByIdInitAuthData(String id);
}
