package com.cms.ets.provider.mapper.authority;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cms.ets.model.mysql.authority.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 角色表 Mapper 接口
 * </p>
 *
 * @author cms
 * @since 2019-10-18
 */
public interface RoleMapper extends BaseMapper<Role> {

    List<Role> getByUserId(@Param("userId") String userId);
}
