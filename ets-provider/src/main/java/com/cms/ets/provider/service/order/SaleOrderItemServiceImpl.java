package com.cms.ets.provider.service.order;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cms.ets.api.order.ISaleOrderItemService;
import com.cms.ets.model.mysql.order.SaleOrderItem;
import com.cms.ets.provider.mapper.order.SaleOrderItemMapper;

/**
 * <p>
 * 销售订单明细项表 服务实现类
 * </p>
 *
 * @author cms
 * @since 2020-04-08
 */
@Service
public class SaleOrderItemServiceImpl extends ServiceImpl<SaleOrderItemMapper, SaleOrderItem> implements ISaleOrderItemService {

}
