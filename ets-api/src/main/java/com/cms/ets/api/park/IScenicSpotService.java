package com.cms.ets.api.park;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cms.ets.model.mysql.park.ScenicSpot;

import java.util.List;

/**
 * <p>
 * 景区信息表 服务类
 * </p>
 *
 * @author cms
 * @since 2019-10-24
 */
public interface IScenicSpotService extends IService<ScenicSpot> {

    /**
     * 分页查询
     * @param page 分页参数
     * @param name 名称
     * @return IPage
     */
    IPage<ScenicSpot> page(Page<ScenicSpot> page, String name);

    /**
     * 校验名称是否已存在
     * @param name 名称
     * @param id 主键
     * @return 存在true, 不存在false
     */
    boolean checkNameExist(String name, String id);

    /**
     * 简易查询，只查询id、spotName
     * @return List<ScenicSpot>
     * @date 2019年10月25日11:45:21
     */
    List<ScenicSpot> getSimpleList();
}
