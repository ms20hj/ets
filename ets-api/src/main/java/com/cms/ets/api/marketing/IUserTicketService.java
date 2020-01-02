package com.cms.ets.api.marketing;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cms.ets.model.mysql.marketing.UserTicket;

import java.util.List;

/**
 * <p>
 * 账户门票关联表 服务类
 * </p>
 *
 * @author cms
 * @since 2020-01-02
 */
public interface IUserTicketService extends IService<UserTicket> {

    /**
     * 根据用户id查询关联的门票id
     * @param userId User id
     * @return List<String>
     * @date 2020年1月2日10:43:29
     */
    List<String> getTicketIdByUserId(String userId);

    /**
     * 根据用户id删除
     * @param userId User id
     */
    void removeByUserId(String userId);

    /**
     * 根据Ticket id 删除
     * @param ticketId Ticket id
     * @date 2020年1月2日15:08:46
     */
    void removeByTicketId(String ticketId);

    /**
     * 根据Ticket Id 查询关联的User id
     * @param ticketId Ticket ID
     * @return List<String>
     */
    List<String> getUserIdByTicketId(String ticketId);

    /**
     * 授权门票用户关系
     * @param ticketId Ticket Id
     * @param userIds User Id集合
     * @date 2020年1月2日15:07:44
     */
    void authTicketUser(String ticketId, List<String> userIds);
}
