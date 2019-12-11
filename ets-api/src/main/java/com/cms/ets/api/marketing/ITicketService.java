package com.cms.ets.api.marketing;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cms.ets.model.mysql.marketing.Ticket;

import java.util.List;

/**
 * <p>
 * 门票信息表 服务类
 * </p>
 *
 * @author cms
 * @since 2019-12-09
 */
public interface ITicketService extends IService<Ticket> {

    /**
     * 分页查询
     * @param page 分页参数
     * @param name 名称
     * @param categoryId 票种id
     * @return IPage
     */
    IPage<Ticket> page(Page<Ticket> page, String name, String categoryId);

    /**
     * 校验名称是否已存在
     * @param name 名称
     * @param id 主键
     * @return 存在true, 不存在false
     */
    boolean checkNameExist(String name, String id);

    /**
     * 根据门票种类ID查询
     * @param categoryId 票种ID
     * @return List<Ticket>
     * @date 2019年10月29日14:05:08
     */
    List<Ticket> getByCategoryId(String categoryId);
}
