package com.cms.ets.provider.service.marketing;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cms.ets.api.marketing.ITicketScapeService;
import com.cms.ets.api.park.IScapeService;
import com.cms.ets.model.mysql.marketing.Ticket;
import com.cms.ets.model.mysql.marketing.TicketScape;
import com.cms.ets.model.mysql.park.Scape;
import com.cms.ets.provider.mapper.marketing.TicketScapeMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 门票景点可进次数配置 服务实现类
 * </p>
 *
 * @author cms
 * @since 2019-12-25
 */
@Service
public class TicketScapeServiceImpl extends ServiceImpl<TicketScapeMapper, TicketScape> implements ITicketScapeService {

    @Autowired
    private IScapeService scapeService;

    @Override
    public void saveByTicket(Ticket ticket) {
        List<Scape> list = scapeService.getByScenicSpotId(ticket.getScenicSpotId());
        if (list.isEmpty()) {
            return;
        }
        List<TicketScape> ticketScapeList = list.stream().map(scape -> new TicketScape(ticket.getId(), ticket.getScenicSpotId(),
                scape.getId(), scape.getScapeName())).collect(Collectors.toList());
        this.saveBatch(ticketScapeList);
    }

    @Override
    public void resetByTicket(Ticket ticket) {
        // 查询配置信息
        List<TicketScape> list = this.getByTicketIdAndScenicSpotId(ticket.getId(), ticket.getScenicSpotId());
        // 不为空，已经有配置数据生成，直接return
        if (!list.isEmpty()) {
            return;
        }
        // 先去删掉没用的配置数据
        this.removeByTicketId(ticket.getId());
        this.saveByTicket(ticket);
    }

    @Override
    public void removeByTicketId(String ticketId) {
        QueryWrapper<TicketScape> wrapper = new QueryWrapper<>();
        wrapper.eq("ticket_id", ticketId);
        remove(wrapper);
    }

    @Override
    public List<TicketScape> getByTicketIdAndScenicSpotId(String ticketId, String scenicSpotId) {
        QueryWrapper<TicketScape> wrapper = new QueryWrapper<>();
        wrapper.eq("ticket_id", ticketId);
        wrapper.eq("scenic_spot_id", scenicSpotId);
        return list(wrapper);
    }
}
