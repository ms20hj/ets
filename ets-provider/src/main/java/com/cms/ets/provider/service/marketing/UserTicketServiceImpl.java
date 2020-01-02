package com.cms.ets.provider.service.marketing;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cms.ets.api.marketing.IUserTicketService;
import com.cms.ets.model.mysql.marketing.UserTicket;
import com.cms.ets.provider.mapper.marketing.UserTicketMapper;
import com.google.common.collect.Lists;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 账户门票关联表 服务实现类
 * </p>
 *
 * @author cms
 * @since 2020-01-02
 */
@Service
public class UserTicketServiceImpl extends ServiceImpl<UserTicketMapper, UserTicket> implements IUserTicketService {

    @Override
    public List<String> getTicketIdByUserId(String userId) {
        QueryWrapper<UserTicket> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", userId);
        List<UserTicket> list = this.list(wrapper);
        if (list.isEmpty()) {
            return Lists.newArrayList();
        }
        return list.stream().map(UserTicket::getTicketId).collect(Collectors.toList());
    }

    @Override
    public void removeByUserId(String userId) {
        QueryWrapper<UserTicket> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", userId);
        this.remove(wrapper);
    }

    @Override
    public void removeByTicketId(String ticketId) {
        QueryWrapper<UserTicket> wrapper = new QueryWrapper<>();
        wrapper.eq("ticket_id", ticketId);
        this.remove(wrapper);
    }

    @Override
    public List<String> getUserIdByTicketId(String ticketId) {
        QueryWrapper<UserTicket> wrapper = new QueryWrapper<>();
        wrapper.eq("ticket_id", ticketId);
        List<UserTicket> list = this.list(wrapper);
        if (list.isEmpty()) {
            return Lists.newArrayList();
        }
        return list.stream().map(UserTicket::getUserId).collect(Collectors.toList());
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void authTicketUser(String ticketId, List<String> userIds) {
        this.removeByTicketId(ticketId);
        userIds.stream().forEach(userId -> {
            UserTicket ut = new UserTicket(userId, ticketId);
            this.save(ut);
        });
    }
}
