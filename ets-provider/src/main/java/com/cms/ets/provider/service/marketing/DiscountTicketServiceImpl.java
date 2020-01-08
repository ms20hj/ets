package com.cms.ets.provider.service.marketing;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cms.ets.api.marketing.IDiscountTicketService;
import com.cms.ets.model.mysql.marketing.Discount;
import com.cms.ets.model.mysql.marketing.DiscountTicket;
import com.cms.ets.provider.mapper.marketing.DiscountTicketMapper;

import java.util.List;

/**
 * <p>
 * 优惠配置门票关联表 服务实现类
 * </p>
 *
 * @author cms
 * @since 2020-01-03
 */
@Service
public class DiscountTicketServiceImpl extends ServiceImpl<DiscountTicketMapper, DiscountTicket> implements IDiscountTicketService {

    @Override
    public void saveByDiscount(Discount discount) {
        this.removeByDiscountId(discount.getId());
        discount.getTicketIds().stream().forEach(ticketId -> {
            this.save(new DiscountTicket(discount.getId(), ticketId));
        });
    }

    @Override
    public void removeByDiscountId(String discountId) {
        QueryWrapper<DiscountTicket> wrapper = new QueryWrapper<>();
        wrapper.eq("discount_id", discountId);
        this.remove(wrapper);
    }

    @Override
    public List<DiscountTicket> getByDiscountId(String discountId) {
        QueryWrapper<DiscountTicket> wrapper = new QueryWrapper<>();
        wrapper.eq("discount_id", discountId);
        return this.list(wrapper);
    }
}
