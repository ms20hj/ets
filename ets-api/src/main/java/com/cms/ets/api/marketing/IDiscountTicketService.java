package com.cms.ets.api.marketing;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cms.ets.model.mysql.marketing.Discount;
import com.cms.ets.model.mysql.marketing.DiscountTicket;

import java.util.List;

/**
 * <p>
 * 优惠配置门票关联表 服务类
 * </p>
 *
 * @author cms
 * @since 2020-01-03
 */
public interface IDiscountTicketService extends IService<DiscountTicket> {

    /**
     * 根据优惠保存关联的门票信息
     * @param discount Discount对象
     * @date 2020年1月8日10:02:02
     */
    void saveByDiscount(Discount discount);

    /**
     * 根据优惠id删除
     * @param discountId 优惠id
     * @date 2020年1月8日10:02:34
     */
    void removeByDiscountId(String discountId);

    /**
     * 根据优惠id查询
     * @param discountId 优惠id
     */
    List<DiscountTicket> getByDiscountId(String discountId);
}
