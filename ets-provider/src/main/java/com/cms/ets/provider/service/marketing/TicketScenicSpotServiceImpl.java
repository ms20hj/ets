package com.cms.ets.provider.service.marketing;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cms.ets.api.marketing.ITicketScenicSpotService;
import com.cms.ets.model.mysql.marketing.Ticket;
import com.cms.ets.model.mysql.marketing.TicketScenicSpot;
import com.cms.ets.provider.mapper.marketing.TicketScenicSpotMapper;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 门票景区关联表 服务实现类
 * </p>
 *
 * @author cms
 * @since 2019-12-26
 */
@Service
public class TicketScenicSpotServiceImpl extends ServiceImpl<TicketScenicSpotMapper, TicketScenicSpot> implements ITicketScenicSpotService {

    @Override
    public List<TicketScenicSpot> getByTicketId(String ticketId) {
        QueryWrapper<TicketScenicSpot> wrapper = new QueryWrapper<>();
        wrapper.eq("ticket_id", ticketId);
        return list(wrapper);
    }

    @Override
    public void removeByTicketId(String ticketId) {
        QueryWrapper<TicketScenicSpot> wrapper = new QueryWrapper<>();
        wrapper.eq("ticket_id", ticketId);
        remove(wrapper);
    }

    @Override
    public void saveByTicket(Ticket ticket) {
        this.removeByTicketId(ticket.getId());

        List<TicketScenicSpot> list = ticket.getScenicSpotIdList().stream().map(scenicSpotId ->
                new TicketScenicSpot(scenicSpotId, ticket.getId())).collect(Collectors.toList());
        this.saveBatch(list);
    }
}
