package com.cms.ets.provider.service.marketing;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cms.ets.api.marketing.ITicketService;
import com.cms.ets.model.mysql.marketing.Ticket;
import com.cms.ets.provider.mapper.marketing.TicketMapper;
import org.apache.commons.lang.StringUtils;

import java.util.List;

/**
 * <p>
 * 门票信息表 服务实现类
 * </p>
 *
 * @author cms
 * @since 2019-12-09
 */
@Service
public class TicketServiceImpl extends ServiceImpl<TicketMapper, Ticket> implements ITicketService {
    @Override
    public IPage<Ticket> page(Page<Ticket> page, String name, String categoryId) {
        QueryWrapper<Ticket> wrapper = new QueryWrapper<>();
        wrapper.eq("ticket_gategory_id", categoryId);
        if (StringUtils.isNotEmpty(name)) {
            wrapper.like("name", name);
        }
        wrapper.orderByAsc("sort_num");
        return page(page, wrapper);
    }

    @Override
    public boolean checkNameExist(String name, String id) {
        QueryWrapper<Ticket> wrapper = new QueryWrapper<>();
        wrapper.eq("name", name);
        if (StringUtils.isNotEmpty(id)) {
            wrapper.ne("id", id);
        }
        List<Ticket> list = list(wrapper);
        return !list.isEmpty();
    }

    @Override
    public List<Ticket> getByCategoryId(String categoryId) {
        QueryWrapper<Ticket> wrapper = new QueryWrapper<>();
        wrapper.eq("ticket_gategory_id", categoryId);
        return list(wrapper);
    }
}
