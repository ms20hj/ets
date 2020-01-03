package com.cms.ets.provider.service.marketing;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cms.ets.api.marketing.IDiscountSourceAreaService;
import com.cms.ets.model.mysql.marketing.DiscountSourceArea;
import com.cms.ets.provider.mapper.marketing.DiscountSourceAreaMapper;

/**
 * <p>
 * 优惠配置客源地关联表 服务实现类
 * </p>
 *
 * @author cms
 * @since 2020-01-03
 */
@Service
public class DiscountSourceAreaServiceImpl extends ServiceImpl<DiscountSourceAreaMapper, DiscountSourceArea> implements IDiscountSourceAreaService {

}
