package com.cms.ets.api.marketing;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cms.ets.model.mysql.marketing.Discount;
import com.cms.ets.model.mysql.marketing.DiscountTravelAgency;

import java.util.List;

/**
 * 优惠旅行社关联配置服务类
 * @author cms
 */
public interface IDiscountTravelAgencyService extends IService<DiscountTravelAgency> {
    /**
     * 根据优惠保存关联的旅行社信息
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
    List<DiscountTravelAgency> getByDiscountId(String discountId);
}
