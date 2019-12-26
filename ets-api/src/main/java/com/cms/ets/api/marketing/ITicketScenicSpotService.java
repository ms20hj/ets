package com.cms.ets.api.marketing;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cms.ets.model.mysql.marketing.Ticket;
import com.cms.ets.model.mysql.marketing.TicketScenicSpot;

import java.util.List;

/**
 * <p>
 * 门票景区关联表 服务类
 * </p>
 *
 * @author cms
 * @since 2019-12-26
 */
public interface ITicketScenicSpotService extends IService<TicketScenicSpot> {

    /**
     * 根据门票主键查询
     * @param ticketId Ticket id
     * @return List<TicketScenicSpot>
     * @date 2019年12月26日14:47:57
     */
    List<TicketScenicSpot> getByTicketId(String ticketId);

    /**
     * 根据Ticket id 删除
     * @param ticketId Ticket id
     * @date 2019年12月26日14:48:34
     */
    void removeByTicketId(String ticketId);

    /**
     * 根据Ticket保存
     * 每次保存都会执行removeByTicketId删掉没用的历史数据
     * @param ticket Ticket
     * @date 2019年12月26日15:23:24
     */
    void saveByTicket(Ticket ticket);
}
