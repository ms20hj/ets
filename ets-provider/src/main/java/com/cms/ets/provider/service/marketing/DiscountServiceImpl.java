package com.cms.ets.provider.service.marketing;
import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cms.ets.api.marketing.IDiscountService;
import com.cms.ets.model.mysql.marketing.Discount;
import com.cms.ets.provider.mapper.marketing.DiscountMapper;
import org.apache.commons.lang.StringUtils;

import java.util.List;

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
}
