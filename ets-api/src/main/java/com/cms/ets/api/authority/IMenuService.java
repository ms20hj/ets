package com.cms.ets.api.authority;

import com.baomidou.mybatisplus.extension.service.IService;
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
}
