package com.cms.ets.api.park;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cms.ets.model.mysql.park.Scape;

import java.util.List;

/**
 * <p>
 * 景点信息表 服务类
 * </p>
 *
 * @author cms
 * @since 2019-10-24
 */
public interface IScapeService extends IService<Scape> {

    /**
     * 分页查询
     * @param page 分页参数
     * @param name 名称
     * @return IPage
     */
    IPage<Scape> page(Page<Scape> page, String name);

    /**
     * 校验名称是否已存在
     * @param name 名称
     * @param id 主键
     * @return 存在true, 不存在false
     */
    boolean checkNameExist(String name, String id);

    /**
     * 根据景区id查询景点集合
     * @param scenicSpotId 景区id
     * @return List<Scape>
     * @date 2019年12月25日17:21:26
     */
    List<Scape> getByScenicSpotId(String scenicSpotId);
}
