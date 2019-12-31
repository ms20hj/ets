package com.cms.ets.provider.service.marketing;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
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
        for (String scenicSpotId : ticket.getScenicSpotIdList()) {
            this.saveByTicketIdAndScenicSpotId(ticket.getId(), scenicSpotId);
        }
    }

    @Override
    public void resetByTicket(Ticket ticket) {
        // 删除景区id不存在的数据
        QueryWrapper<TicketScape> wrapper = new QueryWrapper<>();
        wrapper.eq("ticket_id", ticket.getId());
        wrapper.notIn("scenic_spot_id", ticket.getScenicSpotIdList().toArray());
        this.remove(wrapper);

        ticket.getScenicSpotIdList().stream().forEach(scenicSpotId -> {
            List<TicketScape> list = this.getByTicketIdAndScenicSpotId(ticket.getId(), scenicSpotId);
            if (list.isEmpty()) {
                this.saveByTicketIdAndScenicSpotId(ticket.getId(), scenicSpotId);
            }
        });
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

    @Override
    public void saveByTicketIdAndScenicSpotId(String ticketId, String scenicSpotId) {
        List<Scape> list = scapeService.getByScenicSpotId(scenicSpotId);
        if (list.isEmpty()) {
            return;
        }
        List<TicketScape> ticketScapeList = list.stream().map(scape -> new TicketScape(ticketId, scenicSpotId,
                scape.getId(), scape.getScapeName())).collect(Collectors.toList());
        this.saveBatch(ticketScapeList);
    }

    @Override
    public List<TicketScape> getByTicketId(String ticketId) {
        QueryWrapper<TicketScape> wrapper = new QueryWrapper<>();
        wrapper.eq("ticket_id", ticketId);
        return list(wrapper);
    }

    @Override
    public void updateInConfig(TicketScape ticketScape) {
        UpdateWrapper<TicketScape> wrapper = new UpdateWrapper<>();
        wrapper.set("all_in", ticketScape.getAllIn());
        wrapper.set("day_in", ticketScape.getDayIn());
        wrapper.eq("id", ticketScape.getId());
        this.update(wrapper);
    }
}
