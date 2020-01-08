package com.cms.ets.provider.service.marketing;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cms.ets.api.marketing.IDiscountTouristService;
import com.cms.ets.model.mysql.marketing.Discount;
import com.cms.ets.model.mysql.marketing.DiscountTourist;
import com.cms.ets.provider.mapper.marketing.DiscountTouristMapper;

import java.util.List;

/**
 * <p>
 * 优惠配置游客类型关联表 服务实现类
 * </p>
 *
 * @author cms
 * @since 2020-01-03
 */
@Service
public class DiscountTouristServiceImpl extends ServiceImpl<DiscountTouristMapper, DiscountTourist> implements IDiscountTouristService {
    @Override
    public void saveByDiscount(Discount discount) {
        this.removeByDiscountId(discount.getId());
        discount.getTouristIds().stream().forEach(touristId -> {
            this.save(new DiscountTourist(discount.getId(), touristId));
        });
    }

    @Override
    public void removeByDiscountId(String discountId) {
        QueryWrapper<DiscountTourist> wrapper = new QueryWrapper<>();
        wrapper.eq("discount_id", discountId);
        this.remove(wrapper);
    }

    @Override
    public List<DiscountTourist> getByDiscountId(String discountId) {
        QueryWrapper<DiscountTourist> wrapper = new QueryWrapper<>();
        wrapper.eq("discount_id", discountId);
        return list(wrapper);
    }
}
