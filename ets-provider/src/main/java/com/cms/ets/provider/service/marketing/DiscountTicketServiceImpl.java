package com.cms.ets.provider.service.marketing;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cms.ets.api.marketing.IDiscountTicketService;
import com.cms.ets.model.mysql.marketing.DiscountTicket;
import com.cms.ets.provider.mapper.marketing.DiscountTicketMapper;

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

}
