package com.cms.ets.provider.service.order;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cms.ets.api.order.ISaleOrderTypeService;
import com.cms.ets.model.mysql.order.SaleOrderType;
import com.cms.ets.provider.mapper.order.SaleOrderTypeMapper;

/**
 * <p>
 * 销售订单类型 服务实现类
 * </p>
 *
 * @author cms
 * @since 2020-04-08
 */
@Service
public class SaleOrderTypeServiceImpl extends ServiceImpl<SaleOrderTypeMapper, SaleOrderType> implements ISaleOrderTypeService {

}
