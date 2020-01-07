package com.cms.ets.provider.service.marketing;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cms.ets.api.marketing.IDiscountTravelAgencyService;
import com.cms.ets.model.mysql.marketing.DiscountTravelAgency;
import com.cms.ets.provider.mapper.marketing.DiscountTravelAgencyMapper;

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

}