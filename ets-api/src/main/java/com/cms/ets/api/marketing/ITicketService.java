package com.cms.ets.api.marketing;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cms.ets.model.mysql.marketing.Ticket;

import java.util.List;
import java.util.Map;

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

    /**
     * 获取门票下拉框初始化值
     * @return Map<String, Object>
     * @date 2019年12月19日17:52:43
     */
    Map<String, Object> getTicketSelectParams();

    /**
     * 保存门票信息，初始化门票景点关联数据
     * @param ticket Ticket对象
     * @date 2019年12月25日17:12:02
     */
    void saveAndInitTicketScape(Ticket ticket);

    /**
     * 更新门票信息，重置门票景点关联数据
     * @param ticket Ticket对象
     * @date 2019年12月25日17:12:36
     */
    void updateAndResetTicketScape(Ticket ticket);

    /**
     * 删除关联
     * @param ids Ticket Id 集合
     * @date 2020年1月2日15:53:59
     */
    void removeRelation(List<String> ids);

    /**
     * 查询简单的ticket对象，只查询id、ticketName字段
     * @return List<Ticket>
     * @date 2020年1月3日09:34:15
     */
    List<Ticket> getSimpleList();

    /**
     * 根据用户id查询可销售门票信息
     * @param userId 用户id
     * @return List<Ticket>
     * @date 2020年4月9日10:53:59
     */
    List<Ticket> getByUserId(String userId);
}
