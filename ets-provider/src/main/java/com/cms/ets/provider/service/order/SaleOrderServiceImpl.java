package com.cms.ets.provider.service.order;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cms.ets.api.order.ISaleOrderService;
import com.cms.ets.model.mysql.authority.User;
import com.cms.ets.model.mysql.order.SaleOrder;
import com.cms.ets.model.mysql.order.SaleOrderType;
import com.cms.ets.provider.mapper.order.SaleOrderMapper;
import com.google.common.collect.Lists;

import java.util.List;

/**
 * <p>
 * 销售订表 服务实现类
 * </p>
 *
 * @author cms
 * @since 2020-04-08
 */
@Service
public class SaleOrderServiceImpl extends ServiceImpl<SaleOrderMapper, SaleOrder> implements ISaleOrderService {

    @Override
    public SaleOrder createOrder(int length, User currentUser) {
        SaleOrder saleOrder = new SaleOrder();
        saleOrder.setCreateId(currentUser.getId());
        saleOrder.setCreateName(currentUser.getUserName());
        saleOrder.setSourceAreaId("100000000000");
        saleOrder.setSourceAreaName("中国");
        List<SaleOrderType> list = Lists.newArrayList();
        if (length == 0) {
            saleOrder.setSaleOrderTypeList(list);
            return saleOrder;
        }
        for (int i = 0; i < length; i++) {
            SaleOrderType saleOrderType = new SaleOrderType(1, 1);
            list.add(saleOrderType);
        }
        saleOrder.setSaleOrderTypeList(list);
        return saleOrder;
    }
}
