package com.cms.ets.provider.mapper.authority;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cms.ets.model.mysql.authority.Menu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 菜单表 Mapper 接口
 * </p>
 *
 * @author cms
 * @since 2019-10-23
 */
public interface MenuMapper extends BaseMapper<Menu> {

    /**
     * 根据用户id和分类查询菜单树
     * @param userId 用户id
     * @param category 分类
     * @return List<Menu>
     */
    List<Menu> getByUserIdAndCategory(@Param("userId") String userId, @Param("category") String category);
}
