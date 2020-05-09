package com.cms.ets.api.order;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cms.ets.model.mysql.authority.User;
import com.cms.ets.model.mysql.order.SaleOrder;

/**
 * <p>
 * 销售订表 服务类
 * </p>
 *
 * @author cms
 * @since 2020-04-08
 */
public interface ISaleOrderService extends IService<SaleOrder> {

    /**
     *
     * @param length 订单SaleOrderType集合长度
     * @param currentUser 当前用户
     * @return SaleOrder
     */
    SaleOrder createOrder(int length, User currentUser);
}
