package com.cms.ets.api.authority;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cms.ets.model.conversion.Route;
import com.cms.ets.model.mysql.authority.Menu;

import java.util.List;

/**
 * <p>
 * 菜单表 服务类
 * </p>
 *
 * @author cms
 * @since 2019-10-23
 */
public interface IMenuService extends IService<Menu> {

    /**
     * 根据类型查询菜单
     * @param category 类型
     * @return List<Menu>
     * @date 2019年10月23日16:00:30
     */
    List<Menu> getByCatogory(String category);

    /**
     * 查询一级菜单
     * @return List<Menu>
     * @date 2020年1月10日11:35:49
     */
    List<Menu> getFirstLevelMenu();

    /**
     * 直接查询二级菜单
     * @return List<Menu>
     * @date 2020年1月10日11:35:55
     */
    List<Menu> getSecondLevelMenu();

    /**
     * 根据用户id和分类查询菜单树
     * @param userId 用户id
     * @param category 分类
     * @return List<Menu>
     */
    List<Menu> getMenuTreeByUserIdAndCategory(String userId, String category);

    /**
     * 根据id集合查询
     * @param ids Menu id集合
     * @return List<Menu>
     * @date 2020年1月10日14:16:24
     */
    List<Menu> getByIds(List<String> ids);

    /**
     * 跟父级id查询
     * @param parentId
     * @return  List<Menu>
     * @date 2020年1月10日14:19:09
     */
    List<Menu> getByParentId(String parentId);

    /**
     * 查询菜单路由
     * @param userId 用户id
     * @param category 分类
     * @return List<Route>
     * @date 2020年1月10日16:16:52
     */
    List<Route> getRouteByUserIdAndCategory(String userId, String category);
}
