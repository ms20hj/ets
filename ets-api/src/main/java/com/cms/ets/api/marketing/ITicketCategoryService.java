package com.cms.ets.api.marketing;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cms.ets.model.mysql.marketing.TicketCategory;

import java.util.List;

/**
 * <p>
 * 门票分类表 服务类
 * </p>
 *
 * @author cms
 * @since 2019-12-09
 */
public interface ITicketCategoryService extends IService<TicketCategory> {

    /**
     * 校验名称是否已存在
     * @param name 名称
     * @param id 主键
     * @return 存在true, 不存在false
     */
    boolean checkNameExist(String name, String id);

    /**
     * 查询门票种类，返回一个树形数据
     * @return List<TicketCategory>
     * @date 2019年12月9日15:43:37
     */
    List<TicketCategory> listTree();
}
