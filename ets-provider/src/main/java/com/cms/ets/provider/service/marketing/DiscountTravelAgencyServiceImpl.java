package com.cms.ets.provider.service.marketing;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cms.ets.api.marketing.IDiscountTravelAgencyService;
import com.cms.ets.model.mysql.marketing.Discount;
import com.cms.ets.model.mysql.marketing.DiscountTravelAgency;
import com.cms.ets.provider.mapper.marketing.DiscountTravelAgencyMapper;

import java.util.List;

/**
 * <p>
 * 优惠配置客户关联表 服务实现类
 * </p>
 *
 * @author cms
 * @since 2020-01-03
 */
@Service
public class DiscountTravelAgencyServiceImpl extends ServiceImpl<DiscountTravelAgencyMapper, DiscountTravelAgency> implements IDiscountTravelAgencyService {

    @Override
    public void saveByDiscount(Discount discount) {
        this.removeByDiscountId(discount.getId());
        discount.getTravelAgencyIds().stream().forEach(taId -> {
            this.save(new DiscountTravelAgency(discount.getId(), taId));
        });
    }

    @Override
    public void removeByDiscountId(String discountId) {
        QueryWrapper<DiscountTravelAgency> wrapper = new QueryWrapper<>();
        wrapper.eq("discount_id", discountId);
        this.remove(wrapper);
    }

    @Override
    public List<DiscountTravelAgency> getByDiscountId(String discountId) {
        QueryWrapper<DiscountTravelAgency> wrapper = new QueryWrapper<>();
        wrapper.eq("discount_id", discountId);
        return this.list(wrapper);
    }
}
