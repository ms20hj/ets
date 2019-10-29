package com.cms.ets.api.park;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cms.ets.model.mysql.park.SaleSite;

import java.util.List;

/**
 * <p>
 * 销售站点信息表 服务类
 * </p>
 *
 * @author cms
 * @since 2019-10-25
 */
public interface ISaleSiteService extends IService<SaleSite> {
    /**
     * 分页查询
     * @param page 分页参数
     * @param name 名称
     * @return IPage
     */
    IPage<SaleSite> page(Page<SaleSite> page, String name);

    /**
     * 校验名称是否已存在
     * @param name 名称
     * @param id 主键
     * @return 存在true, 不存在false
     */
    boolean checkNameExist(String name, String id);

    /**
     * 简易查询对象，只查询id，siteName
     * @return List<SaleSite>
     * @date 2019年10月25日16:19:33
     */
    List<SaleSite> getSimpleList();
}
