package com.cms.ets.api.marketing;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cms.ets.model.mysql.marketing.Ticket;
import com.cms.ets.model.mysql.marketing.TicketScape;

import java.util.List;

/**
 * <p>
 * 门票景点可进次数配置 服务类
 * </p>
 *
 * @author cms
 * @since 2019-12-25
 */
public interface ITicketScapeService extends IService<TicketScape> {

    /**
     * 根据票类信息保存
     * @param ticket Ticket
     * @date 2019年12月25日17:17:12
     */
    void saveByTicket(Ticket ticket);

    /**
     * 根据票类信息重新设置保存
     * @param ticket Ticket
     * @date 2019年12月25日17:29:35
     */
    void resetByTicket(Ticket ticket);

    /**
     * 根据票类id删除
     * @param ticketId Ticket 主键
     * @date 2019年12月25日17:30:27
     */
    void removeByTicketId(String ticketId);

    /**
     * 根据门票id和景区id查询
     * @param ticketId Ticket 主键
     * @param scenicSpotId ScenicSpot 主键
     * @return List<TicketScape>
     * @date 2019年12月25日17:35:20
     */
    List<TicketScape> getByTicketIdAndScenicSpotId(String ticketId, String scenicSpotId);

    /**
     * 根据票类id和景区id保存门票景点关联关系
     * @param ticketId 票类id
     * @param scenicSpotId 景区id
     * @date 2019年12月26日15:32:35
     */
    void saveByTicketIdAndScenicSpotId(String ticketId, String scenicSpotId);

    /**
     * 根据票类id查询
     * @param ticketId Ticket id
     * @return List<TicketScape>
     * @date 2019年12月27日10:14:17
     */
    List<TicketScape> getByTicketId(String ticketId);
}
