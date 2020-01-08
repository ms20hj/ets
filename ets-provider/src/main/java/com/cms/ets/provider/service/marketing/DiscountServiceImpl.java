package com.cms.ets.provider.service.marketing;
import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cms.ets.api.marketing.IDiscountService;
import com.cms.ets.api.marketing.IDiscountTicketService;
import com.cms.ets.api.marketing.IDiscountTouristService;
import com.cms.ets.api.marketing.IDiscountTravelAgencyService;
import com.cms.ets.model.mysql.marketing.Discount;
import com.cms.ets.model.mysql.marketing.Ticket;
import com.cms.ets.model.mysql.marketing.Tourist;
import com.cms.ets.model.mysql.marketing.TravelAgency;
import com.cms.ets.provider.mapper.marketing.DiscountMapper;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 优惠配置表 服务实现类
 * </p>
 *
 * @author cms
 * @since 2020-01-03
 */
@Service
public class DiscountServiceImpl extends ServiceImpl<DiscountMapper, Discount> implements IDiscountService {

    @Autowired
    private IDiscountTicketService discountTicketService;
    @Autowired
    private IDiscountTouristService discountTouristService;
    @Autowired
    private IDiscountTravelAgencyService discountTravelAgencyService;

    @Override
    public IPage<Discount> page(Page<Discount> page, String name) {
        QueryWrapper<Discount> wrapper = new QueryWrapper<>();
        if (StringUtils.isNotEmpty(name)) {
            wrapper.like("disc_name", name);
        }
        return super.page(page, wrapper);
    }

    @Override
    public boolean checkNameExist(String name, String id) {
        QueryWrapper<Discount> wrapper = new QueryWrapper<>();
        wrapper.eq("disc_name", name);
        if (StringUtils.isNotEmpty(id)) {
            wrapper.ne("id", id);
        }
        List<Discount> list = list(wrapper);
        return !list.isEmpty();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(value = CACHE_DISCOUNT, key = "#discount.id", condition="#discount.id != null && #discount.id != ''", beforeInvocation=true)
    public void saveOrUpdateEntity(Discount discount) {
        super.saveOrUpdate(discount);
        discountTicketService.saveByDiscount(discount);
        discountTouristService.saveByDiscount(discount);
        discountTravelAgencyService.saveByDiscount(discount);
    }

    @Override
    public Discount getRelationById(String id) {
        return baseMapper.getRelation(id);
    }

    @Override
    @Cacheable(value = CACHE_DISCOUNT, key = "#id")
    public Discount getForEdit(String id) {
        Discount discount = this.getRelationById(id);
        discount.setTicketIds(discount.getTicketList().stream().map(Ticket::getId).collect(Collectors.toList()));
        discount.setTouristIds(discount.getTouristList().stream().map(Tourist::getId).collect(Collectors.toList()));
        discount.setTravelAgencyIds(discount.getTravelAgencyList().stream().map(TravelAgency::getId).collect(Collectors.toList()));
        return discount;
    }

    @Override
    public void removeRelationById(String id) {
        discountTicketService.removeByDiscountId(id);
        discountTouristService.removeByDiscountId(id);
        discountTravelAgencyService.removeByDiscountId(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(value = CACHE_DISCOUNT, key = "#id", beforeInvocation=true)
    public boolean removeById(Serializable id) {
        this.removeRelationById(String.valueOf(id));
        return super.removeById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(value = CACHE_DISCOUNT, key = "#id", allEntries = true, beforeInvocation=true)
    public boolean removeByIds(Collection<? extends Serializable> idList) {
        for (Serializable id : idList) {
            this.removeById(id);
        }
        return true;
    }
}
