package com.cms.ets.provider.service.authority;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cms.ets.api.authority.IMenuService;
import com.cms.ets.model.conversion.Route;
import com.cms.ets.model.mysql.authority.Menu;
import com.cms.ets.provider.mapper.authority.MenuMapper;
import com.google.common.collect.Lists;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>
 * 菜单表 服务实现类
 * </p>
 *
 * @author cms
 * @since 2019-10-23
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService {

    @Override
    public List<Menu> getByCatogory(String category) {
        QueryWrapper<Menu> wrapper = new QueryWrapper<>();
        wrapper.eq("category", category);
        return list(wrapper);
    }

    @Override
    public List<Menu> getFirstLevelMenu() {
        QueryWrapper<Menu> wrapper = new QueryWrapper<>();
        wrapper.isNull("parent_id");
        wrapper.orderByAsc("sort");
        return list(wrapper);
    }

    @Override
    public List<Menu> getSecondLevelMenu() {
        QueryWrapper<Menu> wrapper = new QueryWrapper<>();
        wrapper.isNotNull("parent_id");
        wrapper.orderByAsc("sort");
        return list(wrapper);
    }

    @Override
    public List<Menu> getMenuTreeByUserIdAndCategory(String userId, String category) {
        List<Menu> menuList = baseMapper.getByUserIdAndCategory(userId, category);
        if (menuList.isEmpty()) {
            return Lists.newArrayList();
        }
        Set<String> parentIdList = menuList.stream().map(Menu::getParentId).collect(Collectors.toSet());
        List<Menu> parentList = getByIds(Lists.newArrayList(parentIdList));
        parentList.stream().forEach(parent -> {
            List<Menu> children = menuList.stream().filter(menu -> menu.getParentId().equals(parent.getId())).collect(Collectors.toList());
            parent.setChildren(children);
        });
        return parentList;
    }

    @Override
    public List<Menu> getByIds(List<String> ids) {
        QueryWrapper<Menu> wrapper = new QueryWrapper<>();
        wrapper.in("id", ids.toArray());
        wrapper.orderByAsc("sort");
        return list(wrapper);
    }

    @Override
    public List<Menu> getByParentId(String parentId) {
        QueryWrapper<Menu> wrapper = new QueryWrapper<>();
        wrapper.eq("parent_id", parentId);
        wrapper.orderByAsc("sort");
        return list(wrapper);
    }

    @Override
    public List<Route> getRouteByUserIdAndCategory(String userId, String category) {
        List<Menu> menuList = this.getMenuTreeByUserIdAndCategory(userId, category);
        if (menuList.isEmpty()) {
            return Lists.newArrayList();
        }
        List<Route> routes = menuList.stream().map(menu -> {
            Route route = new Route(menu.getId(), menu.getMenuName(), "/" + menu.getUrl(), menu.getIcon());

            if (menu.getCategory() != null && !menu.getChildren().isEmpty()) {
                List<Route> children = menu.getChildren().stream().map(child -> {
                    Route childRoute = new Route(child.getId(), child.getMenuName(), route.getPath() + "/" + child.getUrl());
                    return childRoute;
                }).collect(Collectors.toList());
                route.setChildren(children);
            }
            return route;
        }).collect(Collectors.toList());
        return routes;
    }
}
