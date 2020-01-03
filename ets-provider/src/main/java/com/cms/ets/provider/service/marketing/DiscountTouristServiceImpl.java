package com.cms.ets.provider.service.marketing;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cms.ets.api.marketing.IDiscountTouristService;
import com.cms.ets.model.mysql.marketing.DiscountTourist;
import com.cms.ets.provider.mapper.marketing.DiscountTouristMapper;

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

}
