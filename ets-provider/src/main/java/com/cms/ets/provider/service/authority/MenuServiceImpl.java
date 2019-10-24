package com.cms.ets.provider.service.authority;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cms.ets.api.authority.IMenuService;
import com.cms.ets.model.mysql.authority.Menu;
import com.cms.ets.provider.mapper.authority.MenuMapper;

import java.util.List;

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
}
