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
     * @return IPage
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

    /**
     * 保存或者更新对象，同时更新授权信息
     * @param role 角色对象
     * @date 2019年10月23日16:44:13
     */
    void saveOrUpdateAuth(Role role);
}
